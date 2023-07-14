package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.item.ArmGuardItem;
import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ClientOnly
@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin {
	@Shadow
	private @Nullable ArmorStandEntity display;

	@Inject(method = "displayStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"), cancellable = true)
	private void armGuards$displayStack(ItemStack stack, CallbackInfo ci) {
		if (stack.getItem() instanceof ArmGuardItem item) {
			item.equipItem(display, stack, true);
			ci.cancel();
			return;
		} else {
			AGUtil.removeArmGuard(display);
		}
	}
}