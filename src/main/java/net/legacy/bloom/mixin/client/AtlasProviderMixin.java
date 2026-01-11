package net.legacy.bloom.mixin.client;

import java.util.concurrent.CompletableFuture;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.client.SleepingBagRenderHelper;
import net.minecraft.client.data.AtlasProvider;
import net.minecraft.data.CachedOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AtlasProvider.class)
public class AtlasProviderMixin {

    @ModifyReturnValue(method = "run", at = @At("RETURN"))
    private CompletableFuture<?> bloom$addSleepingBags(
		CompletableFuture<?> original,
		CachedOutput output
	) {
		final AtlasProvider provider = AtlasProvider.class.cast(this);
		return CompletableFuture.allOf(
			original,
			provider.storeAtlas(output, Bloom.id("sleeping_bags"), AtlasProvider.simpleMapper(SleepingBagRenderHelper.SLEEPING_BAG_MAPPER))
		);
	}
}
