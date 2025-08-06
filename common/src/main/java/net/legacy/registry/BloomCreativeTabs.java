package net.legacy.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import net.legacy.Bloom;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class BloomCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Bloom.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static void modifyTabs() {
        CreativeTabRegistry.modifyBuiltin(BuiltInRegistries.CREATIVE_MODE_TAB.getValueOrThrow(CreativeModeTabs.INGREDIENTS), (flags, output, canUseGameMasterBlocks) -> {
            output.acceptAfter(Items.DIAMOND, BloomItems.TEST_ITEM.get());
        });
    }

    public static void init() {
        TABS.register();
        modifyTabs();
    }
}
