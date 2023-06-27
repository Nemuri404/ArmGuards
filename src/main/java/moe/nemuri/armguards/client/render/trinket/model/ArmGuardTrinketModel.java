package moe.nemuri.armguards.client.render.trinket.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.LivingEntity;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class ArmGuardTrinketModel extends BipedEntityModel<LivingEntity> {
	public ArmGuardTrinketModel(ModelPart root) {
		super(root, RenderLayer::getEntityCutoutNoCull);
	}

	public static TexturedModelData getTextureModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0f);
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(0.5f)), ModelTransform.pivot(5.0f, 2.0f, 0.0f));
		modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().mirrored().uv(40, 16).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, new Dilation(0.5f)), ModelTransform.pivot(-5.0f, 2.0f, 0.0f));
		return TexturedModelData.of(modelData, 64, 32);
	}
}