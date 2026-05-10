package net.rebel459.bloom.mixin;

import java.util.List;
import java.util.Set;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.rebel459.bloom.config.BloomConfig;
import net.rebel459.unified.platform.UnifiedPlatform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public final class BloomMixinPlugin implements IMixinConfigPlugin {

	private static boolean registeredConfig = false;

	@Override
    public void onLoad(String mixinPackage) {
		if (!registeredConfig) {
			AutoConfig.register(BloomConfig.class, GsonConfigSerializer::new);
			registeredConfig = true;
		}
	}

    @Override
    @Nullable
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, @NotNull String mixinClassName) {
		boolean snowOverlay = BloomConfig.get().misc.snowier_snow != BloomConfig.SnowType.NONE;
		boolean hasSodium = UnifiedPlatform.isModLoaded("sodium");
		if (mixinClassName.contains("client.snow.sodium")) return snowOverlay && hasSodium;
		if (mixinClassName.contains("client.snow.vanilla")) return snowOverlay && !hasSodium;
		if (mixinClassName.contains("client.snow.wilderwild")) return snowOverlay && UnifiedPlatform.isModLoaded("wilderwild");
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    @Nullable
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
