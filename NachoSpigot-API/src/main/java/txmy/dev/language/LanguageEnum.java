package txmy.dev.language;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public enum LanguageEnum {

    ENGLISH("English"),
    SPANISH("Spanish"),
    PORTUGUESE("Portuguese");

    private final String locale;

}
