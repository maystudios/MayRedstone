package net.maystudios.mayredstone.compiler.chunk;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.BlockEvent;

import java.util.HashSet;

public class UpdateChunk {
    private World world;

    public UpdateChunk(World world) {
        this.world = world;
    }

    public void unloadChunk(Chunk chunk) {
        chunk.setLoaded(false);
    }

    public void unloadChunks(Chunk chunks[]) {
        for (Chunk chunk: chunks) {
            unloadChunk(chunk);
        }
    }

    public void loadChunk(Chunk chunk) {
        chunk.setLoaded(true);
    }


}
