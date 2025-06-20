package dev.lpsmods.magnet.item;

import dev.lpsmods.magnet.core.ModUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MagnetItem extends Item {
    public double radius;
    public int delay;

    public MagnetItem(ToolMaterial toolMaterial, int delay, double radius, Item.Properties properties) {
        super(properties);
        this.radius = radius;
        this.delay = delay;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.getCooldowns().addCooldown(stack, this.delay);
        if (!level.isClientSide) {
            Vec3 pos = player.getOnPos().getBottomCenter().add(0,1,0);
            ModUtils.teleportMagnetic(this.radius, level, pos, player);
            if (!player.isCreative()) {
                stack.setDamageValue(stack.getDamageValue()+1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(level, player, hand);
    }
}
