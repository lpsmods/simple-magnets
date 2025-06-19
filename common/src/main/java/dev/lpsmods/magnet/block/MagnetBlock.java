package dev.lpsmods.magnet.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lpsmods.magnet.core.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;

public class MagnetBlock extends DirectionalBlock {
    public static final MapCodec<MagnetBlock> CODEC = RecordCodecBuilder.mapCodec((p_308861_) -> {
        return p_308861_.group(
                Codec.FLOAT.fieldOf("radius").forGetter((blk) -> {return blk.radius;}),
                Codec.INT.fieldOf("delay").forGetter((blk) -> {return blk.delay;})
                , propertiesCodec()).apply(p_308861_, MagnetBlock::new);
    });

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public float radius;
    public int delay;

    public MagnetBlock(float radius, int delay, BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(POWERED, false));
        this.radius = radius;
        this.delay = delay;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite())).setValue(POWERED, false);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, POWERED});
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean pMovedByPiston) {
        if (level.isClientSide) {
            return;
        }
        boolean bl = state.getValue(POWERED);
        if (bl != level.hasNeighborSignal(pos)) {
            if (bl) {
                level.scheduleTick(pos, this, this.delay);
            } else {
                level.setBlock(pos, (BlockState)state.cycle(POWERED), 2);
                Direction facing = state.getValue(FACING);
                Vec3 facingPos = pos.relative(facing).getBottomCenter();
                ModUtils.teleportMagnetic(this.radius, level, facingPos, facing.equals(Direction.DOWN));
            }
        }
    }

    protected void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if ((Boolean)pState.getValue(POWERED) && !pLevel.hasNeighborSignal(pPos)) {
            pLevel.setBlock(pPos, (BlockState)pState.cycle(POWERED), 2);
        }
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }
}
