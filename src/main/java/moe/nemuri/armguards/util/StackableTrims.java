package moe.nemuri.armguards.util;

import com.mojang.serialization.DataResult;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrimPermutation;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.tag.ItemTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Original code by ApfelRauber. Modified to fit Quilt Mappings.
 */
public abstract class StackableTrims {
	public static Optional<List<ArmorTrimPermutation>> getTrims(DynamicRegistryManager registryManager, ItemStack stack) {
		if (!stack.isIn(ItemTags.TRIMMABLE_ARMOR)) return Optional.empty();

		NbtCompound nbt = stack.getNbt();
		if (nbt == null || !nbt.contains("Trim")) return Optional.empty();

		NbtList nbtList = nbt.getList("Trim", 10);
		if (nbtList.isEmpty()) return Optional.empty();

		List<ArmorTrimPermutation> permutation = new ArrayList<>(nbtList.size());
		for (NbtElement element : nbtList) {
			DataResult<ArmorTrimPermutation> result = ArmorTrimPermutation.CODEC.parse(RegistryOps.create(NbtOps.INSTANCE, registryManager), element);
			result.result().ifPresent(permutation::add);
		}

		return Optional.of(permutation);
	}
}
