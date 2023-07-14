package moe.nemuri.armguards.util;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import moe.nemuri.armguards.enchantment.AGEnchantments;
import moe.nemuri.armguards.enchantment.ConductionEnchantment;
import moe.nemuri.armguards.item.ArmGuardItem;
import moe.nemuri.armguards.item.ChargeableArmGuardItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;

public class AGUtil {
	public static boolean hasArmGuard(LivingEntity entity) {
		if (TrinketsApi.getTrinketComponent(entity).isPresent()) {
			return TrinketsApi.getTrinketComponent(entity).get().isEquipped(stack -> stack.getItem() instanceof ArmGuardItem);
		}
		return false;
	}

	public static ItemStack getArmGuard(LivingEntity entity) {
		if (hasArmGuard(entity)) {
			for (var pair : TrinketsApi.getTrinketComponent(entity).get().getEquipped(stack -> stack.getItem() instanceof ArmGuardItem)) {
				return pair.getRight();
			}
		}
		return ItemStack.EMPTY;
	}

	public static void chargeArmGuard(ItemStack stack, LivingEntity entity) {
		if (stack.getItem() instanceof ChargeableArmGuardItem && !ChargeableArmGuardItem.isCharged(stack) && ConductionEnchantment.shouldCharge(EnchantmentHelper.getLevel(AGEnchantments.CONDUCTION, stack), entity.getRandom())) {
			ChargeableArmGuardItem.setCharged(stack, true);
		}
	}
}