package moe.nemuri.armguards.enchantment;

import moe.nemuri.armguards.item.ArmGuardItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public abstract class ArmGuardEnchantment extends Enchantment {
	protected ArmGuardEnchantment(Rarity weight) {
		super(weight, EnchantmentTarget.VANISHABLE, new EquipmentSlot[]{EquipmentSlot.OFFHAND});
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof ArmGuardItem;
	}
}