package txmy.dev.language;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public enum LanguageManager {

    INSTANCE;

    private final Map<String, Language> languages = new HashMap<>();

    public void load() {
        AtomicBoolean def = new AtomicBoolean(true);

        Arrays.stream(LanguageEnum.values()).forEach(lang -> {
            addLanguage(lang.getLocale(), new LanguageData().setTimeUtil(lang.getTimeUtil()), def.getAndSet(false));
        });
    }

    public void addLanguage(String name, LanguageData data, boolean def) {
        this.languages.putIfAbsent(name, Language.of(name, data, def));
    }

    public Language getLanguage(String name) {
        return languages.get(name);
    }

    public Language getLanguage(LanguageEnum language) {
        return languages.get(language.getLocale());
    }

    public Language getDefault() {
        return languages.values().stream().filter(Language::isDefaultLang).findFirst().orElse(null);
    }
}
