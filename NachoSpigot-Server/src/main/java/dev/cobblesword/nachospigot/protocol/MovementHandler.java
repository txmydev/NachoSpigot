package dev.cobblesword.nachospigot.protocol;

import net.minecraft.server.PacketPlayInFlying;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface MovementHandler {
  default boolean updateLocation(Player player, Location to, Location from, PacketPlayInFlying packet) {
    return true;
  }
  default boolean updateRotation(Player player, Location to, Location from, PacketPlayInFlying packet) {
    return true;
  }
}
