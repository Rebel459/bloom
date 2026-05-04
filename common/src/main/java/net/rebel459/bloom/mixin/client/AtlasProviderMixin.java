package net.rebel459.bloom.mixin.client;

import java.util.concurrent.CompletableFuture;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.client.SleepingBagRenderHelper;
import net.minecraft.client.data.AtlasProvider;
import net.minecraft.data.CachedOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AtlasProvider.class)
public class AtlasProviderMixin {

    @ModifyReturnValue(method = "run", at = @At("RETURN"))
    private CompletableFuture<?> bloom$addSleepingBags(
		CompletableFuture<?> original,
		CachedOutput cache
	) {
		final AtlasProvider provider = AtlasProvider.class.cast(this);
		return CompletableFuture.allOf(
			original,
			provider.storeAtlas(cache, Bloom.id("sleeping_bags"), AtlasProvider.simpleMapper(SleepingBagRenderHelper.SLEEPING_BAG_MAPPER))
		);
	}
}
