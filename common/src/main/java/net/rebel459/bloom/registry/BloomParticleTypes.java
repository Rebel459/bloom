package net.rebel459.bloom.registry;

import java.util.function.Supplier;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.rebel459.bloom.Bloom;
import net.rebel459.unified.platform.UnifiedRegistries;

public final class BloomParticleTypes {

	static UnifiedRegistries.DeferredRegistry<ParticleType<?>> PARTICLES = UnifiedRegistries.DeferredRegistry.create(Bloom.MOD_ID, BuiltInRegistries.PARTICLE_TYPE);

    public static final Supplier<SimpleParticleType> JACARANDA_LEAVES = PARTICLES.register("jacaranda_leaves", () -> new SimpleParticleType(false));

	public static void init() {}
}
