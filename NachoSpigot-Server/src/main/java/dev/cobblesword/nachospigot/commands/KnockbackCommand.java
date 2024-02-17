package dev.cobblesword.nachospigot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import txmy.dev.knockback.KnockbackModule;
import txmy.dev.knockback.KnockbackProfile;
import txmy.dev.knockback.KnockbackValue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KnockbackCommand extends Command {
    public KnockbackCommand() {
        super("knockback", "Modify spigot knockback", "/knockback <view|list|set|add|delete|edit>", Arrays.asList("kb"));
        setPermission("bukkit.command.knockback");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "list": {
                    sender.sendMessage("§7Knockback profiles: §c" + Arrays.stream(KnockbackModule.INSTANCE.profiles.keySet().toArray(new String[0])).collect(Collectors.joining("§7, §c")));
                    break;
                }
                case "view": {
                    if (args.length == 2) {
                        KnockbackProfile profile = KnockbackModule.INSTANCE.profiles.get(args[1].toLowerCase());
                        if (profile != null) {
                            sender.sendMessage("§7Viewing profile §c" + profile.title + "§7:");
                            for (KnockbackValue value : profile.values) {
                                sender.sendMessage("  §f" + value.name + ": §c" + value.value);
                            }
                        } else sender.sendMessage("§cInvalid profile: " + args[1].toLowerCase());
                    } else sender.sendMessage("§c/knockback view <profile>");
                    break;
                }
                case "use": {
                    if (args.length == 2) {
                        KnockbackProfile profile = KnockbackModule.INSTANCE.profiles.get(args[1].toLowerCase());
                        if (profile != null) {
                            if (sender instanceof Player) {
                                Player player = (Player) sender;
                                for (Player other : player.getWorld().getPlayers()) {
                                    ((CraftPlayer) other).getHandle().setKnockbackProfile(profile);
                                }
                            }
                            sender.sendMessage("§7Updated knockback profile to §c" + profile.title);
                        } else sender.sendMessage("§cInvalid profile: " + args[1].toLowerCase());
                    } else sender.sendMessage("§c/knockback use <profile>");
                    break;
                }
                case "add": {
                    if (args.length == 2) {
                        if (!KnockbackModule.INSTANCE.profiles.containsKey(args[1].toLowerCase())) {
                            KnockbackModule.INSTANCE.profiles.put(args[1].toLowerCase(), new KnockbackProfile(args[1].toLowerCase()));
                            sender.sendMessage("§7KNockback profile created §c" + args[1].toLowerCase());
                        } else sender.sendMessage("§cKnockback profile already exists.");
                    } else sender.sendMessage("§c/knockback add <profile>");
                    break;
                }
                case "remove":
                case "delete": {
                    if (args.length == 2) {
                        if (!args[1].equalsIgnoreCase("default")) {
                            if (KnockbackModule.INSTANCE.profiles.containsKey(args[1].toLowerCase())) {
                                new File("knockback" + File.separator + args[1].toLowerCase() + ".yml").delete();
                                KnockbackModule.INSTANCE.profiles.remove(args[1].toLowerCase());
                                sender.sendMessage("§7KNockback profile deleted §c" + args[1].toLowerCase());
                            } else sender.sendMessage("§cKnockback profile doesn't exist.");
                        } else sender.sendMessage("§cCannot delete the default profile.");
                    } else sender.sendMessage("§c/knockback delete <profile>");
                    break;
                }
                case "edit": {
                    if (args.length >= 2) {
                        KnockbackProfile profile = KnockbackModule.INSTANCE.profiles.get(args[1].toLowerCase());
                        if (profile != null) {
                            List<String> fields = new ArrayList<>();
                            for (KnockbackValue value : profile.values) fields.add(value.id);
                            if (args.length >= 3) {
                                if (args.length == 4) {
                                    for (KnockbackValue value : profile.values) {
                                        if (value.id.equalsIgnoreCase(args[2])) {
                                            if (value.type == Integer.class) {
                                                try {
                                                    value.value = Integer.parseInt(args[3]);
                                                    sender.sendMessage("§7Set profile §c" + profile.title + " §7field §c" + value.name + " §7to §c" + value.value);
                                                    profile.save();
                                                } catch (NumberFormatException e) {
                                                    sender.sendMessage("§cInvalid number");
                                                }
                                            } else if (value.type == Double.class) {
                                                try {
                                                    value.value = Double.parseDouble(args[3]);
                                                    sender.sendMessage("§7Set profile §c" + profile.title + " §7field §c" + value.name + " §7to §c" + value.value);
                                                    profile.save();
                                                } catch (NumberFormatException e) {
                                                    sender.sendMessage("§cInvalid number");
                                                }
                                            } else if (value.type == Boolean.class) {
                                                try {
                                                    value.value = Boolean.parseBoolean(args[3]);
                                                    sender.sendMessage("§7Set profile §c" + profile.title + " §7field §c" + value.name + " §7to §c" + value.value);
                                                    profile.save();
                                                } catch (NumberFormatException e) {
                                                    sender.sendMessage("§cInvalid number");
                                                }
                                            }
                                            return true;
                                        }
                                    }
                                    sender.sendMessage("§7Available fields: §c" + String.join(",", fields.toArray(new String[0])));
                                } else sender.sendMessage("§c/knockback edit <profile> <field> <value>");
                            } else sender.sendMessage("§7Available fields: §c" + String.join(",", fields.toArray(new String[0])));
                        } else sender.sendMessage("§cInvalid profile: " + args[1].toLowerCase());
                    } else sender.sendMessage("§c/knockback edit <profile> <field> <value>");
                    break;
                }
                default: {
                    sender.sendMessage("§c" + usageMessage);
                    break;
                }
            }
        } else {
            sender.sendMessage("§c" + usageMessage);
        }
        return true;
    }
}
