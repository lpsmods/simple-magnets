package dev.lpsmods.magnet.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = tag("incorrect_for_copper_tool");

        private static TagKey<Block> tag(String path) {
            return TagKey.create(Registries.BLOCK, ModUtils.makeId(path));
        }
    }

    public class Items {
        public static final TagKey<Item> COPPER_TOOL_MATERIALS = tag("copper_tool_materials");

        private static TagKey<Item> tag(String path) {
            return TagKey.create(Registries.ITEM, ModUtils.makeId(path));
        }
    }

    public class EntityTypes {
        public static final TagKey<EntityType<?>> MAGNETIC = tag("magnetic");

        private static TagKey<EntityType<?>> tag(String path) {
            return TagKey.create(Registries.ENTITY_TYPE, ModUtils.makeId(path));
        }
    }
}
