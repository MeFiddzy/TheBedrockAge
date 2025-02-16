package net.mefiddzy.bedrockagemod.datagen;

import net.mefiddzy.bedrockagemod.BedrockAgeMod;
import net.mefiddzy.bedrockagemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelGenerator extends ItemModelProvider {
    public ModItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BedrockAgeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BEDROCK_APPLE);
        simpleItem(ModItems.BEDROCK_SOUL);
        simpleItem(ModItems.BEDROCK_INGOT);
        simpleItem(ModItems.BEDROCK_POWDER);
        simpleItem(ModItems.OLD_BEDROCK_FINDER);
        simpleItem(ModItems.ADVANCED_OLD_BEDROCK_FINDER);
        simpleItem(ModItems.UNREFINED_BEDROCK_INGOT);
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(BedrockAgeMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
