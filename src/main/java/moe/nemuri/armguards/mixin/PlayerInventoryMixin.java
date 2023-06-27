package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
	@Shadow
	@Final
	public PlayerEntity player;

	@Inject(method = "damageArmor", at = @At("TAIL"), cancellable = true)
	private void armGuards$damageArmor(DamageSource damageSource, float amount, int[] slots, CallbackInfo ci) {
		if (AGUtil.hasArmGuard(player) && !(amount <= 0.0f)) {
			amount /= 4.0f;
			if (amount < 1.0f) {
				amount = 1.0f;
			}
			ItemStack stack = AGUtil.getArmGuard(player);
			if ((!damageSource.isTypeIn(DamageTypeTags.IS_FIRE) || !stack.getItem().isFireproof())) {
				stack.damage((int) amount, this.player, player -> player.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
			}
		} else {
			ci.cancel();
		}
	}
}