package moe.nemuri.armguards.mixin;

import moe.nemuri.armguards.enchantment.AGEnchantments;
import moe.nemuri.armguards.enchantment.DeflectEnchantment;
import moe.nemuri.armguards.util.AGUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	public abstract boolean isBlocking();

	@Shadow
	public abstract void remove(RemovalReason reason);

	public LivingEntityMixin(EntityType<? extends LivingEntity> type, World world) {
		super(type, world);
	}

	@Inject(method = "blockedByShield", at = @At("RETURN"), cancellable = true)
	private void armGuards$blockedByShield(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
		if (!this.isBlocking() && this.shouldDeflectProjectile(source)) {
			cir.setReturnValue(true);
		} else {
			cir.cancel();
		}
	}

	@Unique
	private boolean shouldDeflectProjectile(DamageSource source) {
		LivingEntity livingEntity = (LivingEntity) (Object) this;
		if (AGUtil.hasArmGuard(livingEntity)) {
			Entity entity = source.getSource();
			boolean bl = entity instanceof PersistentProjectileEntity persistentProjectileEntity && persistentProjectileEntity.getPierceLevel() > 0;

			if (!source.isTypeIn(DamageTypeTags.BYPASSES_SHIELD) && !bl) {
				Vec3d vec3d = source.getPosition();
				if (vec3d != null) {
					Vec3d vec3d2 = this.getRotationVec(1.0f);
					Vec3d vec3d3 = vec3d.relativize(this.getPos()).normalize();
					vec3d3 = new Vec3d(vec3d3.x, 0.0, vec3d3.z);
					int level = EnchantmentHelper.getLevel(AGEnchantments.DEFLECT, AGUtil.getArmGuard(livingEntity));
					return vec3d3.dotProduct(vec3d2) < 0.0 && DeflectEnchantment.shouldDeflectProjectile(level, livingEntity.getRandom());
				}
			}
		}
		return false;
	}
}