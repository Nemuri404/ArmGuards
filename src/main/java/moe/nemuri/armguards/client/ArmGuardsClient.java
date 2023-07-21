package moe.nemuri.armguards.client;

import moe.nemuri.armguards.client.render.trinket.AGTrinketRenderers;
import moe.nemuri.armguards.item.AGItems;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.resource.ResourceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;

@ClientOnly
public class ArmGuardsClient implements ClientModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Arm Guards/Client");

	@Override
	public void onInitializeClient(ModContainer mod) {
		ResourceLoader.get(ResourceType.CLIENT_RESOURCES).registerReloader(new AGTrinketRenderers());
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), AGItems.LEATHER_ARM_GUARD);
	}
}