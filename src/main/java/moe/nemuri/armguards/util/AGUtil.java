package moe.nemuri.armguards.util;

import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketsApi;
import moe.nemuri.armguards.enchantment.AGEnchantments;
import moe.nemuri.armguards.enchantment.ConductionEnchantment;
import moe.nemuri.armguards.item.ArmGuardItem;
import moe.nemuri.armguards.item.ChargeableArmGuardItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.event.GameEvent;

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

	public static void removeArmGuard(ArmorStandEntity entity) {
		var optional = TrinketsApi.getTrinketComponent(entity);
		if (optional.isPresent()) {
			TrinketComponent comp = optional.get();
			for (var group : comp.getInventory().values()) {
				for (TrinketInventory inv : group.values()) {
					for (int i = 0; i < inv.size(); i++) {
						if (inv.getStack(i).getItem() instanceof ArmGuardItem) {
							inv.removeStack(i);
						}
					}
				}
			}
		}
	}
}