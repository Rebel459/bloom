package net.legacy.bloom.util.rules;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.legacy.bloom.mixin.worldgen.SurfaceRulesContextAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class TemperatureRules implements SurfaceRules.ConditionSource {

	private final float min;
	private final float max;

	public TemperatureRules(float min, float max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public SurfaceRules.Condition apply(SurfaceRules.Context context) {
		class Condition implements SurfaceRules.Condition {
			private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			@Override
			public boolean test() {
				var accessor = (SurfaceRulesContextAccessor) (Object) context;
				pos.set(accessor.getBlockX(), Math.max(accessor.getBlockY(), accessor.getBlockX()), accessor.getBlockZ());

				Holder<Biome> biomeSupplier = accessor.getBiomeAtPos().apply(pos);
				if (biomeSupplier.is(ConventionalBiomeTags.IS_CAVE)) {
					pos.set(pos.getX(), accessor.getChunk().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, pos.getX(), pos.getZ()), pos.getZ());
					biomeSupplier = accessor.getBiomeAtPos().apply(pos);
				}
				Biome biome = biomeSupplier.value();

				float adjustedTemp = biome.getTemperature(accessor.getBlockPos(), context.getSeaLevel());

				return adjustedTemp >= min && adjustedTemp <= max;
			}
		}
		return new Condition();
	}

	public static SurfaceRules.ConditionSource temperature(float point) {
		return new TemperatureRules(point - 0.0001F, point + 0.0001F);
	}

	public static SurfaceRules.ConditionSource temperature(float min, float max) {
		return new TemperatureRules(min, max);
	}

	public static SurfaceRules.ConditionSource temperatureBelow(float threshold) {
		return new TemperatureRules(Float.NEGATIVE_INFINITY, threshold);
	}

	public static SurfaceRules.ConditionSource temperatureAbove(float threshold) {
		return new TemperatureRules(threshold, Float.POSITIVE_INFINITY);
	}

	@Override
	public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
		return null;
	}
}
