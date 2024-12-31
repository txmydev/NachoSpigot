package txmy.dev.updater;

import com.google.common.base.Preconditions;
import com.jcraft.jsch.*;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

@Log4j2
public class SFTPPluginUpdater implements PluginUpdater {

    private final JSch jsch = new JSch();

    private final Path pluginsFolderPath;
    private final Path cacheLastModifiedTimeFilePath;
    private final boolean debug;

    private Map<String, Integer> lastModifiedCache = new HashMap<>();

    public SFTPPluginUpdater(Path pluginsFolderPath, Path cacheLastModifiedTimeFilePath, boolean debug) {
        this.pluginsFolderPath = pluginsFolderPath;
        this.debug = debug;
        this.cacheLastModifiedTimeFilePath = cacheLastModifiedTimeFilePath;

        if (Files.exists(cacheLastModifiedTimeFilePath)) {
            try (BufferedReader reader = Files.newBufferedReader(cacheLastModifiedTimeFilePath)) {
                reader.lines().forEach(line -> {
                    String[] split = line.split("=");

                    String fileName = split[0];
                    int lastModifiedTime = Integer.parseInt(split[1]);

                    lastModifiedCache.put(fileName, lastModifiedTime);
                    if (debug) {
                        log.info("debug: found in sftp cache file {} with modified time of {}", fileName, lastModifiedTime);
                    }
                });
            } catch (IOException ex) {
                log.error("couldn't read last modified file cache: ", ex);
            }
        }

    }

    @Override
    public boolean download(Credentials credentials,
                            String folder,
                            boolean everything,
                            @Nullable List<String> fileNames) {
        Preconditions.checkArgument(everything || (fileNames != null && !fileNames.isEmpty()), "file names must be specified when not downloading everything!");

        Session session = null;
        ChannelSftp channel = null;
        try {
            session = jsch.getSession(credentials.getUsername(), credentials.getHost(), credentials.getPort());
            if (credentials.isAuth()) {
                session.setPassword(credentials.getPassword());
            }
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect(5000);
            if (!session.isConnected()) {
                log.error("error: couldn't connect to sft server");
                return false;
            }

            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect(5000);

            if (!channel.isConnected()) {
                log.error("error: couldn't establish a connection with the sftp channel");
                return false;
            }

            Vector<ChannelSftp.LsEntry> files = channel.ls(folder);
            for (ChannelSftp.LsEntry fileEntry : files) {
                if (fileEntry.getAttrs().isDir()) {
                    if (debug) {
                        log.info("debug: found directory '{}', skipping..", fileEntry.getFilename());
                    }
                    continue;
                }

                boolean download = everything || fileNames.contains(fileEntry.getFilename());
                if (!download) {
                    if (debug) {
                        log.info("debug: skipping file '{}' as we don't want it", fileEntry.getFilename());
                    }
                    continue;
                }

                if (lastModifiedCache.containsKey(fileEntry.getFilename()) && lastModifiedCache.get(fileEntry.getFilename()) == fileEntry.getAttrs().getMTime()) {
                    if (debug) {
                        log.info("debug: skipping file {} as it wasn't modified since last time.", fileEntry.getFilename());
                    }
                    continue;
                }

                String remoteFilePath = folder + "/" + fileEntry.getFilename();
                try {
                    Files.copy(channel.get(remoteFilePath), pluginsFolderPath.resolve(fileEntry.getFilename()), StandardCopyOption.REPLACE_EXISTING);

                    if (debug) {
                        log.info("debug: downloaded and replaced file {}", fileEntry.getFilename());
                    }

                    lastModifiedCache.put(fileEntry.getFilename(), fileEntry.getAttrs().getMTime());
                } catch (Exception e) {
                    log.error("error while downloading file {}: ", fileEntry.getFilename(), e);
                }
            }

        } catch (JSchException | SftpException ex) {
            log.error("error while opening connection to the sftp: {}", ex.getLocalizedMessage());
            return false;

        } finally {
            if (channel != null) {
                channel.disconnect();
            }

            if (session != null) {
                session.disconnect();
            }
        }

        return true;
    }

    public void saveLastModified() {
        String cacheToString = lastModifiedCache.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("\n"));

        try {
            Files.write(cacheLastModifiedTimeFilePath, cacheToString.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

            if (debug) {
                log.info("saved downloaded files last modified time");
            }

        } catch (IOException ex) {
            log.error("couldn't save last modified cache files: ", ex);
        }
    }

}
