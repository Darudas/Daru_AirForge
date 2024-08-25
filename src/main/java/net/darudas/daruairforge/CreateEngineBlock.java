package net.darudas.daruairforge;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.simibubi.create.AllBlocks.ROTATION_SPEED_CONTROLLER;

public class CreateEngineBlock extends Block implements CreateEngineBlockEntity {
    private static final double STRESS_IMPACT = 8.0; // Match the Mechanical Press
    private static final double STRESS_CAPACITY = 16.0; // Example capacity

    public CreateEngineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CreateEngineBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, CreateEngineBlockEntity.TYPE, CreateEngineBlockEntity::tick);
    }

    public static void register() {
        BlockStressValues.registerProvider("createengine", new BlockStressValues.IStressValueProvider() {
            @Override
            public double getImpact(Block block) {
                return STRESS_IMPACT;
            }

            @Override
            public double getCapacity(Block block) {
                return STRESS_CAPACITY;
            }

            @Override
            public boolean hasImpact(Block block) {
                return true;
            }

            @Override
            public boolean hasCapacity(Block block) {
                return true;
            }

            @Override
            public Couple<Integer> getGeneratedRPM(Block block) {
                return Couple.create(0, 0); // Implement proper RPM logic
            }
        });

        BlockEntityType<CreateEngineBlockEntity> blockEntityType = BlockEntityType.Builder.of(
            CreateEngineBlockEntity::new, 
            Daruairforge.CREATE_ENGINE.get()
        ).build(null);

        ForgeRegistries.BLOCK_ENTITY_TYPES.register(
            new ResourceLocation(Daruairforge.MODID, "create_engine"), 
            blockEntityType
        );
    }

    public static BlockEntityType<BlockEntity> getBlockEntityType() {
        return ForgeRegistries.BLOCK_ENTITY_TYPES.getValue(new ResourceLocation(Daruairforge.MODID, "create_engine"));
    }
}