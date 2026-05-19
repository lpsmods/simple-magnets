import { CustomComponentParameters, ItemComponentUseEvent } from "@minecraft/server";

export interface MagnetItemComponentOptions {}

export class MagnetItemComponent {
  static typeId = "lpsm_magnet:magnet";

  radius: number;

  constructor(radius: number) {
    this.radius = radius;
    this.onUse = this.onUse.bind(this);
  }
  onUse(event: ItemComponentUseEvent, args: CustomComponentParameters): void {
    const options = args.params as MagnetItemComponentOptions;
    const { x, y, z } = event.source.location;
    const dim = event.source.dimension;
    (dim.runCommand(`execute positioned ${x} ${y} ${z} run tp @e[type=item,r=${this.radius}] ~ ~ ~`),
      dim.runCommand(`execute positioned ${x} ${y} ${z} run tp @e[type=xp_orb,r=${this.radius}] ~ ~ ~`));
  }
}
