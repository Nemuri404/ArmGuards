package moe.nemuri.armguards.mixin;

import com.mojang.blaze3d.vertex.BufferBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import moe.nemuri.armguards.client.render.AGRenderLayer;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.RenderLayer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ClientOnly
@Mixin(BufferBuilderStorage.class)
public abstract class BufferBuilderStorageMixin {
	@Inject(method = "assignBufferBuilder", at = @At("HEAD"))
	private static void armGuards$assignBufferBuilder(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> builderStorage, RenderLayer layer, CallbackInfo ci) {
		builderStorage.put(AGRenderLayer.getCharge(), new BufferBuilder(layer.getExpectedBufferSize()));
		builderStorage.put(AGRenderLayer.getChargeTranslucent(), new BufferBuilder(layer.getExpectedBufferSize()));
	}
}