package net.darudas.daruairforge;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CreateEngineBlockEntity extends KineticBlockEntity {

    public CreateEngineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public float getGeneratedSpeed() {
        // Hier implementieren Sie die Logik für die Geschwindigkeitserzeugung
        return 32; // Beispielwert
    }

    @Override
    public float calculateStressApplied() {
        // Hier implementieren Sie die Logik für die Stressberechnung
        return 4; // Beispielwert
    }
}