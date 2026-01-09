package net.legacy.bloom.mixin.client;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.client.SleepingBagSheets;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.AtlasManager;
import net.minecraft.data.AtlasIds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AtlasManager.class)
public class AtlasManagerMixin {

	@WrapOperation(method = "<clinit>", at = @At(value = "INVOKE", target = "Ljava/util/List;of([Ljava/lang/Object;)Ljava/util/List;"))
	private static List<AtlasManager.AtlasConfig> sleepingBags(Object[] objects, Operation<List<AtlasManager.AtlasConfig>> original) {
		AtlasManager.AtlasConfig[] extended = (AtlasManager.AtlasConfig[]) Arrays.copyOf(objects, objects.length + 1);
		extended[objects.length] = new AtlasManager.AtlasConfig(SleepingBagSheets.SLEEPING_BAG_SHEET, Bloom.id("sleeping_bags"), false);
		return List.of(extended);
	}
}
