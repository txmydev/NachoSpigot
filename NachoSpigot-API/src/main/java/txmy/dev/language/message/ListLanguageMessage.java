package txmy.dev.language.message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

public class ListLanguageMessage extends LanguageMessage<List<String>> {

    private List<String> lines;

    public ListLanguageMessage(List<String> lines) {
        this.lines = lines.stream().map(str -> ChatColor.translateAlternateColorCodes('&', str)).collect(Collectors.toList());
    }

    public ListLanguageMessage(ListLanguageMessage other, Object[][] args) {
        this.lines = other.get(args);
    }

    public static LanguageMessage<List<String>> from(List<String> value) {
        return new ListLanguageMessage(value);
    }

    public static ListLanguageMessage fromAndReplace(ListLanguageMessage other, Object[][] args) {
        return new ListLanguageMessage(other, args);
    }


    @Override
    public List<String> get() {
        return lines;
    }

    @Override
    public void send(CommandSender player) {
        lines.forEach(line -> player.sendMessage(ChatColor.translateAlternateColorCodes('&',line)));
    }

    @Override
    public void send(CommandSender player, Object... args) {
        throw new UnsupportedOperationException("You should use send(CommandSender, Object[][] args)");
    }

    @Override
    public void send(CommandSender player, Object[][] args) {
        for(int i = 0; i < args.length; i++) {
            Object[] argArray = args[i];
            lines.set(i, String.format(lines.get(i), argArray));
        }

        send(player);
    }

    @Override
    public List<String> get(Object[][] args) {
        for(int i = 0; i < args.length; i++) {
            Object[] argArray = args[i];
            lines.set(i, String.format(lines.get(i), argArray));
        }

        return lines;
    }

    @Override
    public List<String> get(Object[] args) {
        return lines.stream().map(line -> String.format(line, args)).collect(Collectors.toList());
    }
}
