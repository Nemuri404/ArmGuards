package moe.nemuri.armguards;

import moe.nemuri.armguards.enchantment.AGEnchantments;
import moe.nemuri.armguards.event.AGEvents;
import moe.nemuri.armguards.item.AGItems;
import moe.nemuri.armguards.sound.AGSoundEvents;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ArmGuards implements ModInitializer {
	public static final String MOD_ID = "arm_guards";
	public static final Logger LOGGER = LogManager.getLogger("Arm Guards");

	@Override
	public void onInitialize(ModContainer mod) {
		AGItems.init();
		AGEnchantments.init();
		AGSoundEvents.init();
		AGEvents.init();
	}

	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}
}