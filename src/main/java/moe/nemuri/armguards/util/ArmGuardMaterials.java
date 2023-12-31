package moe.nemuri.armguards.util;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ArmGuardMaterials implements ArmGuardMaterial {
	LEATHER("leather", 5, 1, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.LEATHER)),
	WOOL("wool", 4, 1, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, 0.0f, () -> Ingredient.ofTag(ItemTags.WOOL)),
	COPPER("copper", 15, 2, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, 0.0f, () -> Ingredient.ofTag(ConventionalItemTags.COPPER_INGOTS)),
	IRON("iron", 15, 2, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f, () -> Ingredient.ofTag(ConventionalItemTags.IRON_INGOTS)),
	GOLD("gold", 7, 2, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0f, 0.0f, () -> Ingredient.ofTag(ConventionalItemTags.GOLD_INGOTS)),
	DIAMOND("diamond", 33, 3, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0f, 0.0f, () -> Ingredient.ofTag(ConventionalItemTags.DIAMONDS)),
	TURTLE("turtle", 25, 2, 9, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.SCUTE)),
	NETHERITE("netherite", 37, 3, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0f, 0.1f, () -> Ingredient.ofTag(ConventionalItemTags.NETHERITE_INGOTS));

	private final String name;
	private final int durabilityMultiplier;
	private final int protection;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Lazy<Ingredient> repairIngredientSupplier;

	ArmGuardMaterials(String name, int durabilityMultiplier, int protection, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.protection = protection;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredientSupplier = new Lazy<>(repairIngredientSupplier);
	}

	@Override
	public int getDurability() {
		return 11 * this.durabilityMultiplier;
	}

	@Override
	public int getProtection() {
		return this.protection;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredientSupplier.get();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
}