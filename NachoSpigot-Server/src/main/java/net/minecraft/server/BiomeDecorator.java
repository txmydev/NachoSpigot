package net.minecraft.server;

import dev.cobblesword.nachospigot.generator.GeneratorConfig;

import java.util.Random;

public class BiomeDecorator {
    protected World world;
    protected Random b;
    protected BlockPosition c;
    protected CustomWorldSettingsFinal d;
    protected WorldGenerator e = new WorldGenClay(4);
    protected WorldGenerator f;
    protected WorldGenerator g;
    protected WorldGenerator h;
    protected WorldGenerator i;
    protected WorldGenerator j;
    protected WorldGenerator k;
    protected WorldGenerator l;
    protected WorldGenerator m;
    protected WorldGenerator n;
    protected WorldGenerator o;
    protected WorldGenerator p;
    protected WorldGenerator q;
    protected WorldGenerator r;
    protected WorldGenFlowers s;
    protected WorldGenerator t;
    protected WorldGenerator u;
    protected WorldGenerator v;
    protected WorldGenerator w;
    protected WorldGenerator x;
    protected WorldGenerator y;
    protected int z;
    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected int E;
    protected int F;
    protected int G;
    protected int H;
    protected int I;
    protected int J;
    protected int K;
    public boolean L;

    public BiomeDecorator() {
        this.f = new WorldGenSand(Blocks.SAND, 7);
        this.g = new WorldGenSand(Blocks.GRAVEL, 6);
        this.s = new WorldGenFlowers(Blocks.YELLOW_FLOWER, BlockFlowers.EnumFlowerVarient.DANDELION);
        this.t = new WorldGenMushrooms(Blocks.BROWN_MUSHROOM);
        this.u = new WorldGenMushrooms(Blocks.RED_MUSHROOM);
        this.v = new WorldGenHugeMushroom();
        this.w = new WorldGenReed();
        this.x = new WorldGenCactus();
        this.y = new WorldGenWaterLily();
        this.B = 2;
        this.C = 1;
        this.H = 1;
        this.I = 3;
        this.J = 1;
        this.L = true;
    }

    public void a(World world, Random var2, BiomeBase var3, BlockPosition var4) {
        if (this.world != null) {
            throw new RuntimeException("Already decorating");
        } else {
            this.world = world;
            String var5 = world.getWorldData().getGeneratorOptions();
            if (var5 != null) {
                this.d = CustomWorldSettingsFinal.CustomWorldSettings.a(var5).b();
            } else {
                this.d = CustomWorldSettingsFinal.CustomWorldSettings.a("").b();
            }

            this.b = var2;
            this.c = var4;
            this.h = new WorldGenMinable(Blocks.DIRT.getBlockData(), this.d.I);
            this.i = new WorldGenMinable(Blocks.GRAVEL.getBlockData(), this.d.M);
            this.j = new WorldGenMinable(Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.GRANITE), this.d.Q);
            this.k = new WorldGenMinable(Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.DIORITE), this.d.U);
            this.l = new WorldGenMinable(Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.ANDESITE), this.d.Y);
            this.m = new WorldGenMinable(Blocks.COAL_ORE.getBlockData(), world.generatorConfig.coalSize);
            this.n = new WorldGenMinable(Blocks.IRON_ORE.getBlockData(), world.generatorConfig.ironSize);
            this.o = new WorldGenMinable(Blocks.GOLD_ORE.getBlockData(), world.generatorConfig.goldSize);
            this.p = new WorldGenMinable(Blocks.REDSTONE_ORE.getBlockData(), world.generatorConfig.redstoneSize);
            this.q = new WorldGenMinable(Blocks.DIAMOND_ORE.getBlockData(), world.generatorConfig.diamondSize);
            this.r = new WorldGenMinable(Blocks.LAPIS_ORE.getBlockData(), world.generatorConfig.lapisSize);
            this.a(var3);
            this.world = null;
            this.b = null;
        }
    }

    protected void a(BiomeBase var1) {
        this.a();

        int var2;
        int var3;
        int var4;
        for (var2 = 0; var2 < this.I; ++var2) {
            var3 = this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(16) + 8;
            this.f.generate(this.world, this.b, this.world.r(this.c.a(var3, 0, var4)));
        }

        for (var2 = 0; var2 < this.J; ++var2) {
            var3 = this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(16) + 8;
            this.e.generate(this.world, this.b, this.world.r(this.c.a(var3, 0, var4)));
        }

        for (var2 = 0; var2 < this.H; ++var2) {
            var3 = this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(16) + 8;
            this.g.generate(this.world, this.b, this.world.r(this.c.a(var3, 0, var4)));
        }

        var2 = this.A;
        if (this.b.nextInt(10) == 0) {
            ++var2;
        }

        int var5;
        BlockPosition var7;
        for (var3 = 0; var3 < var2; ++var3) {
            var4 = this.b.nextInt(16) + 8;
            var5 = this.b.nextInt(16) + 8;
            WorldGenTreeAbstract var6 = var1.a(this.b);
            var6.e();
            var7 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5));
            if (var6.generate(this.world, this.b, var7)) {
                var6.a(this.world, this.b, var7);
            }
        }

        for (var3 = 0; var3 < this.K; ++var3) {
            var4 = this.b.nextInt(16) + 8;
            var5 = this.b.nextInt(16) + 8;
            this.v.generate(this.world, this.b, this.world.getHighestBlockYAt(this.c.a(var4, 0, var5)));
        }

        BlockPosition var8;
        int var11;
        int var12;
        for (var3 = 0; var3 < this.B; ++var3) {
            var4 = this.b.nextInt(16) + 8;
            var5 = this.b.nextInt(16) + 8;
            var11 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5)).getY() + 32;
            if (var11 > 0) {
                var12 = this.b.nextInt(var11);
                var8 = this.c.a(var4, var12, var5);
                BlockFlowers.EnumFlowerVarient var9 = var1.a(this.b, var8);
                BlockFlowers var10 = var9.a().a();
                if (var10.getMaterial() != Material.AIR) {
                    this.s.a(var10, var9);
                    this.s.generate(this.world, this.b, var8);
                }
            }
        }

        for (var3 = 0; var3 < this.C; ++var3) {
            var4 = this.b.nextInt(16) + 8;
            var5 = this.b.nextInt(16) + 8;
            var11 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5)).getY() * 2;
            if (var11 > 0) {
                var12 = this.b.nextInt(var11);
                var1.b(this.b).generate(this.world, this.b, this.c.a(var4, var12, var5));
            }
        }

        for (var3 = 0; var3 < this.D; ++var3) {
            var4 = this.b.nextInt(16) + 8;
            var5 = this.b.nextInt(16) + 8;
            var11 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5)).getY() * 2;
            if (var11 > 0) {
                var12 = this.b.nextInt(var11);
                (new WorldGenDeadBush()).generate(this.world, this.b, this.c.a(var4, var12, var5));
            }
        }

        for (var3 = 0; var3 < this.z; ++var3) {
            var4 = this.b.nextInt(16) + 8;
            var5 = this.b.nextInt(16) + 8;
            var11 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5)).getY() * 2;
            if (var11 > 0) {
                var12 = this.b.nextInt(var11);

                BlockPosition var14;
                for (var8 = this.c.a(var4, var12, var5); var8.getY() > 0; var8 = var14) {
                    var14 = var8.down();
                    if (!this.world.isEmpty(var14)) {
                        break;
                    }
                }

                this.y.generate(this.world, this.b, var8);
            }
        }

        for (var3 = 0; var3 < this.E; ++var3) {
            if (this.b.nextInt(4) == 0) {
                var4 = this.b.nextInt(16) + 8;
                var5 = this.b.nextInt(16) + 8;
                BlockPosition var13 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5));
                this.t.generate(this.world, this.b, var13);
            }

            if (this.b.nextInt(8) == 0) {
                var4 = this.b.nextInt(16) + 8;
                var5 = this.b.nextInt(16) + 8;
                var11 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5)).getY() * 2;
                if (var11 > 0) {
                    var12 = this.b.nextInt(var11);
                    var8 = this.c.a(var4, var12, var5);
                    this.u.generate(this.world, this.b, var8);
                }
            }
        }

        if (this.b.nextInt(4) == 0) {
            var3 = this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(16) + 8;
            var5 = this.world.getHighestBlockYAt(this.c.a(var3, 0, var4)).getY() * 2;
            if (var5 > 0) {
                var11 = this.b.nextInt(var5);
                this.t.generate(this.world, this.b, this.c.a(var3, var11, var4));
            }
        }

        if (this.b.nextInt(8) == 0) {
            var3 = this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(16) + 8;
            var5 = this.world.getHighestBlockYAt(this.c.a(var3, 0, var4)).getY() * 2;
            if (var5 > 0) {
                var11 = this.b.nextInt(var5);
                this.u.generate(this.world, this.b, this.c.a(var3, var11, var4));
            }
        }

        for (var3 = 0; var3 < this.F * world.generatorConfig.sugarCaneMultiplier; ++var3) {
            var4 = this.b.nextInt(16) + 8;
            var5 = this.b.nextInt(16) + 8;
            var11 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5)).getY() * 2;
            if (var11 > 0) {
                var12 = this.b.nextInt(var11);
                this.w.generate(this.world, this.b, this.c.a(var4, var12, var5));
            }
        }

        for (var3 = 0; var3 < 10 * world.generatorConfig.sugarCaneMultiplier; ++var3) {
            var4 = this.b.nextInt(16) + 8;
            var5 = this.b.nextInt(16) + 8;
            var11 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5)).getY() * 2;
            if (var11 > 0) {
                var12 = this.b.nextInt(var11);
                this.w.generate(this.world, this.b, this.c.a(var4, var12, var5));
            }
        }

        if (this.b.nextInt(32) == 0) {
            var3 = this.b.nextInt(16) + 8;
            var4 = this.b.nextInt(16) + 8;
            var5 = this.world.getHighestBlockYAt(this.c.a(var3, 0, var4)).getY() * 2;
            if (var5 > 0) {
                var11 = this.b.nextInt(var5);
                (new WorldGenPumpkin()).generate(this.world, this.b, this.c.a(var3, var11, var4));
            }
        }

        for (var3 = 0; var3 < this.G; ++var3) {
            var4 = this.b.nextInt(16) + 8;
            var5 = this.b.nextInt(16) + 8;
            var11 = this.world.getHighestBlockYAt(this.c.a(var4, 0, var5)).getY() * 2;
            if (var11 > 0) {
                var12 = this.b.nextInt(var11);
                this.x.generate(this.world, this.b, this.c.a(var4, var12, var5));
            }
        }

        if (this.L) {
            for (var3 = 0; var3 < 50; ++var3) {
                var4 = this.b.nextInt(16) + 8;
                var5 = this.b.nextInt(16) + 8;
                var11 = this.b.nextInt(248) + 8;
                if (var11 > 0) {
                    var12 = this.b.nextInt(var11);
                    var8 = this.c.a(var4, var12, var5);
                    (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(this.world, this.b, var8);
                }
            }

            for (var3 = 0; var3 < 20; ++var3) {
                var4 = this.b.nextInt(16) + 8;
                var5 = this.b.nextInt(16) + 8;
                var11 = this.b.nextInt(this.b.nextInt(this.b.nextInt(240) + 8) + 8);
                var7 = this.c.a(var4, var11, var5);
                (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(this.world, this.b, var7);
            }
        }

    }

    protected void a(int var1, WorldGenerator var2, int var3, int var4) {
        int var5;
        if (var4 < var3) {
            var5 = var3;
            var3 = var4;
            var4 = var5;
        } else if (var4 == var3) {
            if (var3 < 255) {
                ++var4;
            } else {
                --var3;
            }
        }

        for (var5 = 0; var5 < var1; ++var5) {
            BlockPosition var6 = this.c.a(this.b.nextInt(16), this.b.nextInt(var4 - var3) + var3, this.b.nextInt(16));
            var2.generate(this.world, this.b, var6);
        }

    }

    protected void b(int var1, WorldGenerator var2, int var3, int var4) {
        for (int var5 = 0; var5 < var1; ++var5) {
            BlockPosition var6 = this.c.a(this.b.nextInt(16), this.b.nextInt(var4) + this.b.nextInt(var4) + var3 - var4, this.b.nextInt(16));
            var2.generate(this.world, this.b, var6);
        }

    }

    protected void a() {
        this.a(this.d.J, this.h, this.d.K, this.d.L);
        this.a(this.d.N, this.i, this.d.O, this.d.P);

        /*
                    this.m = new WorldGenMinable(Blocks.COAL_ORE.getBlockData(), world.generatorConfig.coalSize);
            this.n = new WorldGenMinable(Blocks.IRON_ORE.getBlockData(), world.generatorConfig.ironSize);
            this.o = new WorldGenMinable(Blocks.GOLD_ORE.getBlockData(), world.generatorConfig.goldSize);
            this.p = new WorldGenMinable(Blocks.REDSTONE_ORE.getBlockData(), world.generatorConfig.redstoneSize);
            this.q = new WorldGenMinable(Blocks.DIAMOND_ORE.getBlockData(), world.generatorConfig.diamondSize);
            this.r = new WorldGenMinable(Blocks.LAPIS_ORE.getBlockData(), world.generatorConfig.lapisSize);
         */
        GeneratorConfig conf = this.world.generatorConfig;
        if (conf.coalMultiplier > 0) this.a((int) (20 * conf.coalMultiplier + this.b.nextFloat()), this.m, 0, 128);
        if (conf.ironMultiplier > 0) this.a((int) (20 * conf.ironMultiplier + this.b.nextFloat()), this.n, 0, 64);
        if (conf.goldMultiplier > 0) this.a((int) (2 * conf.goldMultiplier + this.b.nextFloat()), this.o, 0, 32);
        if (conf.redstoneMultiplier > 0) this.a((int) (8 * conf.redstoneMultiplier + this.b.nextFloat()), this.p, 0, 16);
        if (conf.diamondMultiplier > 0) this.a((int) (1 * conf.diamondMultiplier + this.b.nextFloat()), this.q, 0, 16);
        if (conf.lapisMultiplier > 0) this.b((int) (1 * conf.lapisMultiplier + this.b.nextFloat()), this.r, 16, 16);
    }
}
