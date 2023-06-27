package moe.nemuri.armguards.item;

import net.minecraft.item.DyeableItem;

public class DyeableArmGuardItem extends ArmGuardItem implements DyeableItem {
	public DyeableArmGuardItem(ArmGuardMaterial material, Settings settings) {
		super(material, settings);
	}
}