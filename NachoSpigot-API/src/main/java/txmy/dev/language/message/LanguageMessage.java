package txmy.dev.language.message;

import org.bukkit.command.CommandSender;
import txmy.dev.language.LanguageData;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LanguageMessage<T> {

    // Use regex to identify this kind of stuff
    // Example {time:13513517358}
    private static Pattern TIME_VARIABLE_PATTERN = Pattern.compile("([{]time:)([0-9]*)([}])");

    public static Object[] findAndReplaceTimeVariables(LanguageData language, Object... replaceablesOr) {
        Object[] replaceables = Arrays.copyOf(replaceablesOr, replaceablesOr.length);

        for(int i = 0; i < replaceables.length; i++ ){
            Object obj = replaceables[i];
            if(!(obj instanceof String)) continue;

            String str = (String) obj;

            Matcher matcher = TIME_VARIABLE_PATTERN.matcher(str);
            if (matcher.matches()) {
                str = language.formatTime(Long.parseLong(matcher.group(2)));
                replaceables[i] = str;
            }
        }

        return replaceables;
    }

    public abstract T get();


    public abstract void send(CommandSender player);
    public void send(CommandSender sender, Object... args) {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " tried to call method send(Player, Object[] args) without implementing it.");
    }

    public void send(CommandSender sender, Object[][] args){
        throw new UnsupportedOperationException(getClass().getSimpleName() + " tried to call method send(Player, Object[][] args) without implementing it.");
    }

    public T get(Object... args) {
        return null;
    }

    public T get(Object[][] args) {
        return null;
    }
}
