package net.legacy;

import net.legacy.registry.BloomBlocks;
import net.legacy.registry.BloomCreativeTabs;
import net.legacy.registry.BloomItems;
import net.minecraft.resources.ResourceLocation;

public final class Bloom {
    public static final String MOD_ID = "bloom";
    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {
        BloomItems.init();
        BloomBlocks.init();
        BloomCreativeTabs.init();
    }
}
