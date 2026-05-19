import { BlockComponentRegistry, ItemComponentRegistry } from "@minecraft/server";
import { MagnetBlockComponent } from "./block/magnet";
import { MagnetItemComponent } from "./item/magnet";
import { AddonUtils, GuideBookComponent } from "@lpsmods/mc-utils";
import { pages } from "./guide/main";

export function registerItemComponents(reg: ItemComponentRegistry): void {
  const id = AddonUtils.makeId("guide_book");
  reg.registerCustomComponent(id, new GuideBookComponent(pages));
  GuideBookComponent.setup(id);
  reg.registerCustomComponent("magnet:iron_magnet", new MagnetItemComponent(3.425));
  reg.registerCustomComponent("magnet:gold_magnet", new MagnetItemComponent(5.425));
  reg.registerCustomComponent("magnet:copper_magnet", new MagnetItemComponent(7.425));
  reg.registerCustomComponent("magnet:diamond_magnet", new MagnetItemComponent(9.425));
  reg.registerCustomComponent("magnet:netherite_magnet", new MagnetItemComponent(11.425));
}

export function registerBlockComponents(reg: BlockComponentRegistry): void {
  reg.registerCustomComponent("magnet:iron_magnet_block", new MagnetBlockComponent(3.425));
  reg.registerCustomComponent("magnet:gold_magnet_block", new MagnetBlockComponent(5.425));
  reg.registerCustomComponent("magnet:copper_magnet_block", new MagnetBlockComponent(7.425));
  reg.registerCustomComponent("magnet:diamond_magnet_block", new MagnetBlockComponent(9.425));
  reg.registerCustomComponent("magnet:netherite_magnet_block", new MagnetBlockComponent(11.425));
}
