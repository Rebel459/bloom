package net.rebel459.bloom.client;

import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.ParticleResources;
import net.rebel459.bloom.registry.BloomParticleTypes;
import net.rebel459.unified.platform.client.UnifiedClientHelpers;

public final class BloomParticleResources extends ParticleResources {

	public static void init() {
        UnifiedClientHelpers.PARTICLE_PROVIDERS.add(BloomParticleTypes.JACARANDA_LEAVES, FallingLeavesParticle.CherryProvider::new);
	}
}
