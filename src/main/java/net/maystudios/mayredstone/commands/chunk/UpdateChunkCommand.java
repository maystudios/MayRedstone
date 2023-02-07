package net.maystudios.mayredstone.commands.chunk;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.maystudios.mayredstone.MayRedstone;
import net.maystudios.mayredstone.compiler.chunk.scanner.ScanBlockWorld;
import net.maystudios.mayredstone.loging.Log;
import net.minecraft.block.BlockState;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ITextComponent;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.logging.Level;

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

        ScanBlockWorld scanBlockWorld = new ScanBlockWorld(world);
        BlockPos[] blockPos = scanBlockWorld.getBlocksInWorld(new ChunkPos(pos), xCount, zCount);
        MayRedstone.blockUpdateHandler.unloadBlocks(blockPos);

        Log.log(Level.INFO," Unload Blocks: " + blockPos.length);

        BlockState[] blockStates = scanBlockWorld.scanBlocksInWorld(new ChunkPos(pos), xCount, zCount);

        MayRedstone.redstoneCompiler.setWorld(world);
        MayRedstone.redstoneCompiler.setxChunk(xCount);
        MayRedstone.redstoneCompiler.setzChunk(zCount);
        MayRedstone.redstoneCompiler.setPos(new ChunkPos(pos));

        Log.log(Level.INFO," Scan Blocks: " + blockStates.length);

        // MayRedstone.redstoneCompiler.relocate(blockStates);
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
