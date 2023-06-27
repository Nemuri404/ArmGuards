package moe.nemuri.armguards.enchantment;

import moe.nemuri.armguards.ArmGuards;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AGEnchantments {
	public static final Enchantment CONDUCTION = register("conduction", new DeflectEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.OFFHAND));
	public static final Enchantment DEFLECT = register("deflect", new DeflectEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.OFFHAND));

	private static Enchantment register(String id, Enchantment enchantment) {
		return Registry.register(Registries.ENCHANTMENT, ArmGuards.id(id), enchantment);
	}

	public static void init() {
	}
}