package net.rebel459.bloom.mixin.client;

import net.minecraft.client.renderer.blockentity.state.BedRenderState;
import net.rebel459.bloom.client.SleepingBagRenderStateAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BedRenderState.class)
public class BedRenderStateMixin implements SleepingBagRenderStateAccess {
	@Unique
	private boolean bloom$sleepingBag;

	@Override
	public boolean bloom$isSleepingBag() {
		return this.bloom$sleepingBag;
	}

	@Override
	public void bloom$setSleepingBag(boolean sleepingBag) {
		this.bloom$sleepingBag = sleepingBag;
	}
}
