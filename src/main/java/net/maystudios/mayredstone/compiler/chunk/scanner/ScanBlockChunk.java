package net.maystudios.mayredstone.compiler.chunk.scanner;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScanBlockChunk {

    private World world;
    private final int CHUNK_SIZE = 16;
    private final int CHUNK_HEIGHT = 256;

    public ScanBlockChunk(World world) {
        this.world = world;
    }

    public BlockPos[] getBlocksInChunk(Chunk chunk) {
        BlockPos[] blocks = new BlockPos[CHUNK_SIZE * CHUNK_SIZE * CHUNK_HEIGHT];

        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int z = 0; z < CHUNK_SIZE; z++) {
                for (int y = 0; y < CHUNK_HEIGHT; y++) {
                    int index = y + z * CHUNK_HEIGHT + x * CHUNK_SIZE * CHUNK_HEIGHT;
                    blocks[index] = new BlockPos(chunk.getPos().getXStart() + x, y , chunk.getPos().getZStart() + z);
                }
            }
        }
        return blocks;
    }

    public BlockPos[] getBlocksInChunks(Chunk[] chunks) {
        List<BlockPos> blockPosList = new ArrayList<>();

        for (Chunk chunk: chunks) {
            blockPosList.addAll(Arrays.asList(getBlocksInChunk(chunk)));
        }
        BlockPos[] blocks = blockPosList.toArray(new BlockPos[0]);
        return blocks;
    }

    public BlockState[] scanBlocksInChunk1D(Chunk chunk) {
        BlockState blocks[] = new BlockState[16 * 16 * 256];

        int i = 0;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 256; y++) {
                    blocks[i++] = world.getBlockState(new BlockPos(x, y, z));
                }
            }
        }
        return blocks;
    }

    public BlockState[] scanBlocksInChunks1D(Chunk[] chunks) {
        List<BlockState>blocks = null;
        for (Chunk chunk: chunks) {
            blocks.addAll(Arrays.asList(scanBlocksInChunk1D(chunk)));
        }
        return blocks.toArray(new BlockState[0]);
    }
}
