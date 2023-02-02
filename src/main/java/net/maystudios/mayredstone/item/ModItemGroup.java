package net.maystudios.mayredstone.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;


public class ModItemGroup {

    public static final ItemGroup MAYREDSTONE_GROUP = new ItemGroup("mayredstoneTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.AMETHYST.get());
        }
    };

    public static final ItemGroup PUMP_GROUP = new ItemGroup("pumpTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.YENNI.get());
        }
    };
}
