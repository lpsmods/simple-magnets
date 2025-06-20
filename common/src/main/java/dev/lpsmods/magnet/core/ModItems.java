package dev.lpsmods.magnet.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import dev.lpsmods.magnet.item.MagnetItem;
import dev.lpsmods.magnet.item.ModToolMaterial;
import net.minecraft.world.item.*;

@RegistryContainer
public class ModItems {
    public static final RegistryEntry<Item> IRON_MAGNET;
    public static final RegistryEntry<Item> GOLD_MAGNET;
    public static final RegistryEntry<Item> COPPER_MAGNET;
    public static final RegistryEntry<Item> DIAMOND_MAGNET;
    public static final RegistryEntry<Item> NETHERITE_MAGNET;

    private static RegistryEntry<Item> magnet(String name, ToolMaterial toolMaterial, double radius, int delay) {
        return RegistryEntry.item(ModUtils.makeId(name), properties -> new MagnetItem(toolMaterial, delay, radius, properties), () -> new Item.Properties().stacksTo(1));
    }

    static {
        IRON_MAGNET = magnet("iron_magnet", ToolMaterial.IRON,3.425, 100);
        GOLD_MAGNET = magnet("gold_magnet", ToolMaterial.GOLD,5.425, 80);
        COPPER_MAGNET = magnet("copper_magnet", ModToolMaterial.COPPER,7.425, 60);
        DIAMOND_MAGNET = magnet("diamond_magnet", ToolMaterial.DIAMOND,9.425, 40);
        NETHERITE_MAGNET = magnet("netherite_magnet", ToolMaterial.NETHERITE,11.425, 20);
    }
}
