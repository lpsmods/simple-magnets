package dev.lpsmods.magnet.item;


import dev.lpsmods.magnet.core.ModTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterial {
    public static final ToolMaterial COPPER;

    static {
        COPPER = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_COPPER_TOOL, 185, 12.0f, 0.0f, 22, ModTags.Items.COPPER_TOOL_MATERIALS);
    }
}
