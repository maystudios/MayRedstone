package net.maystudios.mayredstone.compiler.redstone;


import java.sql.Struct;

public class RedstoneKernelHandler {
    public enum MinecraftBlock
    {
        BLOCK_TRANSPARENT,
        BLOCK_SOLID,
        BLOCK_FALLING,
        REDSTONE_REDSTONE_BLOCK,
        REDSTONE_REDSTONE_DUST,
        REDSTONE_REPEATER,
        REDSTONE_COMPERATOR,
        REDSTONE_TORCH;

        public static final int BLOCK_COUNT = values().length;
    }

    public enum REDSTONE
    {
        POWERED,
        UNPOWERED
    }

}
