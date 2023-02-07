package net.maystudios.mayredstone.compiler.chunk.scanner;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScanBlockWorld {


    private World world;
    private final int CHUNK_SIZE = 16;
    private final int CHUNK_HEIGHT = 256;

    public ScanBlockWorld(World world) {
        this.world = world;
    }

    public BlockPos[] getBlocksInWorld(ChunkPos chunkPos, int xCount, int zCount) {
        BlockPos[] blocks = new BlockPos[CHUNK_HEIGHT * CHUNK_SIZE * xCount * CHUNK_SIZE * zCount];

        for (int y = 0; y < CHUNK_HEIGHT; y++) {
            for (int x = 0; x < CHUNK_SIZE * xCount; x++) {
                for (int z = 0; z < CHUNK_SIZE * zCount; z++) {
                    int index = y * CHUNK_SIZE * xCount * CHUNK_SIZE * zCount + x * CHUNK_SIZE * zCount + z;
                    blocks[index] = new BlockPos(chunkPos.getXStart() + x, y, chunkPos.getZStart() + z);
                }
            }
        }
        return blocks;
    }

    public BlockState[] scanBlocksInWorld(ChunkPos chunkPos, int xCount, int zCount) {
        BlockState blocks[] = new BlockState[CHUNK_HEIGHT * CHUNK_SIZE * xCount * CHUNK_SIZE * zCount];

        int i = 0;
        for (int y = 0; y < CHUNK_HEIGHT; y++) {
            for (int x = 0; x < CHUNK_SIZE * xCount; x++) {
                for (int z = 0; z < CHUNK_SIZE * zCount; z++) {
                    int index = y * CHUNK_SIZE * xCount * CHUNK_SIZE * zCount + x * CHUNK_SIZE * zCount + z;
                    blocks[index] = world.getBlockState(new BlockPos(chunkPos.getXStart() + x, y, chunkPos.getZStart() + z));

                    if ( Block.getStateId(blocks[index]) != 0) {
                        System.out.println(blocks[index] + "   " + Block.getStateId(blocks[index]));
                    }
                }
            }
        }
        return blocks;
    }
}
