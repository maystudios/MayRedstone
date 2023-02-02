package net.maystudios.mayredstone.compiler.chunk;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LoadChunk {

    private World world;

    public LoadChunk(World world) {
        this.world = world;
    }

    public void replaceChunks(Chunk chunks[][], Block mat) {
        for (Chunk[] xChunk : chunks) {
            for (Chunk zChunk : xChunk) {
                replaceChunk(zChunk, mat);
            }
        }
    }

    public void replaceChunk(Chunk chunk, Block mat) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 256; y++) {
                    BlockPos pos = new BlockPos(x + chunk.getPos().getXStart(), y, z + chunk.getPos().getZStart());
                    world.setBlockState(pos, mat.getDefaultState());
                }
            }
        }
    }






/*
    public void replaceChunk(Chunk chunk, Block bs) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 256; y++) {
                    BlockPos pos = new BlockPos(x + chunk.getPos().getXStart(), y, z + chunk.getPos().getZStart());
                    world.setBlockState(pos, bs.getDefaultState());
                }
            }
        }
    }
*/
    private final ReadWriteLock worldLock = new ReentrantReadWriteLock();

    public void replaceChunksInThreads(List<Chunk> chunks, Block bs) {
        int chunkCount = chunks.size();
        int chunkSize = chunkCount / 1;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < chunkCount; i += chunkSize) {
            int end = Math.min(i + chunkSize, chunkCount);
            List<Chunk> chunkSubList = chunks.subList(i, end);
            Thread thread = new Thread(() -> replaceChunksPart(chunkSubList, bs));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void replaceChunksPart(List<Chunk> chunks, Block bs) {
        worldLock.writeLock().lock();
        try {
            for (Chunk chunk : chunks) {
                replaceChunk(chunk, bs);
            }
        } finally {
            worldLock.writeLock().unlock();
        }
    }
}
