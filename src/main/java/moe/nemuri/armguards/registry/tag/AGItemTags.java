package moe.nemuri.armguards.registry.tag;

import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public final class AGItemTags {
	public static final TagKey<Item> ARM_GUARDS = create("arm_guards");
	public static final TagKey<Item> TRIMMABLE_ARM_GUARDS = create("trimmable_arm_guards");
	public static final TagKey<Item> WOOL_ARM_GUARDS = create("wool_arm_guards");

	private static TagKey<Item> create(String id) {
		return TagKey.of(RegistryKeys.ITEM, AGUtil.id(id));
	}
}