package txmy.dev.language;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import txmy.dev.language.util.LanguageTimeUtil;

@RequiredArgsConstructor @Getter
public enum LanguageEnum {

    ENGLISH("English", LanguageTimeUtil.of(
            "second",
            "minute",
            "hour",
            "day",
            "week",
            "month",
            "year"
    )),
    SPANISH("Spanish",
            LanguageTimeUtil.of(
                    "segundo",
                    "minuto",
                    "hora",
                    "dia",
                    "semana",
                    "mes",
                    "año"
            )),
    PORTUGUESE("Portuguese", null);

    private final String locale;
    private final LanguageTimeUtil timeUtil;

}
