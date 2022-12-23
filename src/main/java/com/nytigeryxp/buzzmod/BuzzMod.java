package com.nytigeryxp.buzzmod;

import com.mojang.logging.LogUtils;
import com.nytigeryxp.buzzmod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BuzzMod.MODID)
public class BuzzMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "buzzmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public BuzzMod()
    {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        // All event registers must be done within the main mod constructor
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ClickEventHandler {
        @SubscribeEvent
        public static void printEventName(PlayerInteractEvent event) {
            System.out.println(event.getClass().toString());
            switch (event.getClass().toString().split("\\$")[1]) {
                case "LeftClickBlock" -> Minecraft.getInstance().player.sendSystemMessage(Component.literal("Left CLick Block"));
                case "LeftClickEmpty" ->  Minecraft.getInstance().player.sendSystemMessage(Component.literal("Left CLick Empty"));
                case "RightClickEmpty" ->  Minecraft.getInstance().player.sendSystemMessage(Component.literal("Right Click Empty"));
                case "RightClickBlock" ->  Minecraft.getInstance().player.sendSystemMessage(Component.literal("Right Click Block"));
                default ->  Minecraft.getInstance().player.sendSystemMessage(Component.literal("Irrelevant Button Pressed"));
            }
        }
    }
}
