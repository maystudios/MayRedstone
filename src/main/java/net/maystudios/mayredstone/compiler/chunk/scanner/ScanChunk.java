package net.maystudios.mayredstone.compiler.chunk.scanner;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Arrays;
import java.util.List;

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
