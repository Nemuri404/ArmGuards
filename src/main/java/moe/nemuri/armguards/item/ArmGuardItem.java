package moe.nemuri.armguards.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.UUID;

public class ArmGuardItem extends Item implements Vanishable, Trinket {
	private final int protection;
	private final float toughness;
	protected final ArmGuardMaterial type;

	public ArmGuardItem(ArmGuardMaterial material, Settings settings) {
		super(settings.maxDamageIfAbsent(material.getDurability()));
		TrinketsApi.registerTrinket(this, this);
		this.type = material;
		this.protection = material.getProtection();
		this.toughness = material.getToughness();
	}

	@Override
	public int getEnchantability() {
		return this.type.getEnchantability();
	}

	public ArmGuardMaterial getMaterial() {
		return this.type;
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return this.type.getRepairIngredient().test(ingredient);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		if (equipItem(user, stack)) {
			return TypedActionResult.success(stack, world.isClient());
		}
		return super.use(world, user, hand);
	}

	public boolean equipItem(LivingEntity user, ItemStack stack) {
		var optional = TrinketsApi.getTrinketComponent(user);
		if (optional.isPresent()) {
			TrinketComponent comp = optional.get();
			for (var group : comp.getInventory().values()) {
				for (TrinketInventory inv : group.values()) {
					for (int i = 0; i < inv.size(); i++) {
						if (inv.getStack(i).isEmpty()) {
							SlotReference ref = new SlotReference(inv, i);
							if (TrinketSlot.canInsert(stack, ref, user)) {
								ItemStack newStack = stack.copy();
								inv.setStack(i, newStack);
								SoundEvent soundEvent = this.getEquipSound();
								if (!stack.isEmpty() && soundEvent != null) {
									user.emitGameEvent(GameEvent.EQUIP);
									user.playSound(soundEvent, 1.0f, 1.0f);
								}
								stack.setCount(0);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
		final var modifiers = Trinket.super.getModifiers(stack, slot, entity, uuid);
		modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, "Armor modifier", this.getProtection(), EntityAttributeModifier.Operation.ADDITION));
		modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, "Armor toughness", this.getToughness(), EntityAttributeModifier.Operation.ADDITION));
		return modifiers;
	}

	public int getProtection() {
		return this.protection;
	}

	public float getToughness() {
		return this.toughness;
	}

	public SoundEvent getEquipSound() {
		return this.getMaterial().getEquipSound();
	}

	@Override
	public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
		String group = slot.inventory().getSlotType().getGroup();
		String name = slot.inventory().getSlotType().getName();
		return group.equals("offhand") && name.equals("glove");
	}
}