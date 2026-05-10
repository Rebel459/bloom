package net.rebel459.bloom.mixin.client.snow.sodium;

import net.caffeinemc.mods.sodium.client.model.light.data.LightDataAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = LightDataAccess.class, remap = false)
public interface SodiumLightDataAccessInvoker {
	@Invoker("compute")
	int bloom$compute(int x, int y, int z);
}
