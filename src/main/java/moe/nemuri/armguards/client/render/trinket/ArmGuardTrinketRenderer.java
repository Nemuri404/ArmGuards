package moe.nemuri.armguards.client.render.trinket;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import moe.nemuri.armguards.ArmGuards;
import moe.nemuri.armguards.client.render.AGRenderLayer;
import moe.nemuri.armguards.client.render.trinket.model.ArmGuardTrinketModel;
import moe.nemuri.armguards.item.ArmGuardItem;
import moe.nemuri.armguards.item.ChargeableArmGuardItem;
import moe.nemuri.armguards.item.DyeableArmGuardItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrimPermutation;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import java.util.Map;

import static net.minecraft.client.render.TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE;

@ClientOnly
public class ArmGuardTrinketRenderer implements TrinketRenderer {
	private static final Map<String, Identifier> TEXTURE_CACHE = Maps.newHashMap();
	private final BipedEntityModel<LivingEntity> entityModel = new ArmGuardTrinketModel(ArmGuardTrinketModel.getTextureModelData().createModel());
	private final SpriteAtlasTexture atlas = MinecraftClient.getInstance().getBakedModelManager().getAtlas(ARMOR_TRIMS_ATLAS_TEXTURE);

	@Override
	public void render(ItemStack stack, SlotReference slot, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider provider, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if (stack.getItem() instanceof ArmGuardItem item) {
			this.setupTransforms(entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch, this.getModel());
			this.setVisible(this.getModel(), slot.inventory().getSlotType().getGroup().equals("offhand") ? entity.getMainArm().getOpposite() : entity.getMainArm());

			if (item instanceof DyeableArmGuardItem) {
				int i = ((DyeableArmGuardItem) item).getColor(stack);
				float r = (float) (i >> 16 & 0xFF) / 255.0F;
				float g = (float) (i >> 8 & 0xFF) / 255.0F;
				float b = (float) (i & 0xFF) / 255.0F;
				this.render(matrices, provider, light, item, this.getModel(), r, g, b, null);
				this.render(matrices, provider, light, item, this.getModel(), 1.0f, 1.0f, 1.0f, "overlay");
			} else {
				this.render(matrices, provider, light, item, this.getModel(), 1.0f, 1.0f, 1.0f, null);
			}

			ArmorTrimPermutation.getPermutationFromStack(entity.getWorld().getRegistryManager(), stack).ifPresent(permutation -> this.renderTrim(matrices, provider, light, permutation, this.getModel()));

			if (item instanceof ChargeableArmGuardItem) {
				if (ChargeableArmGuardItem.isCharged(stack)) {
					this.renderCharge(matrices, provider, light, this.getModel(), (float) entity.age + tickDelta);
				} else {
					if (stack.hasGlint()) {
						this.renderGlint(matrices, provider, light, this.getModel());
					}
				}
			} else {
				if (stack.hasGlint()) {
					this.renderGlint(matrices, provider, light, this.getModel());
				}
			}
		}
	}

	private void render(MatrixStack matrices, VertexConsumerProvider provider, int light, ArmGuardItem item, BipedEntityModel<LivingEntity> model, float red, float green, float blue, @Nullable String overlay) {
		VertexConsumer vertices = provider.getBuffer(RenderLayer.getArmorCutoutNoCull(this.getTexture(item, overlay)));
		model.render(matrices, vertices, light, OverlayTexture.DEFAULT_UV, red, green, blue, 1.0f);
	}

	private void renderTrim(MatrixStack matrices, VertexConsumerProvider provider, int light, ArmorTrimPermutation permutation, BipedEntityModel<LivingEntity> model) {
		Sprite sprite = this.getSprite(permutation);
		if (sprite != null) {
			VertexConsumer vertices = sprite.getTextureSpecificVertexConsumer(provider.getBuffer(TexturedRenderLayers.getArmorTrim()));
			model.render(matrices, vertices, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
		}
	}

	private void renderGlint(MatrixStack matrices, VertexConsumerProvider provider, int light, BipedEntityModel<LivingEntity> model) {
		model.render(matrices, provider.getBuffer(RenderLayer.getArmorEntityGlint()), light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
	}

	private void renderCharge(MatrixStack matrices, VertexConsumerProvider provider, int light, BipedEntityModel<LivingEntity> model, float partialAge) {
		float offset = partialAge * 0.01f % 1.0f;
		VertexConsumer vertices = provider.getBuffer(AGRenderLayer.getEnergySwirl(this.getEnergySwirlTexture(), offset, offset));
		model.render(matrices, vertices, light, OverlayTexture.DEFAULT_UV, 0.5f, 0.5f, 0.5f, 1.0f);
	}

	protected BipedEntityModel<LivingEntity> getModel() {
		return this.entityModel;
	}

	private void setupTransforms(LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, BipedEntityModel<LivingEntity> model) {
		model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		model.animateModel(entity, limbAngle, limbDistance, tickDelta);
		TrinketRenderer.followBodyRotations(entity, model);
	}

	protected void setVisible(BipedEntityModel<LivingEntity> model, Arm arm) {
		model.setVisible(false);
		switch (arm) {
			case LEFT -> {
				model.leftArm.visible = true;
				model.rightArm.visible = false;
			}
			case RIGHT -> {
				model.leftArm.visible = false;
				model.rightArm.visible = true;
			}
		}
	}

	private Identifier getTexture(ArmGuardItem item, @Nullable String overlay) {
		String string = "arm_guards:textures/models/arm_guard/" + item.getMaterial().getName() + (overlay == null ? "" : "_" + overlay) + ".png";
		return TEXTURE_CACHE.computeIfAbsent(string, Identifier::new);
	}

	private Sprite getSprite(ArmorTrimPermutation permutation) {
		String material = permutation.getMaterial().value().assetName();
		Identifier id = permutation.getPattern().value().assetId().withPath(path -> "trims/models/arm_guard/" + path + "_" + material);
		return this.atlas.getSprite(ArmGuards.id(id.toString().replace("minecraft:", "")));
	}

	private Identifier getEnergySwirlTexture() {
		return new Identifier("textures/entity/creeper/creeper_armor.png");
	}
}