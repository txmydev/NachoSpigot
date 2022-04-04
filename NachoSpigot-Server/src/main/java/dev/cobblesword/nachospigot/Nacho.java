package dev.cobblesword.nachospigot;

import dev.cobblesword.nachospigot.commands.KnockbackCommand;
import dev.cobblesword.nachospigot.hitdetection.LagCompensator;
import dev.cobblesword.nachospigot.protocol.MovementHandler;
import me.elier.nachospigot.config.NachoConfig;
import xyz.sculas.nacho.anticrash.AntiCrash;
import xyz.sculas.nacho.async.AsyncExplosions;
import dev.cobblesword.nachospigot.protocol.PacketHandler;
import net.minecraft.server.MinecraftServer;
import dev.cobblesword.nachospigot.commands.SetMaxSlotCommand;
import dev.cobblesword.nachospigot.commands.SpawnMobCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Sets;
import java.util.Set;

public class Nacho {
    public static final Logger LOGGER = LogManager.getLogger(Nacho.class);
    private static Nacho INSTANCE;

    private final Set<PacketHandler> packetHandlers = Sets.newConcurrentHashSet();
    private final Set<MovementHandler> movementHandlers = Sets.newConcurrentHashSet();

    private final LagCompensator lagCompensator;

    public Nacho() {
        INSTANCE = this;

        AsyncExplosions.initExecutor(NachoConfig.useFixedPoolForTNT, NachoConfig.fixedPoolSize);

        lagCompensator = new LagCompensator();

        if(NachoConfig.enableAntiCrash) {
            this.packetHandlers.add(new AntiCrash());
        }
    }

    public static Nacho get() {
        return INSTANCE == null ? new Nacho() : INSTANCE;
    }

    public void registerCommands() {
        SetMaxSlotCommand setMaxSlotCommand = new SetMaxSlotCommand("sms"); //[Nacho-0021] Add setMaxPlayers within Bukkit.getServer() and SetMaxSlot Command
        SpawnMobCommand spawnMobCommand = new SpawnMobCommand("spawnmob");
        KnockbackCommand knockbackCommand = new KnockbackCommand("kb");
        MinecraftServer.getServer().server.getCommandMap().register(setMaxSlotCommand.getName(), "ns", setMaxSlotCommand);
        MinecraftServer.getServer().server.getCommandMap().register(spawnMobCommand.getName(), "ns", spawnMobCommand);
        MinecraftServer.getServer().server.getCommandMap().register(knockbackCommand.getName(), "ns", knockbackCommand);
    }

    public void registerPacketListener(PacketHandler packetHandler) {
        this.packetHandlers.add(packetHandler);
    }

    public void unregisterPacketListener(PacketHandler packetHandler) {
        this.packetHandlers.remove(packetHandler);
    }

    public Set<PacketHandler> getPacketListeners() { return packetHandlers; }

    public void registerMovementListener(MovementHandler movementHandler) {
        this.movementHandlers.add(movementHandler);
    }

    public void unregisterMovementListener(MovementHandler movementHandler) {
        this.movementHandlers.remove(movementHandler);
    }

    public Set<MovementHandler> getMovementListeners() { return movementHandlers; }

    public LagCompensator getLagCompensator() {
        return lagCompensator;
    }
}
