package moe.nemuri.armguards.item;

import moe.nemuri.armguards.util.ArmGuardMaterial;
import net.minecraft.util.DyeColor;

public class WoolArmGuardItem extends ArmGuardItem {
	private final DyeColor color;

	public WoolArmGuardItem(ArmGuardMaterial material, DyeColor color, Settings settings) {
		super(material, settings);
		this.color = color;
	}

	public DyeColor getColor() {
		return this.color;
	}
}