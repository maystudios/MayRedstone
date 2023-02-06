package net.maystudios.mayredstone.chunk.block.handler;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockUpdateHandler {

    private World world;
    private ServerWorld serverWorld;

    public BlockUpdateHandler(World world) {
        this.world = world;
    }

    private Set<BlockPos> disabledBlockPositions = new HashSet<>();

    @SubscribeEvent(priority= EventPriority.HIGHEST)
    public void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        BlockPos blockPos = event.getPos();
        if (disabledBlockPositions.contains(blockPos) && event.isCancelable()) {
            event.setCanceled(true);
        }
    }

    public void unloadBlock(BlockPos blockPos) {
        disabledBlockPositions.add(blockPos);
    }

    public void addDisabledBlockPos(int x, int y, int z) {
        disabledBlockPositions.add(new BlockPos(x, y, z));
    }

    public void unloadBlocks(BlockPos blocksPos[]) {
        for (BlockPos blockPos: blocksPos) {
            unloadBlock(blockPos);
        }
    }
}
