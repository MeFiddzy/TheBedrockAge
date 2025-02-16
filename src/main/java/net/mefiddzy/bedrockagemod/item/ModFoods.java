package net.mefiddzy.bedrockagemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties BEDROCK_APPLE = new FoodProperties.Builder().nutrition(13)
            .saturationMod(0.6f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 1), 1f)
            .build();
}
