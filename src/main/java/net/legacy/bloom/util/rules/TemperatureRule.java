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
		return new Condition(context);
	}

	private final class Condition implements SurfaceRules.Condition {
		private final SurfaceRulesContextAccessor accessor;
		private final int seaLevel;

		private final BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos();
		private final BlockPos.MutableBlockPos surfacePos = new BlockPos.MutableBlockPos();

		private Holder<Biome> cachedHeightmapBiome = null;
		private boolean isCachedMountain = false;
		private int cachedX = Integer.MIN_VALUE;
		private int cachedZ = Integer.MIN_VALUE;

		Condition(SurfaceRules.Context context) {
			this.accessor = (SurfaceRulesContextAccessor) (Object) context;
			this.seaLevel = accessor.getSystem().getSeaLevel();
		}

		@Override
		public boolean test() {
			int x = accessor.getBlockX();
			int y = accessor.getBlockY();
			int z = accessor.getBlockZ();
			currentPos.set(x, y, z);

			// Handle caching
			if (x != cachedX || z != cachedZ) {
				cachedX = x;
				cachedZ = z;

				int surfaceY = accessor.getChunk().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z);
				surfacePos.set(x, surfaceY, z);
				cachedHeightmapBiome = accessor.getBiomeAtPos().apply(surfacePos);

				isCachedMountain = cachedHeightmapBiome.is(ConventionalBiomeTags.IS_MOUNTAIN);
			}

			Holder<Biome> biomeToUse;

			if (isCachedMountain) {
				biomeToUse = accessor.getBiomeAtPos().apply(currentPos);
				if (biomeToUse.is(ConventionalBiomeTags.IS_CAVE)) biomeToUse = cachedHeightmapBiome;
			} else {
				biomeToUse = cachedHeightmapBiome;
			}

			Biome biome = biomeToUse.value();
			float adjustedTemp = biome.getTemperature(currentPos, seaLevel);

			return adjustedTemp >= TemperatureRule.this.min && adjustedTemp <= TemperatureRule.this.max;
		}
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
