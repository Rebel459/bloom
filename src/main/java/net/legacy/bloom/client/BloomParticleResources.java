package net.legacy.bloom.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.legacy.bloom.registry.BloomParticleTypes;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.ParticleResources;

@Environment(EnvType.CLIENT)
public final class BloomParticleResources extends ParticleResources {

	public static void init() {
		final ParticleFactoryRegistry particleRegistry = ParticleFactoryRegistry.getInstance();

        particleRegistry.register(BloomParticleTypes.JACARANDA_LEAVES, FallingLeavesParticle.CherryProvider::new);

	}
}