package moe.nemuri.armguards.client.render.trinket;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import moe.nemuri.armguards.ArmGuards;
import moe.nemuri.armguards.item.AGItems;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.resource.loader.api.reloader.SimpleSynchronousResourceReloader;

@ClientOnly
public class AGTrinketRenderers implements SimpleSynchronousResourceReloader {
	@Override
	public void reload(ResourceManager manager) {
		TrinketRendererRegistry.registerRenderer(AGItems.LEATHER_ARM_GUARD, new ArmGuardTrinketRenderer());
		TrinketRendererRegistry.registerRenderer(AGItems.GOLDEN_ARM_GUARD, new ArmGuardTrinketRenderer());
		TrinketRendererRegistry.registerRenderer(AGItems.IRON_ARM_GUARD, new ArmGuardTrinketRenderer());
		TrinketRendererRegistry.registerRenderer(AGItems.DIAMOND_ARM_GUARD, new ArmGuardTrinketRenderer());
		TrinketRendererRegistry.registerRenderer(AGItems.NETHERITE_ARM_GUARD, new ArmGuardTrinketRenderer());
		TrinketRendererRegistry.registerRenderer(AGItems.COPPER_ARM_GUARD, new ArmGuardTrinketRenderer());
		TrinketRendererRegistry.registerRenderer(AGItems.TURTLE_ARM_GUARD, new ArmGuardTrinketRenderer());
	}

	@Override
	public @NotNull Identifier getQuiltId() {
		return ArmGuards.id("trinket_renderer");
	}
}