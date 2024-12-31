package txmy.dev.updater;

import lombok.extern.log4j.Log4j2;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Log4j2
public class PluginUpdaterHandler {

    public void check() {
        File updaterFolder = new File("./updater");
        if (!updaterFolder.exists()) {
            updaterFolder.mkdirs();
        }

        File file = new File(updaterFolder, "updater.yml");
        if (!file.exists()) {
            InputStream localResource = getClass().getClassLoader().getResourceAsStream("configurations/updater.yml");
            if (localResource != null) {
                try {
                    Files.copy(localResource, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    log.error("error while saving default updater.yml file to /updater/updater.yml: ", e);
                }
            }
        }

        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if (!configuration.getBoolean("enabled", false)) {
            return;
        }

        String method = configuration.getString("method", "SFTP");
        if (method.equalsIgnoreCase("SFTP")) {
            PluginUpdater.Credentials credentials = new PluginUpdater.Credentials(
                    configuration.getString("address", "127.0.0.1"),
                    configuration.getString("username", "user"),
                    configuration.getString("password", "pwd"),
                    configuration.getInt("port", 2022),
                    configuration.getBoolean("auth")
            );

            Path pluginsFolderPath = new File("plugins/").toPath();
            if (!Files.exists(pluginsFolderPath) || !Files.isDirectory(pluginsFolderPath)) {
                log.error("couldn't find plugins folder, searched at path: {}", pluginsFolderPath.toAbsolutePath().normalize().toString());
                return;
            }

            File debugFile = new File(updaterFolder, "debug");
            SFTPPluginUpdater pluginUpdater = new SFTPPluginUpdater(
                    pluginsFolderPath,
                    updaterFolder.toPath().resolve(".sftpcache"),
                    debugFile.exists()
            );

            pluginUpdater.download(
                    credentials,
                    configuration.getString("lookup-folder"),
                    configuration.getBoolean("download-everything"),
                    configuration.getStringList("files-to-download")
            );

            pluginUpdater.saveLastModified();
        }
    }

}
