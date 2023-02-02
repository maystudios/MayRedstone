package net.maystudios.mayredstone.commands.chunk;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.maystudios.mayredstone.MayRedstone;
import net.maystudios.mayredstone.compiler.block.SetBlock;
import net.maystudios.mayredstone.compiler.chunk.scanner.ScanBlockChunk;
import net.maystudios.mayredstone.compiler.chunk.scanner.ScanChunk;
import net.maystudios.mayredstone.compiler.chunk.UpdateChunk;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class UpdateChunkCommand implements Command<CommandSource> {

    @Override
    public int run(CommandContext<CommandSource> context) {
        CommandSource source = context.getSource();

        int xCount = IntegerArgumentType.getInteger(context, "xCount");
        int zCount = IntegerArgumentType.getInteger(context, "zCount");

        try {
            execute(source, xCount, zCount);
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }

        source.sendFeedback(ITextComponent.getTextComponentOrEmpty("Done?"), true);

        //source.sendFeedback(new TranslationTextComponent("commands.example.success", xCount, zCount), true);
        return 1;
    }

    private void execute(CommandSource source, int xCount, int zCount) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        World world = player.world;
        BlockPos pos = player.getPosition();
        System.out.println("Passed 1");


        ScanChunk scanChunk = new ScanChunk(world);
        Chunk[] chunks = scanChunk.scanAllChunks1D(scanChunk.getChunkPosFromBlockPos(pos) ,xCount, zCount);
        UpdateChunk updateChunk = new UpdateChunk(world);
        updateChunk.unloadChunks(chunks);
        System.out.println("Passed 2");


        ScanBlockChunk scanBlockChunk = new ScanBlockChunk(world);
        BlockPos[] blocks = scanBlockChunk.getBlocksInChunks(chunks);
        MayRedstone.blockUpdateHandler.unloadBlocks(blocks);
        System.out.println("Passed 3 " + blocks.length);

        /*
        SetBlock setBlock = new SetBlock(world);
        setBlock.SetBlocks(blocks, Blocks.CYAN_STAINED_GLASS);
        System.out.println("Passed 4");
        */
    }

    public void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("may")
                    .then(Commands.literal("chunk")
                        .then(Commands.literal("unload")
                            .then(Commands.argument("xCount", IntegerArgumentType.integer())
                                .then(Commands.argument("zCount", IntegerArgumentType.integer())
                                    .executes(new UpdateChunkCommand())))))
        );
    }
}
