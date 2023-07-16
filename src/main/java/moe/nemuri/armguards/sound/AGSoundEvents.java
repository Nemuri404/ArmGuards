package moe.nemuri.armguards.sound;

import moe.nemuri.armguards.ArmGuards;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public class AGSoundEvents {
	public static final SoundEvent ITEM_ARM_GUARD_EQUIP_COPPER = register("item.arm_guard.equip_copper");
	public static final SoundEvent ITEM_ARM_GUARD_EQUIP_DIAMOND = register("item.arm_guard.equip_diamond");
	public static final SoundEvent ITEM_ARM_GUARD_EQUIP_GOLD = register("item.arm_guard.equip_gold");
	public static final SoundEvent ITEM_ARM_GUARD_EQUIP_IRON = register("item.arm_guard.equip_iron");
	public static final SoundEvent ITEM_ARM_GUARD_EQUIP_LEATHER = register("item.arm_guard.equip_leather");
	public static final SoundEvent ITEM_ARM_GUARD_EQUIP_NETHERITE = register("item.arm_guard.equip_netherite");
	public static final SoundEvent ITEM_ARM_GUARD_EQUIP_TURTLE = register("item.arm_guard.equip_turtle");

	private static SoundEvent register(String id) {
		return Registry.register(Registries.SOUND_EVENT, ArmGuards.id(id), SoundEvent.createVariableRangeEvent(ArmGuards.id(id)));
	}

	public static void init() {
	}
}