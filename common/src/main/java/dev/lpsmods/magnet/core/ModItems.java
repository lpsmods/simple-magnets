package dev.lpsmods.magnet.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import dev.lpsmods.magnet.item.MagnetItem;
import dev.lpsmods.magnet.item.ModTiers;
import net.minecraft.world.item.*;

@RegistryContainer
public class ModItems {
    public static final RegistryEntry<Item> IRON_MAGNET;
    public static final RegistryEntry<Item> GOLD_MAGNET;
    public static final RegistryEntry<Item> COPPER_MAGNET;
    public static final RegistryEntry<Item> DIAMOND_MAGNET;
    public static final RegistryEntry<Item> NETHERITE_MAGNET;

    private static RegistryEntry<Item> magnet(String name, Tier tier, double radius, int delay) {
        return RegistryEntry.item(ModUtils.makeId(name), () -> new MagnetItem(tier, delay, radius, new Item.Properties().stacksTo(1)));
    }

    static {
        IRON_MAGNET = magnet("iron_magnet", Tiers.IRON,3.425, 100);
        GOLD_MAGNET = magnet("gold_magnet", Tiers.GOLD,5.425, 80);
        COPPER_MAGNET = magnet("copper_magnet", ModTiers.COPPER,7.425, 60);
        DIAMOND_MAGNET = magnet("diamond_magnet", Tiers.DIAMOND,9.425, 40);
        NETHERITE_MAGNET = magnet("netherite_magnet", Tiers.NETHERITE,11.425, 20);
    }
}
