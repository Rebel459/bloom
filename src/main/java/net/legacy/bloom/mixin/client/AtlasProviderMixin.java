package net.legacy.bloom.mixin.client;

import java.util.concurrent.CompletableFuture;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.client.SleepingBagSheets;
import net.minecraft.client.data.AtlasProvider;
import net.minecraft.data.CachedOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AtlasProvider.class)
public class AtlasProviderMixin {

    @Inject(method = "run", at = @At("TAIL"), cancellable = true)
    private void sleepingBags(CachedOutput cachedOutput, CallbackInfoReturnable<CompletableFuture<?>> cir) {
		AtlasProvider provider = AtlasProvider.class.cast(this);
		cir.setReturnValue(CompletableFuture.allOf(
			cir.getReturnValue(),
			provider.storeAtlas(cachedOutput, Bloom.id("sleeping_bags"), provider.simpleMapper(SleepingBagSheets.SLEEPING_BAG_MAPPER))
		));
    }
}
