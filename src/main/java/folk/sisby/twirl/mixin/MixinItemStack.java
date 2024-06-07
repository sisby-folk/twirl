package folk.sisby.twirl.mixin;

import folk.sisby.twirl.Twirl;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("UnreachableCode")
@Mixin(ItemStack.class)
public class MixinItemStack {
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;use(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/TypedActionResult;"), cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack self = (ItemStack) (Object) this;
        if (EnchantmentHelper.hasAnyEnchantmentsIn(self, Twirl.TWIRLING)) {
            user.sendMessage(Text.of("Twirl Twirl Twirl!!!"));
            cir.setReturnValue(TypedActionResult.pass(self));
            cir.cancel();
        }
    }
}
