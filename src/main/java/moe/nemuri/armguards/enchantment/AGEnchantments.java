package moe.nemuri.armguards.enchantment;

import moe.nemuri.armguards.ArmGuards;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AGEnchantments {
	public static final Enchantment CONDUCTION = register("conduction", new ConductionEnchantment(Enchantment.Rarity.RARE));
	public static final Enchantment DEFLECT = register("deflect", new DeflectEnchantment(Enchantment.Rarity.UNCOMMON));
	public static final Enchantment POISON_BARBS = register("poison_barbs", new PoisonBarbsEnchantment(Enchantment.Rarity.VERY_RARE));

	private static Enchantment register(String id, Enchantment enchantment) {
		return Registry.register(Registries.ENCHANTMENT, ArmGuards.id(id), enchantment);
	}

	public static void init() {
	}
}