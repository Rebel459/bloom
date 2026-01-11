package net.legacy.bloom.mixin.client;

import java.util.ArrayList;
import java.util.List;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.client.SleepingBagRenderHelper;
import net.minecraft.client.resources.model.AtlasManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AtlasManager.class)
public class AtlasManagerMixin {

	@ModifyExpressionValue(
		method = "<clinit>",
		at = @At(
			value = "INVOKE",
			target = "Ljava/util/List;of([Ljava/lang/Object;)Ljava/util/List;"
		)
	)
	private static List<Object> bloom$addSleepingBags(List<Object> original) {
		final List<Object> newList = new ArrayList<>(original);
		newList.add(new AtlasManager.AtlasConfig(SleepingBagRenderHelper.SLEEPING_BAG_SHEET, Bloom.id("sleeping_bags"), false));
		return List.copyOf(newList);
	}
}
