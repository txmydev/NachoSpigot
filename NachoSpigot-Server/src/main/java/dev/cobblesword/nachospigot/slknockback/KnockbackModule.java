package dev.cobblesword.nachospigot.slknockback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class KnockbackModule {
    public static KnockbackModule INSTANCE = new KnockbackModule();

    public Map<String, SLKnockbackProfile> profiles = new HashMap<>();

    public KnockbackModule() {
        File knockback = new File("config/knockback");
        if (!knockback.exists())
            knockback.mkdir();
        File[] files = knockback.listFiles();
        if (files != null) {
            byte b;
            int i;
            File[] arrayOfFile;
            for (i = (arrayOfFile = files).length, b = 0; b < i; ) {
                File file = arrayOfFile[b];
                this.profiles.put(file.getName().replace(".yml", ""), new SLKnockbackProfile(file.getName().replace(".yml", "")));
                b++;
            }
        }
        if (!this.profiles.containsKey("default"))
            this.profiles.put("default", new SLKnockbackProfile("default"));
    }

    public static SLKnockbackProfile getDefault() {
        return INSTANCE.profiles.get("default");
    }
}


