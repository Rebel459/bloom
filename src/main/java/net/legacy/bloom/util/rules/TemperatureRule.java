package net.legacy.bloom.util.rules;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.legacy.bloom.mixin.worldgen.SurfaceRulesContextAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class TemperatureRule implements SurfaceRules.ConditionSource {
	public static final KeyDispatchDataCodec<TemperatureRule> CODEC = KeyDispatchDataCodec.of(
		RecordCodecBuilder.mapCodec(instance ->
			instance.group(
				Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
				Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
			).apply(instance, TemperatureRule::new)
		)
	);

	private final float min;
	private final float max;

	public TemperatureRule(float min, float max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public SurfaceRules.Condition apply(SurfaceRules.Context context) {
		class Condition implements SurfaceRules.Condition {
			private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			@Override
			public boolean test() {
				final var accessor = (SurfaceRulesContextAccessor) (Object) context;
				this.pos.set(accessor.getBlockX(), accessor.getBlockY(), accessor.getBlockZ());

				Holder<Biome> biomeSupplier = accessor.getBiomeAtPos().apply(this.pos);
				if (biomeSupplier.is(ConventionalBiomeTags.IS_CAVE)) {
					this.pos.setY(accessor.getChunk().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, this.pos.getX(), this.pos.getZ()));
					biomeSupplier = accessor.getBiomeAtPos().apply(this.pos);
				}
				Biome biome = biomeSupplier.value();

				float adjustedTemp = biome.getTemperature(pos, context.getSeaLevel());

				return adjustedTemp >= TemperatureRule.this.min && adjustedTemp <= TemperatureRule.this.max;
			}
		}
		return new Condition();
	}

	public static SurfaceRules.ConditionSource temperature(float point) {
		return new TemperatureRule(point - 0.001F, point + 0.001F);
	}

	public static SurfaceRules.ConditionSource temperature(float min, float max) {
		return new TemperatureRule(min, max);
	}

	public static SurfaceRules.ConditionSource temperatureBelow(float threshold) {
		return new TemperatureRule(Float.NEGATIVE_INFINITY, threshold);
	}

	public static SurfaceRules.ConditionSource temperatureAbove(float threshold) {
		return new TemperatureRule(threshold, Float.POSITIVE_INFINITY);
	}

	@Override
	public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
		return CODEC;
	}
}
