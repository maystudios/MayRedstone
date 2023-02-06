package net.maystudios.mayredstone.compiler.chunk.scanner;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
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

    public BlockPos[] getBlocksInWorld(Chunk fromChunk, int xCount, int zCount) {
        BlockPos[] blocks = new BlockPos[CHUNK_HEIGHT * CHUNK_SIZE * xCount * CHUNK_SIZE * zCount];

        for (int y = 0; y < CHUNK_HEIGHT; y++) {
            for (int x = 0; x < CHUNK_SIZE * xCount; x++) {
                for (int z = 0; z < CHUNK_SIZE * zCount; z++) {
                    int index = y + z * CHUNK_HEIGHT + x * CHUNK_SIZE * CHUNK_HEIGHT;
                    blocks[index] = new BlockPos(fromChunk.getPos().getXStart() + x, y, fromChunk.getPos().getZStart() + z);
                }
            }
        }
        return blocks;
    }

    public BlockState[] scanBlocksInWorld(Chunk fromChunk, int xCount, int zCount) {
        BlockState blocks[] = new BlockState[CHUNK_HEIGHT * CHUNK_SIZE * xCount * CHUNK_SIZE * zCount];

        int i = 0;
        for (int y = 0; y < CHUNK_HEIGHT; y++) {
            for (int x = 0; x < CHUNK_SIZE * xCount; x++) {
                for (int z = 0; z < CHUNK_SIZE * zCount; z++) {
                    blocks[i++] = world.getBlockState(new BlockPos(fromChunk.getPos().getXStart() + x, y, fromChunk.getPos().getZStart() + z));
                }
            }
        }
        return blocks;
    }
}
