package moe.nemuri.armguards.enchantment;

import moe.nemuri.armguards.item.ArmGuardItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.random.RandomGenerator;

public class DeflectEnchantment extends Enchantment {
	protected DeflectEnchantment(Rarity weight, EquipmentSlot... slots) {
		super(weight, EnchantmentTarget.VANISHABLE, slots);
	}

	@Override
	public int getMinPower(int level) {
		return 10 + 20 * (level - 1);
	}

	@Override
	public int getMaxPower(int level) {
		return super.getMinPower(level) + 50;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof ArmGuardItem;
	}

	public static boolean shouldDeflectProjectile(int level, RandomGenerator random) {
		if (level <= 0) {
			return false;
		}
		return random.nextFloat() < 0.15f * (float) level;
	}
}