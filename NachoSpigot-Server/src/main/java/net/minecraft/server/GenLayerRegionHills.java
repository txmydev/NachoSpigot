package net.minecraft.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenLayerRegionHills extends GenLayer {
    private static final Logger c = LogManager.getLogger();
    private GenLayer d;
    private World world;

    public GenLayerRegionHills(long var1, GenLayer var3, GenLayer var4, World world) {
        super(var1);
        this.a = var3;
        this.d = var4;
        this.world = world;
    }

    public int[] a(int var1, int var2, int var3, int var4) {
        int[] var5 = this.a.a(var1 - 1, var2 - 1, var3 + 2, var4 + 2);
        int[] var6 = this.d.a(var1 - 1, var2 - 1, var3 + 2, var4 + 2);
        int[] var7 = IntCache.a(var3 * var4);

        for(int var8 = 0; var8 < var4; ++var8) {
            for(int var9 = 0; var9 < var3; ++var9) {
                this.a((long)(var9 + var1), (long)(var8 + var2));
                int var10 = var5[var9 + 1 + (var8 + 1) * (var3 + 2)];
                int var11 = var6[var9 + 1 + (var8 + 1) * (var3 + 2)];
                boolean var12 = (var11 - 2) % 29 == 0;
                if (var10 > 255) {
                    c.debug("old! " + var10);
                }

                if (var10 != 0 && var11 >= 2 && (var11 - 2) % 29 == 1 && var10 < 128) {
                    if (BiomeBase.getBiome(var10 + 128) != null) {
                        var7[var9 + var8 * var3] = var10 + 128;
                    } else {
                        var7[var9 + var8 * var3] = var10;
                    }
                } else if (this.a(3) != 0 && !var12) {
                    var7[var9 + var8 * var3] = var10;
                } else {
                    int k1 = var10;
                    int i2;
                    if (k1 == BiomeBase.DESERT.id && this.world.generatorConfig.biomeDesertHills) {
                        i2 = BiomeBase.DESERT_HILLS.id;
                    } else if (k1 == BiomeBase.FOREST.id && this.world.generatorConfig.biomeForestHills) {
                        i2 = BiomeBase.FOREST_HILLS.id;
                    } else if (k1 == BiomeBase.BIRCH_FOREST.id && this.world.generatorConfig.biomeBirchForestHills) {
                        i2 = BiomeBase.BIRCH_FOREST_HILLS.id;
                    } else if (k1 == BiomeBase.ROOFED_FOREST.id && this.world.generatorConfig.biomePlains) {
                        i2 = BiomeBase.PLAINS.id;
                    } else if (k1 == BiomeBase.TAIGA.id && this.world.generatorConfig.biomeTaigaHills) {
                        i2 = BiomeBase.TAIGA_HILLS.id;
                    } else if (k1 == BiomeBase.MEGA_TAIGA.id && this.world.generatorConfig.biomeMegaTaigaHills) {
                        i2 = BiomeBase.MEGA_TAIGA_HILLS.id;
                    } else if (k1 == BiomeBase.COLD_TAIGA.id && this.world.generatorConfig.biomeColdTaigaHills) {
                        i2 = BiomeBase.COLD_TAIGA_HILLS.id;
                    } else if (k1 == BiomeBase.PLAINS.id) {
                        if (this.a(3) == 0 && this.world.generatorConfig.biomeForestHills) {
                            i2 = BiomeBase.FOREST_HILLS.id;
                        } else if (this.world.generatorConfig.biomeForest) {
                            i2 = BiomeBase.FOREST.id;
                        }
                    } else if (k1 == BiomeBase.ICE_PLAINS.id && this.world.generatorConfig.biomeIceMountains) {
                        i2 = BiomeBase.ICE_MOUNTAINS.id;
                    } else if (k1 == BiomeBase.JUNGLE.id && this.world.generatorConfig.biomeJungleHills) {
                        i2 = BiomeBase.JUNGLE_HILLS.id;
                    } else if (k1 == BiomeBase.OCEAN.id) {
                        i2 = BiomeBase.DEEP_OCEAN.id;
                    } else if (k1 == BiomeBase.EXTREME_HILLS.id && this.world.generatorConfig.biomeExtremeHillsPlus) {
                        i2 = BiomeBase.EXTREME_HILLS_PLUS.id;
                    } else if (k1 == BiomeBase.SAVANNA.id && this.world.generatorConfig.biomeSavannaPlateau) {
                        i2 = BiomeBase.SAVANNA_PLATEAU.id;
                    } else if (a(k1, BiomeBase.MESA_PLATEAU_F.id) && this.world.generatorConfig.biomeMesa) {
                        i2 = BiomeBase.MESA.id;
                    } else if (k1 == BiomeBase.DEEP_OCEAN.id && this.a(3) == 0) {
                        int j2 = this.a(2);
                        if (j2 == 0 && this.world.generatorConfig.biomePlains) {
                            i2 = BiomeBase.PLAINS.id;
                        } else if (this.world.generatorConfig.biomeForest) {
                            i2 = BiomeBase.FOREST.id;
                        }
                    }

                  /*  if (var10 == BiomeBase.DESERT.id ) {
                        k1 = BiomeBase.DESERT_HILLS.id;
                    } else if (var10 == BiomeBase.FOREST.id) {
                        k1 = BiomeBase.FOREST_HILLS.id;
                    } else if (var10 == BiomeBase.BIRCH_FOREST.id) {
                        k1 = BiomeBase.BIRCH_FOREST_HILLS.id;
                    } else if (var10 == BiomeBase.ROOFED_FOREST.id) {
                        k1 = BiomeBase.PLAINS.id;
                    } else if (var10 == BiomeBase.TAIGA.id) {
                        k1 = BiomeBase.TAIGA_HILLS.id;
                    } else if (var10 == BiomeBase.MEGA_TAIGA.id) {
                        k1 = BiomeBase.MEGA_TAIGA_HILLS.id;
                    } else if (var10 == BiomeBase.COLD_TAIGA.id) {
                        k1 = BiomeBase.COLD_TAIGA_HILLS.id;
                    } else if (var10 == BiomeBase.PLAINS.id) {
                        if (this.a(3) == 0) {
                            k1 = BiomeBase.FOREST_HILLS.id;
                        } else {
                            k1 = BiomeBase.FOREST.id;
                        }
                    } else if (var10 == BiomeBase.ICE_PLAINS.id) {
                        k1 = BiomeBase.ICE_MOUNTAINS.id;
                    } else if (var10 == BiomeBase.JUNGLE.id) {
                        k1 = BiomeBase.JUNGLE_HILLS.id;
                    } else if (var10 == BiomeBase.OCEAN.id) {
                        k1 = BiomeBase.DEEP_OCEAN.id;
                    } else if (var10 == BiomeBase.EXTREME_HILLS.id) {
                        k1 = BiomeBase.EXTREME_HILLS_PLUS.id;
                    } else if (var10 == BiomeBase.SAVANNA.id) {
                        k1 = BiomeBase.SAVANNA_PLATEAU.id;
                    } else if (a(var10, BiomeBase.MESA_PLATEAU_F.id)) {
                        k1 = BiomeBase.MESA.id;
                    } else if (var10 == BiomeBase.DEEP_OCEAN.id && this.a(3) == 0) {
                        i2 = this.a(2);
                        if (i2 == 0) {
                            k1 = BiomeBase.PLAINS.id;
                        } else {
                            k1 = BiomeBase.FOREST.id;
                        }
                    }

                    if (var12 && k1 != var10) {
                        if (BiomeBase.getBiome(k1 + 128) != null) {
                            i2 += 128;
                        } else {
                            i2 = var10;
                        }
                    }*/

                    if (k1 == var10) {
                        var7[var9 + var8 * var3] = var10;
                    } else {
                        i2 = var5[var9 + 1 + (var8 + 1 - 1) * (var3 + 2)];
                        int var15 = var5[var9 + 1 + 1 + (var8 + 1) * (var3 + 2)];
                        int var16 = var5[var9 + 1 - 1 + (var8 + 1) * (var3 + 2)];
                        int var17 = var5[var9 + 1 + (var8 + 1 + 1) * (var3 + 2)];
                        int var18 = 0;
                        if (a(i2, var10)) {
                            ++var18;
                        }

                        if (a(var15, var10)) {
                            ++var18;
                        }

                        if (a(var16, var10)) {
                            ++var18;
                        }

                        if (a(var17, var10)) {
                            ++var18;
                        }

                        if (var18 >= 3) {
                            var7[var9 + var8 * var3] = k1;
                        } else {
                            var7[var9 + var8 * var3] = var10;
                        }
                    }
                }
            }
        }

        return var7;
    }

}
