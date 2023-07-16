package moe.nemuri.armguards.item;

import moe.nemuri.armguards.util.ArmGuardMaterial;
import net.minecraft.item.DyeableItem;

public class DyeableArmGuardItem extends ArmGuardItem implements DyeableItem {
	public DyeableArmGuardItem(ArmGuardMaterial material, Settings settings) {
		super(material, settings);
	}
}