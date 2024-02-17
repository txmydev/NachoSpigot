package txmy.dev.knockback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class KnockbackModule {
    public static KnockbackModule INSTANCE = new KnockbackModule();
    public Map<String, KnockbackProfile> profiles = new HashMap<>();

    public KnockbackModule() {
        File knockback = new File("knockback");
        if (!knockback.exists()) knockback.mkdir();
        File[] files = knockback.listFiles();
        if (files != null) {
            for (File file : files) {
                profiles.put(file.getName().replace(".yml", ""), new KnockbackProfile(file.getName().replace(".yml", "")));
            }
        }
        if (!profiles.containsKey("default")) profiles.put("default", new KnockbackProfile("default"));
    }

    public static KnockbackProfile getDefault() {
        return INSTANCE.profiles.get("default");
    }
}
