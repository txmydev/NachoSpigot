package txmy.dev.language.message;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@AllArgsConstructor
public class SingleLanguageMessage extends LanguageMessage<String> {

    private final String text;

    public static SingleLanguageMessage raw(String text) {
        return new SingleLanguageMessage(text);
    }

    public static SingleLanguageMessage fromString(String text) {
        return SingleLanguageMessage.raw(text);
    }

    public void send(CommandSender player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

    @Override
    public void send(CommandSender player, Object... args) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format(text, args)));
    }

    @Override
    public String get() {
        return text;
    }
}
