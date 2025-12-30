package net.legacy.bloom.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.legacy.bloom.Bloom;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public final class BloomParticleTypes {
    public static final SimpleParticleType JACARANDA_LEAVES = register("jacaranda_leaves", false);

    private static SimpleParticleType register(String path, boolean alwaysShow) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Bloom.id(path), FabricParticleTypes.simple(alwaysShow));
    }
}
