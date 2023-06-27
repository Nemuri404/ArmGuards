package moe.nemuri.armguards.mixin;

import com.mojang.blaze3d.vertex.VertexConsumer;
import moe.nemuri.armguards.client.render.item.AGItemRenderer;
import moe.nemuri.armguards.item.ChargeableArmGuardItem;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ClientOnly
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
	@Unique
	private ItemStack stackTest = null;
	@Unique
	private VertexConsumerProvider providerTest = null;
	@Unique
	private RenderLayer layerTest = null;

	@Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At("HEAD"))
	private void armGuards$renderItem(ItemStack stack, ModelTransformationMode modelTransformationMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
		this.stackTest = stack;
		this.providerTest = vertexConsumers;
		this.layerTest = RenderLayers.getItemLayer(stack, false);
	}

	@ModifyArg(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderBakedItemModel(Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/item/ItemStack;IILnet/minecraft/client/util/math/MatrixStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V"), index = 5)
	private VertexConsumer armGuards$renderItem(VertexConsumer vertices) {
		if (this.stackTest != null && this.providerTest != null && this.layerTest != null) {
			if (this.stackTest.getItem() instanceof ChargeableArmGuardItem && ChargeableArmGuardItem.isCharged(this.stackTest)) {
				return AGItemRenderer.getItemChargeConsumer(this.providerTest, this.layerTest);
			}
		}
		return vertices;
	}
}