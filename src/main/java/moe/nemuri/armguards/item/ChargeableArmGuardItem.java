package moe.nemuri.armguards.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.UUID;

public class ChargeableArmGuardItem extends ArmGuardItem {
	private static final String CHARGED_KEY = "Charged";
	private static final String CHARGE_TIMER_KEY = "ChargeTimer";

	public ChargeableArmGuardItem(ArmGuardMaterial material, Settings settings) {
		super(material, settings);
	}

	public static boolean isCharged(ItemStack stack) {
		NbtCompound nbt = stack.getNbt();
		return nbt != null && nbt.getBoolean(CHARGED_KEY);
	}

	public static void setCharged(ItemStack stack, boolean charged) {
		NbtCompound nbt = stack.getOrCreateNbt();
		nbt.putBoolean(CHARGED_KEY, charged);
		if (charged) {
			nbt.putInt(CHARGE_TIMER_KEY, 7 * 20);
		} else {
			if (nbt.contains(CHARGE_TIMER_KEY)) {
				nbt.remove(CHARGE_TIMER_KEY);
			}
		}
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
		final var modifiers = super.getModifiers(stack, slot, entity, uuid);
		if (isCharged(stack)) {
			modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid, "Weapon modifier", 2, EntityAttributeModifier.Operation.ADDITION));
		}
		return modifiers;
	}

	@Override
	public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
		this.updateCharge(stack);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		this.updateCharge(stack);
	}

	private void updateCharge(ItemStack stack) {
		NbtCompound nbt = stack.getNbt();
		if (nbt != null) {
			if (nbt.getInt(CHARGE_TIMER_KEY) > 0) {
				nbt.putInt(CHARGE_TIMER_KEY, nbt.getInt(CHARGE_TIMER_KEY) - 1);
			} else {
				setCharged(stack, false);
			}
		}
	}
}