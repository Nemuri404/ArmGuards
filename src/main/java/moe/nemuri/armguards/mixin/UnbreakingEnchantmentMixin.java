package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.item.ArmGuardItem;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.random.RandomGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UnbreakingEnchantment.class)
public abstract class UnbreakingEnchantmentMixin {
	@Inject(method = "shouldPreventDamage", at = @At("RETURN"), cancellable = true)
	private static void armGuards$shouldPreventDamage(ItemStack stack, int level, RandomGenerator random, CallbackInfoReturnable<Boolean> cir) {
		if (stack.getItem() instanceof ArmGuardItem && random.nextFloat() < 0.6f) {
			cir.setReturnValue(false);
		}
	}
}