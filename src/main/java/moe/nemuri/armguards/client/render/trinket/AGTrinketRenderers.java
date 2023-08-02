package moe.nemuri.armguards.client.render.trinket;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import moe.nemuri.armguards.item.AGItems;
import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.resource.loader.api.reloader.SimpleSynchronousResourceReloader;

@ClientOnly
public class AGTrinketRenderers implements SimpleSynchronousResourceReloader {
	@Override
	public void reload(ResourceManager manager) {
		register(AGItems.LEATHER_ARM_GUARD);
		register(AGItems.GOLDEN_ARM_GUARD);
		register(AGItems.IRON_ARM_GUARD);
		register(AGItems.DIAMOND_ARM_GUARD);
		register(AGItems.NETHERITE_ARM_GUARD);
		register(AGItems.COPPER_ARM_GUARD);
		register(AGItems.TURTLE_ARM_GUARD);
		register(AGItems.WHITE_WOOL_ARM_GUARD);
		register(AGItems.ORANGE_WOOL_ARM_GUARD);
		register(AGItems.MAGENTA_WOOL_ARM_GUARD);
		register(AGItems.LIGHT_BLUE_WOOL_ARM_GUARD);
		register(AGItems.YELLOW_WOOL_ARM_GUARD);
		register(AGItems.LIME_WOOL_ARM_GUARD);
		register(AGItems.PINK_WOOL_ARM_GUARD);
		register(AGItems.GRAY_WOOL_ARM_GUARD);
		register(AGItems.LIGHT_GRAY_WOOL_ARM_GUARD);
		register(AGItems.CYAN_WOOL_ARM_GUARD);
		register(AGItems.PURPLE_WOOL_ARM_GUARD);
		register(AGItems.BLUE_WOOL_ARM_GUARD);
		register(AGItems.BROWN_WOOL_ARM_GUARD);
		register(AGItems.GREEN_WOOL_ARM_GUARD);
		register(AGItems.RED_WOOL_ARM_GUARD);
		register(AGItems.BLACK_WOOL_ARM_GUARD);
	}

	@Override
	public @NotNull Identifier getQuiltId() {
		return AGUtil.id("trinket_renderer");
	}

	private static void register(Item item) {
		TrinketRendererRegistry.registerRenderer(item, new ArmGuardTrinketRenderer());
	}
}