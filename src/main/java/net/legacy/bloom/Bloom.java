package net.legacy.bloom;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.legacy.bloom.config.BloomConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public class Bloom implements ModInitializer {

    public static boolean isWilderWildLoaded = false;

	@Override
	public void onInitialize() {
        Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(MOD_ID);

		BloomConfig.init();

        ResourceManagerHelper.registerBuiltinResourcePack(
                Bloom.id("template"), modContainer.get(),
                Component.translatable("pack.bloom.template"),
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