package net.mefiddzy.bedrockagemod.item;

import net.mefiddzy.bedrockagemod.BedrockAgeMod;
import net.mefiddzy.bedrockagemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BedrockAgeMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BEDROCK_AGE_TAB = CREATIVE_MODE_TABS.register("bedrock_age_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BEDROCK_INGOT.get()))
                    .title(Component.translatable("creativetab.bedrock_age_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.BEDROCK_INGOT.get());
                        pOutput.accept(ModItems.UNREFINED_BEDROCK_INGOT.get());
                        pOutput.accept(ModItems.BEDROCK_POWDER.get());
                        pOutput.accept(ModBlocks.WEAK_BEDROCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
