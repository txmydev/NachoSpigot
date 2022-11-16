package txmy.dev.language.message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
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

    private List<String> replace(Object[][] args) {
        List<String> copy = new ArrayList<>(lines);

        for(int i = 0; i < args.length; i++) {
            Object[] argArray = args[i];
            copy.set(i, String.format(copy.get(i), argArray));
        }

        return copy;
    }

    @Override
    public void send(CommandSender player, Object[][] args) {
        List<String> copy = replace(args);

        sendCopy(player, copy);
    }

    private void sendCopy(CommandSender player, List<String> list) {
        list.forEach(line -> player.sendMessage(ChatColor.translateAlternateColorCodes('&',line)));
    }

    @Override
    public List<String> get(Object[][] args) {
        // We create a copy if not it
        return replace(args);
    }

    @Override
    public List<String> get(Object[] args) {
        return lines.stream().map(line -> String.format(line, args)).collect(Collectors.toList());
    }
}
