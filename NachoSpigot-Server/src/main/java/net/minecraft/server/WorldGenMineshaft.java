package net.minecraft.server;

import java.util.Iterator;
import java.util.Map;

public class WorldGenMineshaft extends StructureGenerator {

    private double d = 0.004D;

    public WorldGenMineshaft() {
        this(1.0F);
    }

    public WorldGenMineshaft(float multiplier) {
        this.d *= multiplier;
    }

    public String a() {
        return "Mineshaft";
    }

    public WorldGenMineshaft(Map<String, String> var1) {
        Iterator var2 = var1.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry var3 = (Map.Entry)var2.next();
            if (((String)var3.getKey()).equals("chance")) {
                this.d = MathHelper.a((String)var3.getValue(), this.d);
            }
        }

    }

    protected boolean a(int var1, int var2) {
        return this.b.nextDouble() < this.d && this.b.nextInt(80) < Math.max(Math.abs(var1), Math.abs(var2));
    }

    protected StructureStart b(int var1, int var2) {
        return new WorldGenMineshaftStart(this.c, this.b, var1, var2);
    }

}
