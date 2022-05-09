package net.minecraft.server;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class PacketPlayOutScoreboardTeam implements Packet<PacketListenerPlayOut> {
    // Nacho Spigot - Change Field names

    public String name = "";
    public String displayName = "";
    public String prefix = "";
    public String suffix = "";
    public String nameTagVisibility;
    public int chatFormat;
    public Collection<String> playerNames;
    public int action;
    public int optionData;

    public PacketPlayOutScoreboardTeam() {
        this.nameTagVisibility = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
        this.chatFormat = -1;
        this.playerNames = Lists.newArrayList();
    }

    public PacketPlayOutScoreboardTeam(ScoreboardTeam var1, int var2) {
        this.nameTagVisibility = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
        this.chatFormat = -1;
        this.playerNames = Lists.newArrayList();
        this.name = var1.getName();
        this.action = var2;
        if (var2 == 0 || var2 == 2) {
            this.displayName = var1.getDisplayName();
            this.prefix = var1.getPrefix();
            this.suffix = var1.getSuffix();
            this.optionData = var1.packOptionData();
            this.nameTagVisibility = var1.getNameTagVisibility().e;
            this.chatFormat = var1.l().b();
        }

        if (var2 == 0) {
            this.playerNames.addAll(var1.getPlayerNameSet());
        }

    }

    public PacketPlayOutScoreboardTeam(ScoreboardTeam var1, Collection<String> var2, int var3) {
        this.nameTagVisibility = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
        this.chatFormat = -1;
        this.playerNames = Lists.newArrayList();
        if (var3 != 3 && var3 != 4) {
            throw new IllegalArgumentException("Method must be join or leave for player constructor");
        } else if (var2 != null && !var2.isEmpty()) {
            this.action = var3;
            this.name = var1.getName();
            this.playerNames.addAll(var2);
        } else {
            throw new IllegalArgumentException("Players cannot be null/empty");
        }
    }

    public void a(PacketDataSerializer var1) throws IOException {
        this.name = var1.readUtf(16);
        this.action = var1.readByte();
        if (this.action == 0 || this.action == 2) {
            this.displayName = var1.readUtf(32);
            this.prefix = var1.readUtf(16);
            this.suffix = var1.readUtf(16);
            this.optionData = var1.readByte();
            this.nameTagVisibility = var1.readUtf(32);
            this.chatFormat = var1.readByte();
        }

        if (this.action == 0 || this.action == 3 || this.action == 4) {
            int var2 = var1.readVarInt();

            for(int var3 = 0; var3 < var2; ++var3) {
                this.playerNames.add(var1.readUtf(40));
            }
        }

    }

    public void b(PacketDataSerializer var1) throws IOException {
        var1.a(this.name);
        var1.writeByte(this.action);
        if (this.action == 0 || this.action == 2) {
            var1.a(this.displayName);
            var1.a(this.prefix);
            var1.a(this.suffix);
            var1.writeByte(this.optionData);
            var1.a(this.nameTagVisibility);
            var1.writeByte(this.chatFormat);
        }

        if (this.action == 0 || this.action == 3 || this.action == 4) {
            var1.b(this.playerNames.size()); // Nacho - deobf
            Iterator var2 = this.playerNames.iterator();

            while(var2.hasNext()) {
                String var3 = (String)var2.next();
                var1.a(var3);
            }
        }

    }

    public void a(PacketListenerPlayOut var1) {
        var1.a(this);
    }
}

