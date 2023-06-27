package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
	@Shadow
	public abstract void incrementStat(Stat<?> stat);

	protected PlayerEntityMixin(EntityType<? extends PlayerEntity> type, World world) {
		super(type, world);
	}

	@Inject(method = "damageShield", at = @At("HEAD"), cancellable = true)
	private void armGuards$damageShield(float amount, CallbackInfo ci) {
		if (AGUtil.hasArmGuard(this)) {
			ItemStack stack = AGUtil.getArmGuard(this);
			if (!this.getWorld().isClient) {
				this.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
			}
			if (amount >= 3.0f) {
				int i = 4 + MathHelper.floor(amount);
				stack.damage(i, this, player -> player.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
			}
		} else {
			ci.cancel();
		}
	}
}