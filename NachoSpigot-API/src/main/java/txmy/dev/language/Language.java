package txmy.dev.language;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public class Language {

    private final String language;
    private final LanguageData data;
    private final boolean defaultLang;

    public static Language of(String language, LanguageData data, boolean defaultLang) {
        return new Language(language, data, defaultLang);
    }
}
