package folk.sisby.twirl.mixin.client;

import folk.sisby.twirl.Twirl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
    @Inject(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V", at = @At("HEAD"))
    public void renderItemHead(
        LivingEntity entity, ItemStack item, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, CallbackInfo ci
    ) {
        matrices.push();
        if (entity == null) return;
        int twirlingLevel = Twirl.getLevelIn(item, Twirl.TWIRLING);
        boolean mainHand = entity.getActiveItem() == item;
        if (twirlingLevel > 0 && entity.isUsingItem() && (mainHand || Twirl.getLevelIn(entity.getActiveItem(), Twirl.TWIRLING) > 0)) {
            float tickDelta = MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(false);
            matrices.translate(leftHanded ? -0.1f : 0.1f, 0.0f, 0.0f);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(leftHanded ? -15f : 15f));
            RotationAxis axis = item.isIn(Twirl.ROTATE_Z) && mainHand ? RotationAxis.NEGATIVE_Z : RotationAxis.NEGATIVE_X;
            matrices.multiply(axis.rotationDegrees(((float) entity.getItemUseTime() + tickDelta) * twirlingLevel * Twirl.TWIRL_SPEED));
        }
    }

    @Inject(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V", at = @At("TAIL"))
    public void renderItemTail(
        LivingEntity entity, ItemStack item, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, CallbackInfo ci
    ) {
        matrices.pop();
    }
}
