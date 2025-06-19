package dev.lpsmods.magnet.core;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import dev.lpsmods.magnet.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

@RegistryContainer
public class ModCreativeTabs {
    public static final RegistryEntry<CreativeModeTab> MAIN;

    static {
        MAIN = RegistryEntry.creativeModeTab(ModUtils.makeId("simple_magnets"), builder -> {
            builder.title(Component.translatable("itemGroup."+Constants.MOD_ID));
            builder.icon(() -> {
                return new ItemStack(ModItems.DIAMOND_MAGNET.get());
            });
            builder.displayItems((CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output out) -> {
                out.accept(ModItems.IRON_MAGNET.get());
                out.accept(ModItems.GOLD_MAGNET.get());
                out.accept(ModItems.COPPER_MAGNET.get());
                out.accept(ModItems.DIAMOND_MAGNET.get());
                out.accept(ModItems.NETHERITE_MAGNET.get());
                out.accept(ModBlocks.COPPER_MAGNET_BLOCK.get());
                out.accept(ModBlocks.DIAMOND_MAGNET_BLOCK.get());
                out.accept(ModBlocks.GOLD_MAGNET_BLOCK.get());
                out.accept(ModBlocks.IRON_MAGNET_BLOCK.get());
                out.accept(ModBlocks.NETHERITE_MAGNET_BLOCK.get());
            }).build();
        });
    }
}
