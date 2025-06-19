package dev.lpsmods.magnet.item;

import dev.lpsmods.magnet.core.ModUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MagnetItem extends TieredItem {
    public double radius;
    public int delay;

    public MagnetItem(Tier tier, int delay, double radius, Item.Properties properties) {
        super(tier, properties);
        this.radius = radius;
        this.delay = delay;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        player.getCooldowns().addCooldown(stack.getItem(), this.delay);
        if (!level.isClientSide) {
            Vec3 pos = player.getOnPos().getBottomCenter().add(0,1,0);
            ModUtils.teleportMagnetic(this.radius, level, pos, player);
            if (!player.isCreative()) {
                stack.setDamageValue(stack.getDamageValue()+1);
            }
            return InteractionResultHolder.success(stack);
        }
        return super.use(level, player, usedHand);
    }
}
