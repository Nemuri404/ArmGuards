package moe.nemuri.armguards.event;

import moe.nemuri.armguards.item.AGItems;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.impl.client.rendering.WorldRenderContextImpl;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.world.World;

public class AGEvents {
	public static void init() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addAfter(Items.SHIELD, AGItems.LEATHER_ARM_GUARD, AGItems.IRON_ARM_GUARD, AGItems.GOLDEN_ARM_GUARD, AGItems.DIAMOND_ARM_GUARD, AGItems.NETHERITE_ARM_GUARD, AGItems.COPPER_ARM_GUARD, AGItems.TURTLE_ARM_GUARD));

		CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(AGItems.LEATHER_ARM_GUARD, CauldronBehavior.CLEAN_DYEABLE_ITEM);
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (source.isBuiltin() && id.equals(EntityType.PIGLIN_BRUTE.getLootTableId())) {
				LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(0)).conditionally(RandomChanceWithLootingLootCondition.builder(0.025f, 0.01f)).with(ItemEntry.builder(AGItems.GOLDEN_ARM_GUARD));
				tableBuilder.pool(poolBuilder);
			}
		});
	}
}