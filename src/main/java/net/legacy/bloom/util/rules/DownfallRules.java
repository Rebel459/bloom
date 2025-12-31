package net.legacy.bloom.util.rules;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.legacy.bloom.mixin.worldgen.BiomeAccessor;
import net.legacy.bloom.mixin.worldgen.SurfaceRulesContextAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class DownfallRules implements SurfaceRules.ConditionSource {

	public static final MapCodec<DownfallRules> CODEC = RecordCodecBuilder.mapCodec(instance ->
		instance.group(
			Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
			Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
		).apply(instance, DownfallRules::new)
	);

	private final float min;
	private final float max;

	public DownfallRules(float min, float max) {
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

				Biome.ClimateSettings climate = ((BiomeAccessor) (Object) biome).getClimate();

				float downfall = climate.downfall();

				return downfall >= min && downfall <= max;
			}
		}
		return new Condition();
	}

	public static SurfaceRules.ConditionSource downfall(float point) {
		return new TemperatureRules(point - 0.0001F, point + 0.0001F);
	}

	public static SurfaceRules.ConditionSource downfall(float min, float max) {
		return new TemperatureRules(min, max);
	}

	public static SurfaceRules.ConditionSource downfallBelow(float threshold) {
		return new TemperatureRules(Float.NEGATIVE_INFINITY, threshold);
	}

	public static SurfaceRules.ConditionSource downfallAbove(float threshold) {
		return new TemperatureRules(threshold, Float.POSITIVE_INFINITY);
	}

	@Override
	public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
		return KeyDispatchDataCodec.of(CODEC);
	}
}
