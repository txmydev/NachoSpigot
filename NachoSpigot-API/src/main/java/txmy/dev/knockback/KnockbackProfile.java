package txmy.dev.knockback;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class KnockbackProfile {
    public String title;
    public List<KnockbackValue> values = new ArrayList<>();

    public KnockbackValue<Boolean> onePointOneKnockback = new KnockbackValue<>("1point1kb", "1.1 knockback", Boolean.class, false);
    public KnockbackValue<Boolean> limitVertical = new KnockbackValue<>("limitvert", "Limit Vertical", Boolean.class, true);
    public KnockbackValue<Double> ylimit = new KnockbackValue<>("ylimit", "Vertical Limit", Double.class, 1.2D);

    public KnockbackValue<Double> horizontal = new KnockbackValue<>("horizontal", "Horizontal", Double.class, 0.33D);
    public KnockbackValue<Double> vertical = new KnockbackValue<>("vertical", "Vertical", Double.class, 0.36D);

    public KnockbackValue<Boolean> inheritH = new KnockbackValue<>("inherith", "Inherit Horizontal", Boolean.class, true);
    public KnockbackValue<Boolean> inheritV = new KnockbackValue<>("inheritv", "Inherit Vertical", Boolean.class, false);

    public KnockbackValue<Double> frictionH = new KnockbackValue<>("ihstrh", "Inherit Strength Horizontal", Double.class, 1.0D);
    public KnockbackValue<Double> frictionV = new KnockbackValue<>("ihstrv", "Inherit Strength Vertical", Double.class, 1.0D);

    public KnockbackValue<Double> groundH = new KnockbackValue<>("groundh", "Ground Horizontal Multiplier", Double.class, 1.1D);
    public KnockbackValue<Double> groundV = new KnockbackValue<>("groundv", "Ground Vertical Multiplier", Double.class, 1.0D);

    public KnockbackValue<Double> sprintH = new KnockbackValue<>("sprinth", "Sprint Horizontal Multiplier", Double.class, 1.2D);
    public KnockbackValue<Double> sprintV = new KnockbackValue<>("sprintv", "Sprint Vertical Multiplier", Double.class, 1.0D);

    public KnockbackValue<Double> bowH = new KnockbackValue<>("bowh", "Bow Horizontal Multiplier", Double.class, 1.2D);
    public KnockbackValue<Double> bowV = new KnockbackValue<>("bowv", "Bow Vertical Multiplier", Double.class, 1.0D);

    public KnockbackValue<Double> rodH = new KnockbackValue<>("rodh", "Rod Horizontal Multiplier", Double.class, 1.2D);
    public KnockbackValue<Double> rodV = new KnockbackValue<>("rodv", "Rod Vertical Multiplier", Double.class, 1.0D);

    public KnockbackValue<Integer> hitDelay = new KnockbackValue<>("nodamageticks", "No Damage Ticks", Integer.class, 20);

    public KnockbackValue<Boolean> comboMode = new KnockbackValue<>("combo", "Combo Mode", Boolean.class, false);
    public KnockbackValue<Integer> comboTicks = new KnockbackValue<>("comboticks", "Combo Ticks", Integer.class, 10);
    public KnockbackValue<Double> comboVelocity = new KnockbackValue<>("combovelocity", "Combo Velocity", Double.class, -0.05D);
    public KnockbackValue<Double> comboHeight = new KnockbackValue<>("comboheight", "Combo Height", Double.class, 2.5D);

    public KnockbackValue<Boolean> stopSprint = new KnockbackValue<>("stopsprint", "StopSprint", Boolean.class, true);
    public KnockbackValue<Double> slowdown = new KnockbackValue<>("slowdown", "Attacker Slowdown(AutoWtap)", Double.class, 0.6D);

    public KnockbackValue<Double> potionFallSpeed = new KnockbackValue<>("potionfall", "Potion Fall Speed", Double.class, 0.05D);
    public KnockbackValue<Double> potionThrowMultiplier = new KnockbackValue<>("potionmultiplier", "Potion Multiplier", Double.class, 0.5D);
    public KnockbackValue<Double> potionThrowOffset = new KnockbackValue<>("potionoffset", "Potion Throw Offset", Double.class, -10.0D);

    public KnockbackProfile(String title) {
        this.title = title;
        load();
    }

    public void load() {
        try {
            values.clear();
            for (Field f : getClass().getFields()) {
                if (f.getType() == KnockbackValue.class) {
                    values.add((KnockbackValue) f.get(this));
                }
            }
            //Collections.reverse(values);
            File file = new File("knockback" + File.separator + title + ".yml");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            YamlConfiguration config = new YamlConfiguration();
            config.load(file);
            for (KnockbackValue value : values) {
                Object val = config.get(value.id);
                if (val == null) {
                    config.set(value.id, value.value);
                } else {
                    value.value = val;
                }
            }
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            File file = new File("knockback" + File.separator + title + ".yml");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            YamlConfiguration config = new YamlConfiguration();
            for (KnockbackValue value : values) {
                config.set(value.id, value.value);
            }
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
