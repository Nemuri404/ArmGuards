package moe.nemuri.armguards.item;

import moe.nemuri.armguards.ArmGuards;
import moe.nemuri.armguards.util.ArmGuardMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class AGItems {
	public static final Item LEATHER_ARM_GUARD = register("leather_arm_guard", new DyeableArmGuardItem(ArmGuardMaterials.LEATHER, new QuiltItemSettings()));
	public static final Item IRON_ARM_GUARD = register("iron_arm_guard", new ArmGuardItem(ArmGuardMaterials.IRON, new QuiltItemSettings()));
	public static final Item GOLDEN_ARM_GUARD = register("golden_arm_guard", new ArmGuardItem(ArmGuardMaterials.GOLD, new QuiltItemSettings()));
	public static final Item DIAMOND_ARM_GUARD = register("diamond_arm_guard", new ArmGuardItem(ArmGuardMaterials.DIAMOND, new QuiltItemSettings()));
	public static final Item NETHERITE_ARM_GUARD = register("netherite_arm_guard", new ArmGuardItem(ArmGuardMaterials.NETHERITE, new QuiltItemSettings().fireproof()));
	public static final Item COPPER_ARM_GUARD = register("copper_arm_guard", new ChargeableArmGuardItem(ArmGuardMaterials.COPPER, new QuiltItemSettings()));
	public static final Item TURTLE_ARM_GUARD = register("turtle_arm_guard", new ArmGuardItem(ArmGuardMaterials.TURTLE, new QuiltItemSettings()));

	private static Item register(String id, Item item) {
		return Items.register(ArmGuards.id(id), item);
	}

	public static void init() {
	}
}