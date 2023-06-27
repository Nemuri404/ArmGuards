package moe.nemuri.armguards.util;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketsApi;
import moe.nemuri.armguards.enchantment.AGEnchantments;
import moe.nemuri.armguards.enchantment.ConductionEnchantment;
import moe.nemuri.armguards.item.ArmGuardItem;
import moe.nemuri.armguards.item.ChargeableArmGuardItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class AGUtil {
	public static boolean hasArmGuard(LivingEntity entity) {
		var optional = TrinketsApi.getTrinketComponent(entity);
		if (optional.isPresent()) {
			TrinketComponent comp = optional.get();
			for (var group : comp.getInventory().values()) {
				for (TrinketInventory inv : group.values()) {
					for (int i = 0; i < inv.size(); i++) {
						if (inv.getStack(i).getItem() instanceof ArmGuardItem) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static ItemStack getArmGuard(LivingEntity entity) {
		var optional = TrinketsApi.getTrinketComponent(entity);
		if (optional.isPresent()) {
			TrinketComponent comp = optional.get();
			for (var group : comp.getInventory().values()) {
				for (TrinketInventory inv : group.values()) {
					for (int i = 0; i < inv.size(); i++) {
						if (inv.getStack(i).getItem() instanceof ArmGuardItem) {
							return inv.getStack(i);
						}
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}

	public static boolean isChargeable(ItemStack stack, LivingEntity entity) {
		return stack.getItem() instanceof ChargeableArmGuardItem && !ChargeableArmGuardItem.isCharged(stack) && ConductionEnchantment.shouldCharge(EnchantmentHelper.getLevel(AGEnchantments.CONDUCTION, stack), entity.getRandom());
	}
}