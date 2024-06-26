package folk.sisby.twirl.mixin;

import folk.sisby.twirl.Twirl;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("UnreachableCode")
@Mixin(ItemStack.class)
public class MixinItemStack {
    private int twirl$style = 0;

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void twirlingUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack self = user.getStackInHand(hand);
        if (EnchantmentHelper.hasAnyEnchantmentsIn(self, Twirl.TWIRLING)) {
            twirl$style = (twirl$style + 1) % Twirl.STYLES.size();
            user.setCurrentHand(hand);
            cir.setReturnValue(TypedActionResult.consume(self));
            if (!self.isIn(Twirl.KEEP_USE)) cir.cancel();
        }
    }

    @Inject(method = "usageTick", at = @At("HEAD"), cancellable = true)
    public void cancelUsageTick(World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        ItemStack self = (ItemStack) (Object) this;
        if (EnchantmentHelper.hasAnyEnchantmentsIn(self, Twirl.TWIRLING) && !self.isIn(Twirl.KEEP_TICK)) {
            ci.cancel();
        }
    }

    @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
    public void twirlingUseAction(CallbackInfoReturnable<UseAction> cir) {
        ItemStack self = (ItemStack) (Object) this;
        if (EnchantmentHelper.hasAnyEnchantmentsIn(self, Twirl.TWIRLING) && !self.isIn(Twirl.KEEP_ACTION)) {
            cir.setReturnValue(Twirl.STYLES.get(twirl$style));
            cir.cancel();
        }
    }

    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    public void twirlingMaxUseTime(LivingEntity user, CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;
        if (EnchantmentHelper.hasAnyEnchantmentsIn(self, Twirl.TWIRLING) && !(self.isIn(Twirl.KEEP_FINISH) || self.isIn(Twirl.KEEP_STOP))) {
            cir.setReturnValue(7200);
            cir.cancel();
        }
    }

    @Inject(method = "onStoppedUsing", at = @At("HEAD"), cancellable = true)
    public void cancelStoppedUsing(World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        ItemStack self = (ItemStack) (Object) this;
        if (EnchantmentHelper.hasAnyEnchantmentsIn(self, Twirl.TWIRLING) && !self.isIn(Twirl.KEEP_STOP)) {
            ci.cancel();
        }
    }

    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    public void cancelFinishUsing(World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack self = (ItemStack) (Object) this;
        if (EnchantmentHelper.hasAnyEnchantmentsIn(self, Twirl.TWIRLING) && !self.isIn(Twirl.KEEP_FINISH)) {
            cir.setReturnValue(self);
            cir.cancel();
        }
    }
}
