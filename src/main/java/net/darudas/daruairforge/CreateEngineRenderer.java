package net.darudas.daruairforge;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;

public class CreateEngineRenderer implements BlockEntityRenderer<CreateEngineBlockEntity> {
    private final ModelBlockRenderer blockRenderer;
    private final BakedModel engineModel;

    public CreateEngineRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher().getModelRenderer();
        this.engineModel = context.getBlockRenderDispatcher().getBlockModel(new ResourceLocation("daruairforge:block/Create_Engine"));
    }

    @Override
    public void render(CreateEngineBlockEntity blockEntity, float partialTicks, PoseStack matrixStack,
                       MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        matrixStack.pushPose();

        BlockState blockState = blockEntity.getBlockState();
        Direction facing = blockState.getValue(CreateEngineBlock.FACING);

        // Rotate the model based on facing direction
        switch (facing) {
            case SOUTH -> matrixStack.mulPose(Vector3f.YP.rotationDegrees(180));
            case WEST -> matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
            case EAST -> matrixStack.mulPose(Vector3f.YP.rotationDegrees(-90));
            default -> {} // No rotation for NORTH
        }

        // Render the main engine block
        blockRenderer.renderModel(matrixStack.last(), buffer.getBuffer(RenderType.solid()),
                blockState, engineModel, 1.0F, 1.0F, 1.0F,
                combinedLight, combinedOverlay, ModelData.EMPTY, null);

        // Render rotating parts (e.g., gears or pistons)
        renderRotatingParts(blockEntity, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);

        // Render gauge
        renderGauge(blockEntity, matrixStack, buffer, combinedLight, combinedOverlay);

        matrixStack.popPose();
    }

    private void renderRotatingParts(CreateEngineBlockEntity blockEntity, float partialTicks, PoseStack matrixStack,
                                     MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
    // Assuming you have a method to get the rotation angle
    float angle = blockEntity.getRotationAngle(partialTicks);

    matrixStack.pushPose();
    matrixStack.translate(0.5, 0.5, 0.5);
    matrixStack.mulPose(Vector3f.Y.rotation(angle));
    matrixStack.translate(-0.5, -0.5, -0.5);

    // Render rotating gears or pistons here
    // Use custom models for these parts

    matrixStack.popPose();
}

    private void renderGauge(CreateEngineBlockEntity blockEntity, PoseStack matrixStack,
                             MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        matrixStack.pushPose();
        matrixStack.translate(0.5, 0.75, 0.5);

        float gaugeAngle = blockEntity.getGaugeAngle();
        matrixStack.mulPose(Vector3f.ZP.rotation(gaugeAngle));

        // Render gauge pointer
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(new ResourceLocation("daruairforge:textures/block/create_engine/gauge_pointer.png")));
        Matrix4f matrix = matrixStack.last().pose();
        vertexConsumer.vertex(matrix, -0.03125F, 0, 0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(combinedOverlay).uv2(combinedLight).normal(0, 1, 0).endVertex();
        vertexConsumer.vertex(matrix, 0.03125F, 0, 0).color(255, 255, 255, 255).uv(1, 0).overlayCoords(combinedOverlay).uv2(combinedLight).normal(0, 1, 0).endVertex();
        vertexConsumer.vertex(matrix, 0.03125F, 0.125F, 0).color(255, 255, 255, 255).uv(1, 1).overlayCoords(combinedOverlay).uv2(combinedLight).normal(0, 1, 0).endVertex();
        vertexConsumer.vertex(matrix, -0.03125F, 0.125F, 0).color(255, 255, 255, 255).uv(0, 1).overlayCoords(combinedOverlay).uv2(combinedLight).normal(0, 1, 0).endVertex();

        matrixStack.popPose();
    }
}