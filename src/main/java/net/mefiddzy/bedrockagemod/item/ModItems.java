package net.mefiddzy.bedrockagemod.item;

import net.mefiddzy.bedrockagemod.BedrockAgeMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BedrockAgeMod.MOD_ID);

    public static final RegistryObject<Item> BEDROCK_POWDER = ITEMS.register("bedrock_powder",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UNREFINED_BEDROCK_INGOT = ITEMS.register("unrefined_bedrock_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BEDROCK_INGOT = ITEMS.register("bedrock_ingot",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
