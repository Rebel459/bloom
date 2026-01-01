package net.legacy.bloom;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.legacy.bloom.config.BloomConfig;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomCreativeInventory;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.sound.BloomSounds;
import net.legacy.bloom.util.ClimateCommand;
import net.legacy.bloom.worldgen.BloomBiomeModifications;
import net.legacy.bloom.worldgen.BloomBiomePlacement;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class Bloom implements ModInitializer {
	public static final String MOD_ID = "bloom";
    public static final boolean HAS_WILDERWILD = FabricLoader.getInstance().isModLoaded("wilderwild");

	@Override
	public void onInitialize() {
		final ModContainer modContainer = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow();
		BloomConfig.init();
        BloomBlocks.init();
        BloomItems.init();
        BloomBiomes.init();
        BloomBiomePlacement.init();
        BloomBiomeModifications.init();
        BloomSounds.init();
        BloomCreativeInventory.init();
		ClimateCommand.register();

		ResourceLoader.registerBuiltinPack(
			Bloom.id("ore_variants"), modContainer,
			Component.translatable("pack.bloom.ore_variants"),
			PackActivationType.ALWAYS_ENABLED
        );
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

}
