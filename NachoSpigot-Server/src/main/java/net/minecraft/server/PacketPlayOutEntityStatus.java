package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutEntityStatus implements Packet<PacketListenerPlayOut> {
    private int a;
    private byte b;

    public PacketPlayOutEntityStatus() {
    }

    public PacketPlayOutEntityStatus(int id, byte var2) {
        this.a = id;
        this.b = var2;
    }

    public PacketPlayOutEntityStatus(Entity var1, byte var2) {
        this.a = var1.getId();
        this.b = var2;
    }

    public void a(PacketDataSerializer var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readByte();
    }

    public void b(PacketDataSerializer var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeByte(this.b);
    }

    public void a(PacketListenerPlayOut var1) {
        var1.a(this);
    }
}
