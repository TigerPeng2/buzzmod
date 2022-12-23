package com.nytigeryxp.buzzmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab BUZZMOD_TAB = new CreativeModeTab("buzzmodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.PENNY.get());
        }
    };
}
