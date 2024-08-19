
package net.darudas.daruairforge;

import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.darudas.daruairforge.blockentity.CreateEngineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.darudas.daruairforge.registry.DaruAirForgeBlockEntities;


public class CreateEngineBlock extends RotatedPillarKineticBlock implements IBE<CreateEngineBlockEntity> {

    public CreateEngineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(AXIS);
    }

    @Override
    public Class<CreateEngineBlockEntity> getBlockEntityClass() {
        return CreateEngineBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends CreateEngineBlockEntity> getBlockEntityType() {
        return DaruAirForgeBlockEntities.CREATE_ENGINE.get();
    }
}