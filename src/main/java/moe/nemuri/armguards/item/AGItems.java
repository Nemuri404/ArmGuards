package moe.nemuri.armguards.item;

import moe.nemuri.armguards.util.AGUtil;
import moe.nemuri.armguards.util.ArmGuardMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class AGItems {
	public static final Item LEATHER_ARM_GUARD = register("leather_arm_guard", new DyeableArmGuardItem(ArmGuardMaterials.LEATHER, new QuiltItemSettings()));
	public static final Item IRON_ARM_GUARD = register("iron_arm_guard", new ArmGuardItem(ArmGuardMaterials.IRON, new QuiltItemSettings()));
	public static final Item GOLDEN_ARM_GUARD = register("golden_arm_guard", new ArmGuardItem(ArmGuardMaterials.GOLD, new QuiltItemSettings()));
	public static final Item DIAMOND_ARM_GUARD = register("diamond_arm_guard", new ArmGuardItem(ArmGuardMaterials.DIAMOND, new QuiltItemSettings()));
	public static final Item NETHERITE_ARM_GUARD = register("netherite_arm_guard", new ArmGuardItem(ArmGuardMaterials.NETHERITE, new QuiltItemSettings().fireproof()));
	public static final Item COPPER_ARM_GUARD = register("copper_arm_guard", new ChargeableArmGuardItem(ArmGuardMaterials.COPPER, new QuiltItemSettings()));
	public static final Item TURTLE_ARM_GUARD = register("turtle_arm_guard", new ArmGuardItem(ArmGuardMaterials.TURTLE, new QuiltItemSettings()));
	public static final Item WHITE_WOOL_ARM_GUARD = register("white_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.WHITE, new QuiltItemSettings()));
	public static final Item ORANGE_WOOL_ARM_GUARD = register("orange_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.ORANGE, new QuiltItemSettings()));
	public static final Item MAGENTA_WOOL_ARM_GUARD = register("magenta_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.MAGENTA, new QuiltItemSettings()));
	public static final Item LIGHT_BLUE_WOOL_ARM_GUARD = register("light_blue_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.LIGHT_BLUE, new QuiltItemSettings()));
	public static final Item YELLOW_WOOL_ARM_GUARD = register("yellow_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.YELLOW, new QuiltItemSettings()));
	public static final Item LIME_WOOL_ARM_GUARD = register("lime_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.LIME, new QuiltItemSettings()));
	public static final Item PINK_WOOL_ARM_GUARD = register("pink_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.PINK, new QuiltItemSettings()));
	public static final Item GRAY_WOOL_ARM_GUARD = register("gray_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.GRAY, new QuiltItemSettings()));
	public static final Item LIGHT_GRAY_WOOL_ARM_GUARD = register("light_gray_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.LIGHT_GRAY, new QuiltItemSettings()));
	public static final Item CYAN_WOOL_ARM_GUARD = register("cyan_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.CYAN, new QuiltItemSettings()));
	public static final Item PURPLE_WOOL_ARM_GUARD = register("purple_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.PURPLE, new QuiltItemSettings()));
	public static final Item BLUE_WOOL_ARM_GUARD = register("blue_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.BLUE, new QuiltItemSettings()));
	public static final Item BROWN_WOOL_ARM_GUARD = register("brown_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.BROWN, new QuiltItemSettings()));
	public static final Item GREEN_WOOL_ARM_GUARD = register("green_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.GREEN, new QuiltItemSettings()));
	public static final Item RED_WOOL_ARM_GUARD = register("red_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.RED, new QuiltItemSettings()));
	public static final Item BLACK_WOOL_ARM_GUARD = register("black_wool_arm_guard", new WoolArmGuardItem(ArmGuardMaterials.WOOL, DyeColor.BLACK, new QuiltItemSettings()));

	private static Item register(String id, Item item) {
		return Items.register(AGUtil.id(id), item);
	}

	public static void init() {
	}
}