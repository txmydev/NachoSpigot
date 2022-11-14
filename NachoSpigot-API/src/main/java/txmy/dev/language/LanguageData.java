package txmy.dev.language;

import lombok.Getter;
import txmy.dev.language.message.LanguageMessage;
import txmy.dev.language.message.ListLanguageMessage;
import txmy.dev.language.message.SingleLanguageMessage;
import txmy.dev.language.util.LanguageTimeUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class LanguageData {

    // key is the identifier, the value = text value
    private final Map<String, LanguageMessage<?>> values;
    private LanguageTimeUtil timeUtil;

    public LanguageData() {
        this.values = new ConcurrentHashMap<>();
    }


    public LanguageData add(String key, List<String> value) {
        this.values.putIfAbsent(key, ListLanguageMessage.from(value));
        return this;
    }

    public LanguageData add(String key, String value) {
        this.values.putIfAbsent(key, SingleLanguageMessage.fromString(value));
        return this;
    }

    public LanguageData add(String[] translation) {
        String key = translation[0];
        String text = translation[1];

        this.values.putIfAbsent(key, SingleLanguageMessage.fromString(text));

        return this;
    }

    public LanguageData add(String key, LanguageMessage<?> message) {
        this.values.putIfAbsent(key, message);
        return this;
    }


    public LanguageData setTimeUtil(LanguageTimeUtil util) {
        this.timeUtil = util;

        return this;
    }

    public LanguageMessage<String> getString(String key) {
        return values.containsKey(key) ?
                (LanguageMessage<String>) values.get(key) :
                SingleLanguageMessage.fromString("NULL: " + key);
    }

    public LanguageMessage<String> getString(String key, String defaultValue) {
        return values.containsKey(key) ?
                (LanguageMessage<String>) values.get(key) :
                SingleLanguageMessage.fromString(defaultValue);
    }

    public LanguageMessage<List<String>> getList(String key) {
        return values.containsKey(key) ?
                (LanguageMessage<List<String>>) values.get(key) :
                ListLanguageMessage.from(Collections.singletonList("NULL: " + key));
    }
    public LanguageMessage<List<String>> getList(String key, String defaultValue) {
        return values.containsKey(key) ?
                (LanguageMessage<List<String>>) values.get(key) :
                ListLanguageMessage.from(Arrays.asList(defaultValue));
    }

    public static LanguageData builder() {
        return new LanguageData();
    }

    public String formatTime(long l1) {
        return timeUtil.millisToRoundedTime(l1);
    }

    public LanguageMessage get(String key) {
        return values.get(key);
    }
}
