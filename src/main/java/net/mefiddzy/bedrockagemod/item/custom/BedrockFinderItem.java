package net.mefiddzy.bedrockagemod.item.custom;

import net.mefiddzy.bedrockagemod.block.ModBlocks;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MossBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BedrockFinderItem extends Item {
    public BedrockFinderItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            BlockPos posC = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean fb = false;
            for (int i = 0; i < posC.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(posC.below(i));
                if(isValuableBlock(state)) {
                    outputValuableCoordinates(posC.below(i), player);
                    fb = true;
                    break;
                }
            }
            if(!fb) {
                player.sendSystemMessage(Component.literal("§4Dind't§4 find any old bedrock on X = " + posC.getX() + " and Z = " + posC.getZ()));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Finds§1§o old bedrock§r on your X and Z (only below your Y)."));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputValuableCoordinates(BlockPos bp, Player player) {
        player.sendSystemMessage(Component.literal("§2Found§r old bedrock at " + bp.getX() + ' ' + bp.getY() + ' ' + bp.getZ()));
    }

    private boolean isValuableBlock(BlockState state) {
        return   I18n.get(state.getBlock().getDescriptionId()).equals("Old Bedrock");
    }
}
