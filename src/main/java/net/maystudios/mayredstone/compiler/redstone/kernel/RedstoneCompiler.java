package net.maystudios.mayredstone.compiler.redstone.kernel;

import net.maystudios.mayredstone.compiler.chunk.scanner.ScanBlockChunk;
import net.maystudios.mayredstone.compiler.chunk.scanner.ScanChunk;
import net.maystudios.mayredstone.cuda.BasicJCuda;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class RedstoneCompiler {

    class BlockData {
        public int BlockTyp;
        public int BlockState;

        public int BlockAdditional;
        public int BlockAdvanced;
    }
    private World world;
    private int xChunk;
    private int zChunk;

    private ChunkPos pos;

    private ScanChunk scanChunk;
    private ScanBlockChunk scanBlockChunk;



    public RedstoneCompiler(World world, int xChunk, int zChunk, ChunkPos pos){
        this.world = world;

        this.xChunk = xChunk;
        this.zChunk = zChunk;
        this.pos = pos;

        this.scanChunk = new ScanChunk(this.world);
        this.scanBlockChunk = new ScanBlockChunk(this.world);

    }

    public void run() {

    }

    public void init() {

        Chunk[] chunks = scanChunk.scanAllChunks1D(pos,xChunk,zChunk);
        BlockState[] blockStates = scanBlockChunk.scanBlocksInChunks1D(chunks);

    }

    private void relocate(BlockState[] blockStates) {
        for (BlockState blockState: blockStates) {
            Block block = blockState.getBlock();
            block.getDefaultState().getProperties().

        }
    }
}
