package dev.cobblesword.nachospigot.protocol;

import net.minecraft.server.Packet;
import net.minecraft.server.PlayerConnection;

public interface PacketHandler {
  default boolean onReceivedPacket(PlayerConnection playerConnection, Packet<?> packet) {
    return true;
  }
  default boolean onSentPacket(PlayerConnection connection, Packet<?> packet) {
    return true;
  }
}
