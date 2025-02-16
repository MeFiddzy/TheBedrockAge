package net.mefiddzy.bedrockagemod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class AdvancedBedrockFinderItem extends Item {
    public AdvancedBedrockFinderItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            BlockPos posC = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean fb = false;
            for (int i = 0; i < posC.getY() + 64; i++) {
                for (int j = posC.getX() - 5; j <= posC.getX() + 5; j++) {
                    for (int k = posC.getZ() - 5; k <= posC.getZ() + 5; k++) {
                        BlockPos vbp = new BlockPos(j, i, k);
                        BlockState state = pContext.getLevel().getBlockState(vbp);
                        if(isValuableBlock(state)) {
                            outputValuableCoordinates(vbp, player);
                            fb = true;
                        }
                    }
                }
            }
            if(!fb) {
                player.sendSystemMessage(Component.literal("§4Dind't§4 find any old bedrock on a radius of 5 blocks in every direction of 5 blocks X = " + posC.getX()  + " and Z = " + posC.getZ()));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos bp, Player player) {
        player.sendSystemMessage(Component.literal("§2Found§r old bedrock at " + bp.getX() + ' ' + bp.getY() + ' ' + bp.getZ()));
    }

    private boolean isValuableBlock(BlockState state) {
        return   I18n.get(state.getBlock().getDescriptionId()).equals("Old Bedrock");
    }
}
