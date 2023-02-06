package net.maystudios.mayredstone.chunk.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SetBlock {

    private World world;

    public SetBlock(World world) {
        this.world = world;
    }

    public void SetBlocks(BlockPos[] blocks, Block mat) {
        for (BlockPos block: blocks) {
            world.setBlockState(block, mat.getDefaultState());
        }
    }
}
