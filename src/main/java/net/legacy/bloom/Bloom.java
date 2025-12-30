package net.legacy.bloom;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.legacy.bloom.config.BloomConfig;
import net.legacy.bloom.registry.BloomBiomes;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomCreativeInventory;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.sound.BloomSounds;
import net.legacy.bloom.worldgen.BloomBiomeModifications;
import net.legacy.bloom.worldgen.BloomBiomePlacement;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public class Bloom implements ModInitializer {

    public static boolean isWilderWildLoaded = false;
    Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(Bloom.MOD_ID);

	@Override
	public void onInitialize() {

		BloomConfig.init();
        BloomBlocks.init();
        BloomItems.init();
        BloomBiomes.init();
        BloomBiomePlacement.init();
        BloomBiomeModifications.init();
        BloomSounds.init();
        BloomCreativeInventory.init();

        ResourceManagerHelper.registerBuiltinResourcePack(
                Bloom.id("ore_variants"), modContainer.get(),
                Component.translatable("pack.bloom.ore_variants"),
                ResourcePackActivationType.ALWAYS_ENABLED
        );

        if (FabricLoader.getInstance().isModLoaded("wilderwild")) {
            isWilderWildLoaded = true;
        }
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
	public static final String MOD_ID = "bloom";

}