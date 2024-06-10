package folk.sisby.twirl.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import folk.sisby.twirl.Twirl;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings("UnreachableCode")
@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    @ModifyExpressionValue(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean fastTwirling(boolean original) {
        ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;
        return original && !EnchantmentHelper.hasAnyEnchantmentsIn(self.getActiveItem(), Twirl.TWIRLING);
    }

    @ModifyExpressionValue(method = "canStartSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"))
    private boolean sprintTwirling(boolean original) {
        ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;
        return original && !EnchantmentHelper.hasAnyEnchantmentsIn(self.getActiveItem(), Twirl.TWIRLING);
    }
}
