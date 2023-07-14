package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
			AGUtil.chargeArmGuard(AGUtil.getArmGuard(livingEntity), livingEntity);
			AGUtil.chargeArmGuard(livingEntity.getMainHandStack(), livingEntity);
			AGUtil.chargeArmGuard(livingEntity.getOffHandStack(), livingEntity);
		} else {
			ci.cancel();
		}
	}
}