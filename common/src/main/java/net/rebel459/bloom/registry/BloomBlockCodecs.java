package net.rebel459.bloom.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.block.UntintedParticleExtendedLeavesBlock;
import net.rebel459.unified.api.codec.ExtensibleBlockCodec;
import net.rebel459.unified.api.codec.ExtensibleCodecs;
import net.rebel459.unified.api.registry.VanillaBlockCodecs;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BloomBlockCodecs {

	public static final ExtensibleBlockCodec.Complex<VanillaBlockCodecs.ParticleLeaves> UNTINTED_PARTICLE_EXTENDED_LEAVES = complex("untinted_particle_extended_leaves",
		VanillaBlockCodecs.ParticleLeaves.CODEC, (definition, properties) -> new UntintedParticleExtendedLeavesBlock(definition.chance(), definition.particle(), properties));

	private static ExtensibleBlockCodec.Simple simple(String id, Function<BlockBehaviour.Properties, ? extends Block> factory) {
		return ExtensibleCodecs.BLOCK.register(Bloom.id(id), () -> factory);
	}

	private static <T> ExtensibleBlockCodec.Complex<T> complex(String id, MapCodec<T> codec, BiFunction<T, BlockBehaviour.Properties, ? extends Block> factory) {
		return ExtensibleCodecs.BLOCK.register(Bloom.id(id), codec, (definition) -> (properties) -> (Block)factory.apply(definition, properties));
	}

	public static void init() {}

}
