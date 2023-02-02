package net.maystudios.mayredstone.compiler.chunk.scanner;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ScanChunk {

    private World world;

    public ScanChunk(World world) {
        this.world = world;
    }

    public Chunk[][] scanAllChunks2D(ChunkPos pos, int xCount, int zCount) {
        Chunk chunks[][] = new Chunk[xCount][zCount];

        for (int xC = 0; xC < xCount; xC++ ) {
            for (int zC = 0; zC < zCount; zC++ ) {
                chunks[xC][zC] = world.getChunk(xC + pos.x, zC + pos.z);
            }
        }
        return chunks;
    }

    public Chunk[] scanAllChunks1D(ChunkPos pos, int xCount, int zCount) {
        Chunk chunks[] = new Chunk[xCount * zCount];

        for (int xC = 0; xC < xCount; xC++ ) {
            for (int zC = 0; zC < zCount; zC++ ) {
                chunks[xC * zCount + zC] = world.getChunk(xC + pos.x, zC + pos.z);
            }
        }
        return chunks;
    }

    public Block[] scanBlocksInChunk1D(Chunk chunk) {
        Block blocks[] = new Block[16 * 16 * 256];

        int i = 0;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 256; y++) {
                    blocks[i++] = null; //TODO
                }
            }
        }
        return blocks;
    }



    public ChunkPos getChunkPosFromBlockPos(BlockPos pos) {
        return new ChunkPos(pos);
    }

    public int getChunkXFromBlockPos(BlockPos pos) {
        return pos.getX() >> 4;
    }

    public int getChunkZFromBlockPos(BlockPos pos) {
        return pos.getZ() >> 4;
    }
}
