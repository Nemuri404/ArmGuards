package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.ArmGuards;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(SmithingTemplateItem.class)
public abstract class SmithingTemplateItemMixin {
	private static final Identifier ARM_GUARD_ICON = ArmGuards.id("item/empty_slot_arm_guard");

	@Inject(method = "getArmorIcons", at = @At("RETURN"), cancellable = true)
	private static void armGuards$getArmorIcons(CallbackInfoReturnable<List<Identifier>> cir) {
		List<Identifier> modified = new ArrayList<>(cir.getReturnValue());
		modified.add(ARM_GUARD_ICON);
		cir.setReturnValue(modified);
	}

	@Inject(method = "getNetheriteUpgradeIcons", at = @At("RETURN"), cancellable = true)
	private static void armGuards$getNetheriteUpgradeIcons(CallbackInfoReturnable<List<Identifier>> cir) {
		List<Identifier> modified = new ArrayList<>(cir.getReturnValue());
		modified.add(ARM_GUARD_ICON);
		cir.setReturnValue(modified);
	}
}