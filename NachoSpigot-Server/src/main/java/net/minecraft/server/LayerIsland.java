package net.minecraft.server;

import java.util.Arrays;

public class LayerIsland extends GenLayer {

    private World world;

    public LayerIsland(long var1, World world) {
        super(var1);
        this.world = world;
    }

    public int[] a(int var1, int var2, int var3, int var4) {
        int[] var5 = IntCache.a(var3 * var4);

        if (!world.generatorConfig.oceans) {
            Arrays.fill(var5, 1);
            return var5;
        }

        for(int var6 = 0; var6 < var4; ++var6) {
            for(int var7 = 0; var7 < var3; ++var7) {
                this.a((long)(var1 + var7), (long)(var2 + var6));
                var5[var7 + var6 * var3] = this.a(10) == 0 ? 1 : 0;
            }
        }

        if (var1 > -var3 && var1 <= 0 && var2 > -var4 && var2 <= 0) {
            var5[-var1 + -var2 * var3] = 1;
        }

        return var5;
    }

}
