package txmy.dev.language.message;

import org.bukkit.command.CommandSender;

public class EmptyLanguageMessage extends LanguageMessage {
    @Override
    public Object get() {
        return "empty";
    }

    @Override
    public void send(CommandSender player) {

    }
}
