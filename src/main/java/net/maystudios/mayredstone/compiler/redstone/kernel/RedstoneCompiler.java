package net.maystudios.mayredstone.compiler.redstone.kernel;

import net.maystudios.mayredstone.compiler.chunk.scanner.ScanBlockChunk;
import net.maystudios.mayredstone.compiler.chunk.scanner.ScanChunk;
import net.maystudios.mayredstone.compiler.config.CompilerConfig;
import net.maystudios.mayredstone.cuda.BasicJCuda;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    private Set<BlockState> solidBlocks;
    
    public RedstoneCompiler(World world, int xChunk, int zChunk, ChunkPos pos){
        this.world = world;

        this.xChunk = xChunk;
        this.zChunk = zChunk;
        this.pos = pos;

        this.scanChunk = new ScanChunk(this.world);
        this.scanBlockChunk = new ScanBlockChunk(this.world);

        System.out.println("Passed: Compiler 1");

        //TODO
        /*
        CompilerConfig compilerConfig = new CompilerConfig();
        compilerConfig.readConfig("C:/Users/crazy/OneDrive/Desktop/MayRedstone/src/main/java/net/maystudios/mayredstone/compiler/config/blockConfig.json");

        System.out.println("Passed: Compiler 2");

        BlockState[] blocks = new BlockState[compilerConfig.solid.length];
        for (int i = 0; i < compilerConfig.solid.length; i++) {
            blocks[i] = Block.getStateById(compilerConfig.solid[i]);
        }

        System.out.println("Passed: Compiler 3");

        this.solidBlocks = new HashSet<>(Arrays.asList(blocks));
        */
        System.out.println("Passed: Compiler 5");
    }

    public void run() {

    }

    public void init() {
        Chunk[] chunks = scanChunk.scanAllChunks1D(pos,xChunk,zChunk);
        BlockState[] blockStates = scanBlockChunk.scanBlocksInChunks1D(chunks);
    }

    public void relocate(BlockState[] blockStates) {
        for (BlockState blockState: blockStates) {
            Block block = blockState.getBlock();
            System.out.println("Block: " + block + "      " + "BlockState: " + blockState);
        }
    }
}
