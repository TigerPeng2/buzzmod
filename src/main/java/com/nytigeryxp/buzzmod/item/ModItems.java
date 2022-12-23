package com.nytigeryxp.buzzmod.item;

import com.nytigeryxp.buzzmod.BuzzMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BuzzMod.MODID);

    public static final RegistryObject<Item> PENNY = ITEMS.register("penny", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.BUZZMOD_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
