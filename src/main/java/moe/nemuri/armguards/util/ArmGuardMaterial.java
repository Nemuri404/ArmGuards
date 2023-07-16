package moe.nemuri.armguards.util;

import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public interface ArmGuardMaterial {
	int getDurability();

	int getProtection();

	int getEnchantability();

	SoundEvent getEquipSound();

	Ingredient getRepairIngredient();

	String getName();

	float getToughness();
}