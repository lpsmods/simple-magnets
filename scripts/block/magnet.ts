import {
  BlockComponentPlayerInteractEvent,
  BlockComponentTickEvent,
  CustomComponentParameters,
  Dimension,
  Vector3,
} from "@minecraft/server";
import { directionToOffset } from "../utils";
import { BlockStateSuperset } from "@minecraft/vanilla-data";

export interface MagnetBlockComponentOptions {}

export class MagnetBlockComponent {
  static typeId = "lpsm_magnet:magnet";

  radius: number;
  poweredState: keyof BlockStateSuperset;

  constructor(radius: number, poweredState: string = "magnet:powered") {
    this.radius = radius;
    this.poweredState = poweredState as keyof BlockStateSuperset;
    this.onPlayerInteract = this.onPlayerInteract.bind(this);
    this.onTick = this.onTick.bind(this);
  }

  onPlayerInteract(event: BlockComponentPlayerInteractEvent, args: CustomComponentParameters): void {
    const options = args.params as MagnetBlockComponentOptions;
    const e = event.block.permutation.getState(this.poweredState) as boolean;
    event.block.setPermutation(event.block.permutation.withState(this.poweredState, !e));
  }
  teleport(dim: Dimension, loc: Vector3, dir: string): void {
    var pos = directionToOffset(dir);
    if (!pos) return;
    var { x, y, z } = pos;
    dim.runCommand(
      `execute positioned ${loc.x} ${loc.y} ${loc.z} run tp @e[type=item,r=${this.radius}] ~${x} ~${y} ~${z}`
    );
    dim.runCommand(
      `execute positioned ${loc.x} ${loc.y} ${loc.z} run tp @e[type=xp_orb,r=${this.radius}] ~${x} ~${y} ~${z}`
    );
  }
  onTick(event: BlockComponentTickEvent, args: CustomComponentParameters): void {
    const options = args.params as MagnetBlockComponentOptions;
    if (!event.block.permutation.getState(this.poweredState)) return;
    const dir = event.block.permutation.getState("minecraft:facing_direction") as string;
    this.teleport(event.dimension, event.block.location, dir);
  }
}
