package txmy.dev.updater;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Interface utilized to represet a plugin updater method
 * that allows you to automatically update certain plugins
 */
public interface PluginUpdater {

    boolean download(Credentials credentials, String folder, boolean everything, @Nullable List<String> fileNames);

    @Getter @AllArgsConstructor
    class Credentials {

        private String host, username, password;
        private int port;

        private boolean auth;

    }

}
