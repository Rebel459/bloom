package net.rebel459.bloom.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.rebel459.bloom.registry.BloomBiomes;

public class BloomRegistryProvider extends FabricDynamicRegistryProvider {
    protected BloomRegistryProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void buildRegistry(RegistrySetBuilder registrySetBuilder) {
        registrySetBuilder.add(Registries.BIOME, BloomBiomes::bootstrap);
    }

    @Override
    public void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(asLookup(entries.getLookup(Registries.BIOME)));
    }

    public static <T> HolderLookup.RegistryLookup<T> asLookup(HolderGetter<T> getter) {
        return (HolderLookup.RegistryLookup<T>) getter;
    }

    @Override
    public String getName() {
        return "Bloom";
    }
}
