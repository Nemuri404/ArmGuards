package moe.nemuri.armguards.client.render;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormats;
import moe.nemuri.armguards.client.render.item.AGItemRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public abstract class AGRenderLayer extends RenderPhase {
	private static final RenderLayer CHARGE_TRANSLUCENT = RenderLayer.of("charge_translucent", VertexFormats.POSITION_TEXTURE, VertexFormat.DrawMode.QUADS, 256, RenderLayer.MultiPhaseParameters.builder().shader(TRANSLUCENT_GLINT_SHADER).texture(new RenderPhase.Texture(AGItemRenderer.ITEM_CHARGED_GLINT, true, false)).writeMaskState(COLOR_MASK).cull(DISABLE_CULLING).depthTest(EQUAL_DEPTH_TEST).transparency(GLINT_TRANSPARENCY).texturing(GLINT_TEXTURING).target(ITEM_TARGET).build(false));
	private static final RenderLayer CHARGE = RenderLayer.of("charge", VertexFormats.POSITION_TEXTURE, VertexFormat.DrawMode.QUADS, 256, RenderLayer.MultiPhaseParameters.builder().shader(GLINT_SHADER).texture(new RenderPhase.Texture(AGItemRenderer.ITEM_CHARGED_GLINT, true, false)).writeMaskState(COLOR_MASK).cull(DISABLE_CULLING).depthTest(EQUAL_DEPTH_TEST).transparency(GLINT_TRANSPARENCY).texturing(GLINT_TEXTURING).build(false));

	public AGRenderLayer(String name, Runnable beginAction, Runnable endAction) {
		super(name, beginAction, endAction);
	}

	public static RenderLayer getChargeTranslucent() {
		return CHARGE_TRANSLUCENT;
	}

	public static RenderLayer getCharge() {
		return CHARGE;
	}

	public static RenderLayer getEnergySwirl(Identifier texture, float x, float y) {
		return RenderLayer.of(
			"energy_swirl",
			VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL,
			VertexFormat.DrawMode.QUADS,
			256,
			false,
			true,
			RenderLayer.MultiPhaseParameters.builder()
				.shader(ENERGY_SWIRL_SHADER)
				.texture(new RenderPhase.Texture(texture, false, false))
				.texturing(new RenderPhase.OffsetTexturing(x, y))
				.writeMaskState(COLOR_MASK)
				.depthTest(EQUAL_DEPTH_TEST)
				.transparency(ADDITIVE_TRANSPARENCY)
				.cull(DISABLE_CULLING)
				.lightmap(ENABLE_LIGHTMAP)
				.overlay(ENABLE_OVERLAY_COLOR)
				.layering(VIEW_OFFSET_Z_LAYERING)
				.build(false)
		);
	}
}