package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Inject(method = "onStruckByLightning", at = @At("TAIL"), cancellable = true)
	private void armGuards$onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
		if ((Entity) (Object) this instanceof LivingEntity entity) {
			AGUtil.chargeArmGuard(AGUtil.getArmGuard(entity), entity);
			AGUtil.chargeArmGuard(entity.getMainHandStack(), entity);
			AGUtil.chargeArmGuard(entity.getOffHandStack(), entity);
		} else {
			ci.cancel();
		}
	}
}