package moe.nemuri.armguards.enchantment;

import moe.nemuri.armguards.item.ChargeableArmGuardItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.random.RandomGenerator;

public class ConductionEnchantment extends ArmGuardEnchantment {
	protected ConductionEnchantment(Rarity weight) {
		super(weight);
	}

	@Override
	public int getMinPower(int level) {
		return level * 25;
	}

	@Override
	public int getMaxPower(int level) {
		return this.getMinPower(level) + 50;
	}

	@Override
	public boolean isTreasure() {
		return true;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof ChargeableArmGuardItem;
	}

	public static boolean shouldCharge(int level, RandomGenerator random) {
		return level > 0 || random.nextFloat() < 0.1f;
	}
}