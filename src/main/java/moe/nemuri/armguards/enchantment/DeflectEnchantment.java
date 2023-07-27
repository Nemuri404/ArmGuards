package moe.nemuri.armguards.enchantment;

import net.minecraft.util.random.RandomGenerator;

public class DeflectEnchantment extends ArmGuardEnchantment {
	private static final float ATTACK_CHANCE_PER_LEVEL = 0.2f;

	protected DeflectEnchantment(Rarity weight) {
		super(weight);
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

	public static boolean shouldDeflectProjectile(int level, RandomGenerator random) {
		return level > 0 && random.nextFloat() < ATTACK_CHANCE_PER_LEVEL * (float) level;
	}
}