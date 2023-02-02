package net.maystudios.mayredstone.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import jcuda.Pointer;
import jcuda.Sizeof;
import jcuda.driver.CUdeviceptr;
import jcuda.driver.CUresult;
import jcuda.driver.JCudaDriver;
import jcuda.runtime.JCuda;
import net.maystudios.mayredstone.compiler.chunk.LoadChunk;
import net.maystudios.mayredstone.compiler.chunk.scanner.ScanChunk;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.maystudios.mayredstone.cuda.BasicJCuda;
import net.maystudios.mayredstone.cuda.Vector3;

public class MayRedstoneCommand_ChunkLoad implements Command<CommandSource>   {

    @Override
    public int run(CommandContext<CommandSource> context) {
        CommandSource source = context.getSource();
        int xCount = IntegerArgumentType.getInteger(context, "xCount");
        int zCount = IntegerArgumentType.getInteger(context, "zCount");

        try {
            setChunks(source, xCount, zCount);
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }

        String s = "";

        BasicJCuda basicJCuda = new BasicJCuda();

        basicJCuda.loadModule("C:/Users/crazy/OneDrive/Desktop/MayRedstone/src/main/java/net/maystudios/mayredstone/commands/sampleKernel.ptx");
        basicJCuda.loadFunction("sampleKernel");

        // Alloziere Speicher auf GPU
        CUdeviceptr deviceInputA = new CUdeviceptr();
        JCudaDriver.cuMemAlloc(deviceInputA, 2 * Sizeof.INT);
        CUdeviceptr deviceInputB = new CUdeviceptr();
        JCudaDriver.cuMemAlloc(deviceInputB, 2 * Sizeof.INT);
        CUdeviceptr deviceOutput = new CUdeviceptr();
        JCudaDriver.cuMemAlloc(deviceOutput, 2 * Sizeof.INT);

        // Übertrage Daten von CPU zu GPU
        int[] hostInputA = new int[] {1, 2};
        JCudaDriver.cuMemcpyHtoD(deviceInputA, Pointer.to(hostInputA), 2 * Sizeof.INT);
        int[] hostInputB = new int[] {3, 4};
        JCudaDriver.cuMemcpyHtoD(deviceInputB, Pointer.to(hostInputB), 2 * Sizeof.INT);

        // Rufe CUDA-Kernel auf
        Pointer kernelParameters = Pointer.to(
                Pointer.to(deviceInputA),
                Pointer.to(deviceInputB),
                Pointer.to(deviceOutput));

        basicJCuda.launchKernel(kernelParameters, null, null, 0, new Vector3(1, 1,1), new Vector3(2, 1, 1));

        // Übertrage Ergebnisse zurück zur CPU
        int[] hostOutput = new int[2];
        JCudaDriver.cuMemcpyDtoH(Pointer.to(hostOutput), deviceOutput, 2 * Sizeof.INT);

        // Gebe Ergebnisse aus
        s = "Ergebnis: " + hostOutput[0] + " + " + hostOutput[1] + " = " + (hostOutput[0] + hostOutput[1]);

        int error = JCuda.cudaGetLastError();
        if(error != CUresult.CUDA_SUCCESS) {
            s = JCuda.cudaGetErrorString(error);
        }
        // Freigeben von Ressourcen
        JCudaDriver.cuMemFree(deviceInputA);
        JCudaDriver.cuMemFree(deviceInputB);
        JCudaDriver.cuMemFree(deviceOutput);

        basicJCuda.destroy();

        basicJCuda.messureTime();



        source.sendFeedback(ITextComponent.getTextComponentOrEmpty(s), true);

        //source.sendFeedback(new TranslationTextComponent("commands.example.success", xCount, zCount), true);
        return 1;
    }

    public void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("may")
                        .then(Commands.argument("xCount", IntegerArgumentType.integer())
                                .then(Commands.argument("zCount", IntegerArgumentType.integer())
                                        .executes(new MayRedstoneCommand_ChunkLoad())))
        );
    }


    private int setChunks(CommandSource source, int xCount, int zCount) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        World world = player.world;
        BlockPos pos = player.getPosition();

        ScanChunk scanChunk = new ScanChunk(world);
        LoadChunk loadChunk = new LoadChunk(world);

        Chunk[][] chunks = scanChunk.scanAllChunks2D(new ChunkPos(pos.getX() >> 4, pos.getZ() >> 4), xCount, zCount);

        List<Chunk> chunksL = new ArrayList<>();
        for (Chunk[] xChunks : chunks) {
            chunksL.addAll(Arrays.asList(xChunks));
        }


        loadChunk.replaceChunks(chunks, Blocks.WHITE_STAINED_GLASS);


        source.sendFeedback(new StringTextComponent("Set Chunk at: " + new ChunkPos(pos.getX() >> 4, pos.getZ() >> 4) + " Error: " + chunks.length), true);
        return 1;
    }



    public static String replaceAllChunksWithRedstoneMultiThreaded(World world, BlockPos pos, int chunkCountX, int chunkCountZ) {
        String err = null;

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int xC = 0; xC < chunkCountX; xC++) {
            for (int zC = 0; zC < chunkCountZ; zC++) {
                int finalX = xC;
                int finalZ = zC;
                executor.execute(() -> {
                    for (int y = 0; y < 256; y++) {
                        for (int x = 0; x < 16; x++) {
                            for (int z = 0; z < 16; z++) {
                                BlockPos blockPos = new BlockPos(pos.getX() + (finalX * 16) + x, y, pos.getZ() + (finalZ * 16) + z);
                                world.setBlockState(blockPos, Blocks.GLASS.getDefaultState());
                            }
                        }
                    }
                });
            }
        }
        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            err = e.toString();
        }

        return err;
    }

}
