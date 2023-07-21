package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
	@Inject(method = "onUserDamaged", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;forEachEnchantment(Lnet/minecraft/enchantment/EnchantmentHelper$Consumer;Lnet/minecraft/item/ItemStack;)V"))
	private static void armGuards$onUserDamaged(LivingEntity user, Entity attacker, CallbackInfo ci) {
		EnchantmentHelper.forEachEnchantment((enchantment, level) -> enchantment.onUserDamaged(user, attacker, level), AGUtil.getArmGuard(user));
	}
}