package moe.nemuri.armguards.event;

import moe.nemuri.armguards.item.AGItems;
import moe.nemuri.armguards.util.AGUtil;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.quiltmc.qsl.villager.api.TradeOfferHelper;

public class AGEvents {
	public static final RegistryKey<ItemGroup> ARM_GUARDS = RegistryKey.of(RegistryKeys.ITEM_GROUP, AGUtil.id("arm_guards"));

	public static void init() {
		Registry.register(Registries.ITEM_GROUP, ARM_GUARDS,
			FabricItemGroup.builder().name(Text.translatable("itemGroup.arm_guards.armGuards")).icon(() -> new ItemStack(AGItems.GOLDEN_ARM_GUARD))
				.entries((params, collector) -> {
					collector.addItem(AGItems.LEATHER_ARM_GUARD);
					collector.addItem(AGItems.COPPER_ARM_GUARD);
					collector.addItem(AGItems.IRON_ARM_GUARD);
					collector.addItem(AGItems.GOLDEN_ARM_GUARD);
					collector.addItem(AGItems.DIAMOND_ARM_GUARD);
					collector.addItem(AGItems.NETHERITE_ARM_GUARD);
					collector.addItem(AGItems.TURTLE_ARM_GUARD);
					collector.addItem(AGItems.WHITE_WOOL_ARM_GUARD);
					collector.addItem(AGItems.LIGHT_GRAY_WOOL_ARM_GUARD);
					collector.addItem(AGItems.GRAY_WOOL_ARM_GUARD);
					collector.addItem(AGItems.BLACK_WOOL_ARM_GUARD);
					collector.addItem(AGItems.BROWN_WOOL_ARM_GUARD);
					collector.addItem(AGItems.RED_WOOL_ARM_GUARD);
					collector.addItem(AGItems.ORANGE_WOOL_ARM_GUARD);
					collector.addItem(AGItems.YELLOW_WOOL_ARM_GUARD);
					collector.addItem(AGItems.LIME_WOOL_ARM_GUARD);
					collector.addItem(AGItems.GREEN_WOOL_ARM_GUARD);
					collector.addItem(AGItems.CYAN_WOOL_ARM_GUARD);
					collector.addItem(AGItems.LIGHT_BLUE_WOOL_ARM_GUARD);
					collector.addItem(AGItems.BLUE_WOOL_ARM_GUARD);
					collector.addItem(AGItems.PURPLE_WOOL_ARM_GUARD);
					collector.addItem(AGItems.MAGENTA_WOOL_ARM_GUARD);
					collector.addItem(AGItems.PINK_WOOL_ARM_GUARD);
				}).hideScrollbar().build());

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addAfter(Items.SHIELD, AGItems.LEATHER_ARM_GUARD, AGItems.COPPER_ARM_GUARD, AGItems.IRON_ARM_GUARD, AGItems.GOLDEN_ARM_GUARD, AGItems.DIAMOND_ARM_GUARD, AGItems.NETHERITE_ARM_GUARD, AGItems.TURTLE_ARM_GUARD, AGItems.WHITE_WOOL_ARM_GUARD));

		CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(AGItems.LEATHER_ARM_GUARD, CauldronBehavior.CLEAN_DYEABLE_ITEM);

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (source.isBuiltin() && id.equals(EntityType.PIGLIN_BRUTE.getLootTableId())) {
				LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(0)).conditionally(RandomChanceWithLootingLootCondition.builder(0.1f, 0.01f)).with(ItemEntry.builder(AGItems.GOLDEN_ARM_GUARD));
				tableBuilder.pool(poolBuilder);
			}
		});

		TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 2, factories -> factories.add(new TradeOffers.SellItemFactory(new ItemStack(AGItems.IRON_ARM_GUARD), 4, 1, 12, 5, 0.2f)));
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 4, factories -> factories.add(new TradeOffers.SellEnchantedToolFactory(AGItems.DIAMOND_ARM_GUARD, 8, 3, 15, 0.2f)));
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 3, factories -> factories.add(new TradeOffers.SellDyedArmorFactory(AGItems.LEATHER_ARM_GUARD, 5, 12, 5)));

		BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, AGItems.TURTLE_ARM_GUARD, Potions.TURTLE_MASTER);
	}
}