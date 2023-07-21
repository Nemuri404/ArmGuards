package moe.nemuri.armguards.enchantment;

import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.random.RandomGenerator;

public class PoisonBarbsEnchantment extends ArmGuardEnchantment {
	private static final float ATTACK_CHANCE_PER_LEVEL = 0.1f;

	protected PoisonBarbsEnchantment(Rarity weight) {
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
		return 2;
	}

	@Override
	protected boolean canAccept(Enchantment other) {
		return super.canAccept(other) && other != AGEnchantments.DEFLECT;
	}

	@Override
	public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
		RandomGenerator randomGenerator = user.getRandom();
		ItemStack stack = AGUtil.getArmGuard(user);
		if (shouldDamageAttacker(level, randomGenerator)) {
			if (attacker instanceof LivingEntity) {
				attacker.damage(user.getDamageSources().thorns(user), (float) getDamageAmount(level, randomGenerator));
				((LivingEntity) attacker).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, level * 20, 0), user);
			}

			if (stack != null) {
				stack.damage(2, user, entity -> entity.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
			}
		}
	}

	public static boolean shouldDamageAttacker(int level, RandomGenerator random) {
		return level > 0 && random.nextFloat() < ATTACK_CHANCE_PER_LEVEL * (float) level;
	}

	public static int getDamageAmount(int level, RandomGenerator random) {
		return level > 10 ? level - 10 : 1 + random.nextInt(2);
	}

}