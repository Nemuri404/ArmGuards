package moe.nemuri.armguards.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.*;
import moe.nemuri.armguards.util.ArmGuardMaterial;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.UUID;

public class ArmGuardItem extends Item implements Vanishable, Trinket {
	public static final DispenserBehavior DISPENSER_BEHAVIOR = new ItemDispenserBehavior() {
		@Override
		protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
			return ArmGuardItem.dispenseArmGuard(pointer, stack) ? stack : super.dispenseSilently(pointer, stack);
		}
	};
	private final int protection;
	private final float toughness;
	protected final ArmGuardMaterial material;

	public ArmGuardItem(ArmGuardMaterial material, Settings settings) {
		super(settings.maxDamageIfAbsent(material.getDurability()));
		TrinketsApi.registerTrinket(this, this);
		this.material = material;
		this.protection = material.getProtection();
		this.toughness = material.getToughness();
		DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);
	}

	@Override
	public int getEnchantability() {
		return this.material.getEnchantability();
	}

	public ArmGuardMaterial getMaterial() {
		return this.material;
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return this.material.getRepairIngredient().test(ingredient);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		if (equipItem(user, stack, false)) {
			return TypedActionResult.success(stack, world.isClient());
		}
		return super.use(world, user, hand);
	}

	public boolean equipItem(LivingEntity entity, ItemStack stack, boolean silent) {
		var optional = TrinketsApi.getTrinketComponent(entity);
		if (optional.isPresent()) {
			TrinketComponent comp = optional.get();
			for (var group : comp.getInventory().values()) {
				for (TrinketInventory inv : group.values()) {
					for (int i = 0; i < inv.size(); i++) {
						if (inv.getStack(i).isEmpty()) {
							SlotReference ref = new SlotReference(inv, i);
							if (TrinketSlot.canInsert(stack, ref, entity)) {
								ItemStack newStack = stack.copy();
								inv.setStack(i, newStack);
								SoundEvent soundEvent = silent ? null : this.getEquipSound();
								if (!stack.isEmpty() && soundEvent != null) {
									entity.emitGameEvent(GameEvent.EQUIP);
									entity.playSound(soundEvent, 1.0f, 1.0f);
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


	public static boolean dispenseArmGuard(BlockPointer pointer, ItemStack stack) {
		BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
		List<LivingEntity> list = pointer.getWorld().getEntitiesByClass(LivingEntity.class, new Box(blockPos), EntityPredicates.EXCEPT_SPECTATOR);
		if (list.isEmpty()) {
			return false;
		} else {
			LivingEntity livingEntity = list.get(0);
			return ((ArmGuardItem) stack.getItem()).equipItem(livingEntity, stack, false);
		}
	}
}