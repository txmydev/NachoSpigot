package txmy.dev.language.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LanguageTimeUtil {

    private final String secondsStr, minutesStr, hoursStr, daysStr, weekStr, monthsStr, yearsStr;

    public static LanguageTimeUtil of(String seconds,
                                      String minutes,
                                      String hours,
                                      String days,
                                      String week,
                                      String months,
                                      String years) {
        return new LanguageTimeUtil(seconds, minutes, hours, days, week, months, years);
    }

    public String millisToRoundedTime(long millis) {
        millis += 1L;

        long seconds = millis / 1000L;
        long minutes = seconds / 60L;
        long hours = minutes / 60L;
        long days = hours / 24L;
        long weeks = days / 7L;
        long months = weeks / 4L;
        long years = months / 12L;

        if (years > 0) {
            return years + " " + yearsStr + (years == 1 ? "" : "s");
        } else if (months > 0) {
            return months + " " + monthsStr + (months == 1 ? "" : "s");
        } else if (weeks > 0) {
            return weeks + " " + weekStr + (weeks == 1 ? "" : "s");
        } else if (days > 0) {
            return days + " " + daysStr + (days == 1 ? "" : "s");
        } else if (hours > 0) {
            return hours + " " + hoursStr + (hours == 1 ? "" : "s");
        } else if (minutes > 0) {
            return minutes + " " + minutesStr + (minutes == 1 ? "" : "s");
        } else {
            return seconds + " " + secondsStr + (seconds == 1 ? "" : "s");
        }
    }

}
