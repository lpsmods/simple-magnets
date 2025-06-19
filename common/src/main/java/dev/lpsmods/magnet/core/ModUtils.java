package dev.lpsmods.magnet.core;

import dev.lpsmods.magnet.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class ModUtils {
    public static ResourceLocation makeId(String path) {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, path);
    }

    public static void teleportMagnetic(double radius, Level level, Vec3 pos, boolean down) {
        teleportMagnetic(radius, level, pos, down, (entity) -> true);
    }

    public static void teleportMagnetic(double radius, Level level, Vec3 pos, Player player) {
        teleportMagnetic(radius, level, pos, false, (entity) -> {

            // Ensure the player has room for the item.
            if (player != null && entity instanceof ItemEntity) {
                Inventory inv = player.getInventory();
                if (inv.getFreeSlot() == -1) {
                    ItemStack stack = ((ItemEntity) entity).getItem();
                    int slot = inv.getSlotWithRemainingSpace(stack);
                    return slot != -1;
                }
            }
            return true;
        });
    }

    public static void teleportMagnetic(double radius, Level level, Vec3 pos, boolean down, Predicate<Entity> predicate) {
        AABB area = AABB.ofSize(pos, radius * 2, radius * 2, radius * 2);
        level.getEntities(EntityTypeTest.forClass(Entity.class), area, (entity) -> entity.getType().is(ModTags.EntityTypes.MAGNETIC)).forEach((entity)->{
            Vec3 newPos = pos;
            if (down) {
                newPos = newPos.subtract(0, entity.getBbHeight() - 1,0);
            }
            if (!predicate.test(entity)) return;
            entity.setPos(newPos);
        });
    }
}
