package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.item.AGItems;
import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {
	@Inject(method = "wearsGoldArmor", at = @At("RETURN"), cancellable = true)
	private static void armGuards$wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
		if (AGUtil.getArmGuard(entity).isOf(AGItems.GOLDEN_ARM_GUARD)) {
			cir.setReturnValue(true);
		}
	}
}