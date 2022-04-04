package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class GenLayerBiome extends GenLayer {
    private World world;
    private BiomeBase[] c;
    private BiomeBase[] d;
    private BiomeBase[] e;
    private BiomeBase[] f;
    private final CustomWorldSettingsFinal g;

    public GenLayerBiome(long var1, GenLayer var3, WorldType var4, String var5, World world) { // NachoSpigot - Add world
        super(var1);
        this.world = world;
        this.a = var3;

        // NachoSpigot - start
        this.world = world;
        List<BiomeBase> list = new ArrayList<BiomeBase>();
        //this.c = new BiomeBase[] { BiomeBase.DESERT, BiomeBase.DESERT, BiomeBase.DESERT, BiomeBase.SAVANNA, BiomeBase.SAVANNA, BiomeBase.PLAINS};
        if (world.generatorConfig.biomeDesert) {
            list.add(BiomeBase.DESERT);
            list.add(BiomeBase.DESERT);
            list.add(BiomeBase.DESERT);
        }
        if (world.generatorConfig.biomeSavanna) {
            list.add(BiomeBase.SAVANNA);
            list.add(BiomeBase.SAVANNA);
        }
        if (world.generatorConfig.biomePlains) {
            list.add(BiomeBase.PLAINS);
        }
        this.c = list.toArray(new BiomeBase[list.size()]);
        list.clear();
        //this.d = new BiomeBase[] { BiomeBase.FOREST, BiomeBase.ROOFED_FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.PLAINS, BiomeBase.BIRCH_FOREST, BiomeBase.SWAMPLAND};
        if (world.generatorConfig.biomeForest) {
            list.add(BiomeBase.FOREST);
        }
        if (world.generatorConfig.biomeRoofedForest) {
            list.add(BiomeBase.ROOFED_FOREST);
        }
        if (world.generatorConfig.biomeExtremeHills) {
            list.add(BiomeBase.EXTREME_HILLS);
        }
        if (world.generatorConfig.biomePlains) {
            list.add(BiomeBase.PLAINS);
        }
        if (world.generatorConfig.biomeBirchForest) {
            list.add(BiomeBase.BIRCH_FOREST);
        }
        if (world.generatorConfig.biomeSwampland) {
            list.add(BiomeBase.SWAMPLAND);
        }
        if (list.isEmpty() && world.generatorConfig.biomeJungle) {
            list.add(BiomeBase.JUNGLE);
        }
        this.d = list.toArray(new BiomeBase[list.size()]);
        list.clear();
        //this.e = new BiomeBase[] { BiomeBase.FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.TAIGA, BiomeBase.PLAINS};
        if (world.generatorConfig.biomeForest) {
            list.add(BiomeBase.FOREST);
        }
        if (world.generatorConfig.biomeExtremeHills) {
            list.add(BiomeBase.EXTREME_HILLS);
        }
        if (world.generatorConfig.biomeTaiga) {
            list.add(BiomeBase.TAIGA);
        }
        if (world.generatorConfig.biomePlains) {
            list.add(BiomeBase.PLAINS);
        }
        this.e = list.toArray(new BiomeBase[list.size()]);
        list.clear();
        //this.f = new BiomeBase[] { BiomeBase.ICE_PLAINS, BiomeBase.ICE_PLAINS, BiomeBase.ICE_PLAINS, BiomeBase.COLD_TAIGA};
        if (world.generatorConfig.biomeIcePlains) {
            list.add(BiomeBase.ICE_PLAINS);
            list.add(BiomeBase.ICE_PLAINS);
            list.add(BiomeBase.ICE_PLAINS);
        }
        if (world.generatorConfig.biomeColdTaiga) {
            list.add(BiomeBase.COLD_TAIGA);
        }

        this.f = list.toArray(new BiomeBase[list.size()]);

    //    this.c = new BiomeBase[]{BiomeBase.DESERT, BiomeBase.DESERT, BiomeBase.DESERT, BiomeBase.SAVANNA, BiomeBase.SAVANNA, BiomeBase.PLAINS};
     //   this.d = new BiomeBase[]{BiomeBase.FOREST, BiomeBase.ROOFED_FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.PLAINS, BiomeBase.BIRCH_FOREST, BiomeBase.SWAMPLAND};
     //   this.e = new BiomeBase[]{BiomeBase.FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.TAIGA, BiomeBase.PLAINS};
     //   this.f = new BiomeBase[]{BiomeBase.ICE_PLAINS, BiomeBase.ICE_PLAINS, BiomeBase.ICE_PLAINS, BiomeBase.COLD_TAIGA};
     //   this.a = var3;
        if (var4 == WorldType.NORMAL_1_1) {
            this.c = new BiomeBase[]{BiomeBase.DESERT, BiomeBase.FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.SWAMPLAND, BiomeBase.PLAINS, BiomeBase.TAIGA};
            this.g = null;
        } else if (var4 == WorldType.CUSTOMIZED) {
            this.g = CustomWorldSettingsFinal.CustomWorldSettings.a(var5).b();
        } else {
            this.g = null;
        }

    }

    public int[] a(int var1, int var2, int var3, int var4) {
        int[] var5 = this.a.a(var1, var2, var3, var4);
        int[] var6 = IntCache.a(var3 * var4);

        for(int var7 = 0; var7 < var4; ++var7) {
            for(int var8 = 0; var8 < var3; ++var8) {
                this.a((long)(var8 + var1), (long)(var7 + var2));
                int var9 = var5[var8 + var7 * var3];
                int var10 = (var9 & 3840) >> 8;
                var9 &= -3841;
                if (this.g != null && this.g.F >= 0) {
                    var6[var8 + var7 * var3] = this.g.F;
                } else if (b(var9)) {
                    var6[var8 + var7 * var3] = var9;
                } else if (var9 == BiomeBase.MUSHROOM_ISLAND.id) {
                    var6[var8 + var7 * var3] = var9;
                } else if (var9 == 1) {
                    if (var10 > 0) {
                        if (this.a(3) == 0 && this.world.generatorConfig.biomeMesaPlateau) {
                            var6[var8 + var7 * var3] = BiomeBase.MESA_PLATEAU.id;
                        }else if(this.world.generatorConfig.biomeMesaPlateauF) {
                            var6[var8 + var7 * var3] = BiomeBase.MESA_PLATEAU_F.id;
                        }else if(this.world.generatorConfig.biomeMesa){
                            var6[var8 + var7 * var3] = BiomeBase.MESA.id;
                        } else {
                            var6[var8 + var7 * var3] = this.c[this.a(this.c.length)].id;
                        }
                    } else {
                        var6[var8 + var7 * var3] = this.c[this.a(this.c.length)].id;
                    }
                } else if (var9 == 2) {
                    if (var10 > 0) {
                        var6[var8 + var7 * var3] = BiomeBase.JUNGLE.id;
                    } else {
                        var6[var8 + var7 * var3] = this.d[this.a(this.d.length)].id;
                    }
                } else if (var9 == 3) {
                    if (var10 > 0) {
                        var6[var8 + var7 * var3] = BiomeBase.MEGA_TAIGA.id;
                    } else {
                        var6[var8 + var7 * var3] = this.e[this.a(this.e.length)].id;
                    }
                } else if (var9 == 4) {
                    var6[var8 + var7 * var3] = this.f[this.a(this.f.length)].id;
                } else {
                    var6[var8 + var7 * var3] = BiomeBase.MUSHROOM_ISLAND.id;
                }
            }
        }

        return var6;
    }
}
