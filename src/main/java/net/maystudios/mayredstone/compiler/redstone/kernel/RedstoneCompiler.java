package net.maystudios.mayredstone.compiler.redstone.kernel;

import net.maystudios.mayredstone.cuda.BasicJCuda;
import net.minecraft.world.World;

public class RedstoneCompiler {

    class BlockData {
        public int BlockTyp;
        public int BlockState;

        public int BlockAdditional;
        public int BlockAdvanced;
    }
    private World world;

    public RedstoneCompiler(World world) {
        this.world = world;
    }

    public void run() {

    }

    public void init() {
        int xChunk = 2;
        int yChunk = 2;


    }
}
