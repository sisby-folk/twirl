package folk.sisby.twirl.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
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

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
    @WrapMethod(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/world/World;III)V")
    public void renderItemHead(LivingEntity entity, ItemStack item, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, World world, int light, int overlay, int seed, Operation<Void> original) {
        int twirlingLevel = Twirl.getLevelIn(item, Twirl.TWIRLING);
        if (entity != null && twirlingLevel > 0 && entity.isUsingItem() && (entity.getActiveItem() == item || Twirl.getLevelIn(entity.getActiveItem(), Twirl.TWIRLING) > 0)) {
            matrices.push();
            float tickDelta = MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(false);
            matrices.translate(leftHanded ? -0.1f : 0.1f, 0.0f, 0.0f);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(leftHanded ? -15f : 15f));
            RotationAxis axis = item.isIn(Twirl.ROTATE_Z) && entity.getActiveItem() == item ? RotationAxis.NEGATIVE_Z : RotationAxis.NEGATIVE_X;
            matrices.multiply(axis.rotationDegrees(((float) entity.getItemUseTime() + tickDelta) * twirlingLevel * Twirl.TWIRL_SPEED));
            original.call(entity, item, renderMode, leftHanded, matrices, vertexConsumers, world, light, overlay, seed);
            matrices.pop();
        } else {
            original.call(entity, item, renderMode, leftHanded, matrices, vertexConsumers, world, light, overlay, seed);
        }
    }
}
