package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.item.ChargeableArmGuardItem;
import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Inject(method = "onStruckByLightning", at = @At("TAIL"), cancellable = true)
	private void armGuards$onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
		Entity entity = (Entity) (Object) this;
		if (entity instanceof LivingEntity livingEntity) {
			if (AGUtil.hasArmGuard(livingEntity)) {
				ItemStack stack = AGUtil.getArmGuard(livingEntity);
				AGUtil.chargeArmGuard(stack, livingEntity);
			} else if (livingEntity.getMainHandStack().getItem() instanceof ChargeableArmGuardItem) {
				ItemStack stack = livingEntity.getMainHandStack();
				AGUtil.chargeArmGuard(stack, livingEntity);
			} else if (livingEntity.getOffHandStack().getItem() instanceof ChargeableArmGuardItem) {
				ItemStack stack = livingEntity.getOffHandStack();
				AGUtil.chargeArmGuard(stack, livingEntity);
			}
		} else {
			ci.cancel();
		}
	}
}