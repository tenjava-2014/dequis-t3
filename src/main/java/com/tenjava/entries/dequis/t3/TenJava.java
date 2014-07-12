package com.tenjava.entries.dequis.t3;

import java.lang.Math;
import java.util.List;
import java.util.Random;
import java.util.Arrays;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;


public class TenJava extends JavaPlugin {
    public void onEnable() {
        /* there is nothing to do */
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new DerpChunkGenerator();
    }

    class DerpChunkGenerator extends ChunkGenerator {
        public byte[] generate(World world, Random random, int cx, int cz) {
            byte[] result = new byte[32768];
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < 64; y++) {
                        result[(x * 16 + z) * 128 + y] = (byte)Material.OBSIDIAN.getId();
                    }

                }
            }
            return result;
        }

        @Override
        public List<BlockPopulator> getDefaultPopulators(World world) {
            return Arrays.asList((BlockPopulator) new FlagPopulator());
        }

        @Override
        public Location getFixedSpawnLocation(World world, Random random) {
            int x = 0;
            int z = 0;
            int y = 127;
            return new Location(world, x, y, z);
        }
    }


    class FlagPopulator extends BlockPopulator {
        private double logRand(int max, Random random) {
            return max - Math.log10(random.nextInt((int) Math.pow(10, max)));
        }

        public void populate(World world, Random random, Chunk source) {
            Block block = null;
            if (random.nextInt(100) <= 90) {
                int centerX = (source.getX() << 4) + random.nextInt(16);
                int centerZ = (source.getZ() << 4) + random.nextInt(16);
                int centerY = world.getHighestBlockYAt(centerX, centerZ) + 1;

                int height = random.nextInt(20);
                for (int y = centerY; y < centerY + height; y++) {
                    block = world.getBlockAt(centerX, y, centerZ);
                    block.setType(Material.FENCE);
                }

            }
            int blocksPerChunk = (int) (logRand(5, random) * 2);

            for (int i = 0; i < blocksPerChunk; i++) {
                int centerX = (source.getX() << 4) + random.nextInt(16);
                int centerZ = (source.getZ() << 4) + random.nextInt(16);
                int y = random.nextInt(64) + 64;
                block = world.getBlockAt(centerX, y, centerZ);
                Material mat = Material.STONE;
                int rand = (int) Math.floor(Math.max(logRand(10, random) * 1.5, 10));
                switch (rand) {
                    case 0: mat = Material.STONE; break;
                    case 1: mat = Material.WOOD; break;
                    case 2: mat = Material.LOG; break;
                    case 3: mat = Material.COAL_ORE; break;
                    case 4: mat = Material.IRON_ORE; break;
                    case 5: mat = Material.DIAMOND_ORE; break;
                }
                block.setType(mat);
            }
        }
    }
}
