package com.tenjava.entries.dequis.t3;

import java.util.List;
import java.util.Random;
import java.util.Arrays;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class TenJava extends JavaPlugin {
    public void onEnable() {

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
                    for (int y = 0; y < 10; y++) {
                        result[(x * 16 + z) * 128 + y] = (byte)Material.STONE.getId();
                    }

                }
            }
            return result;
        }

        @Override
        public List<BlockPopulator> getDefaultPopulators(World world) {
            return Arrays.asList();
        }

        @Override
        public Location getFixedSpawnLocation(World world, Random random) {
            int x = 0;
            int z = 0;
            int y = 127;
            return new Location(world, x, y, z);
        }
    }
}
