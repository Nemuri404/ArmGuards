package moe.nemuri.armguards.client.render.item;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexConsumers;
import moe.nemuri.armguards.client.render.AGRenderLayer;
import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class AGItemRenderer {
	public static final Identifier ITEM_CHARGED_GLINT = AGUtil.id("textures/misc/charged_glint_item.png");

	public static VertexConsumer getItemChargeConsumer(VertexConsumerProvider provider, RenderLayer layer) {
		return MinecraftClient.isFabulousGraphicsOrBetter() && layer == TexturedRenderLayers.getItemEntityTranslucentCull() ? VertexConsumers.union(provider.getBuffer(AGRenderLayer.getChargeTranslucent()), provider.getBuffer(layer)) : VertexConsumers.union(provider.getBuffer(AGRenderLayer.getCharge()), provider.getBuffer(layer));
	}
}