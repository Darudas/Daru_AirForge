package net.darudas.daruairforge;

import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CreateEngineBlockEntity extends KineticBlockEntity {

    public CreateEngineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public float calculateStressApplied() {
        return BlockStressValues.getImpactFor(KineticBlockEntityType.MECHANICAL_PRESS);
    }

    @Override
    public float getGeneratedSpeed() {
        return 32; // Example speed value
    }
}
