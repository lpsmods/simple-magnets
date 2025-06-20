package dev.lpsmods.magnet.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import dev.lpsmods.magnet.block.MagnetBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

@RegistryContainer
public class ModBlocks {
    public static final RegistryEntry<Block> IRON_MAGNET_BLOCK;
    public static final RegistryEntry<Block> GOLD_MAGNET_BLOCK;
    public static final RegistryEntry<Block> COPPER_MAGNET_BLOCK;
    public static final RegistryEntry<Block> DIAMOND_MAGNET_BLOCK;
    public static final RegistryEntry<Block> NETHERITE_MAGNET_BLOCK;

    private static RegistryEntry<Block> magnetBlock(String name, float radius, int delay) {
        return RegistryEntry.blockWithItem(ModUtils.makeId(name), properties -> new MagnetBlock(radius, delay, properties), () -> BlockBehaviour.Properties.of().destroyTime(1.5f).forceSolidOn().pushReaction(PushReaction.NORMAL).randomTicks());
    }

    static {
        IRON_MAGNET_BLOCK = magnetBlock("iron_magnet_block", 3.425F, 10);
        GOLD_MAGNET_BLOCK = magnetBlock("gold_magnet_block", 5.425F, 8);
        COPPER_MAGNET_BLOCK = magnetBlock("copper_magnet_block", 7.425F, 6);
        DIAMOND_MAGNET_BLOCK = magnetBlock("diamond_magnet_block", 9.425F, 4);
        NETHERITE_MAGNET_BLOCK = magnetBlock("netherite_magnet_block", 11.425F, 2);
    }
}
