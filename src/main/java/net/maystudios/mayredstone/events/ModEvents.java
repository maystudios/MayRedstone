package net.maystudios.mayredstone.events;

import net.maystudios.mayredstone.MayRedstone;
import net.maystudios.mayredstone.commands.MayRedstoneCommand_ChunkLoad;
import net.maystudios.mayredstone.commands.chunk.UpdateChunkCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = MayRedstone.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        MayRedstoneCommand_ChunkLoad mrccl = new MayRedstoneCommand_ChunkLoad();
        mrccl.register(event.getDispatcher());

        UpdateChunkCommand ucc = new UpdateChunkCommand();
        ucc.register(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
