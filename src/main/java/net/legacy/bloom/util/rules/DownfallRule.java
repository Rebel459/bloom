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

public class DownfallRule implements SurfaceRules.ConditionSource {
	public static final KeyDispatchDataCodec<DownfallRule> CODEC = KeyDispatchDataCodec.of(
		RecordCodecBuilder.mapCodec(instance ->
			instance.group(
				Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
				Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
			).apply(instance, DownfallRule::new)
		)
	);

	private final float min;
	private final float max;

	public DownfallRule(float min, float max) {
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
				this.pos.set(accessor.getBlockX(), accessor.getBlockY(), accessor.getBlockZ());

				Holder<Biome> biomeSupplier = accessor.getBiomeAtPos().apply(this.pos);
				if (biomeSupplier.is(ConventionalBiomeTags.IS_CAVE)) {
					biomeSupplier = accessor.getBiomeAtPos().apply(new BlockPos(this.pos.getX(), accessor.getChunk().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, this.pos.getX(), this.pos.getZ()), this.pos.getZ()));
				}
				Biome biome = biomeSupplier.value();

				Biome.ClimateSettings climate = ((BiomeAccessor) (Object) biome).getClimate();

				float downfall = climate.downfall();

				return downfall >= DownfallRule.this.min && downfall <= DownfallRule.this.max;
			}
		}
		return new Condition();
	}

	public static SurfaceRules.ConditionSource downfall(float point) {
		return new DownfallRule(point - 0.001F, point + 0.001F);
	}

	public static SurfaceRules.ConditionSource downfall(float min, float max) {
		return new DownfallRule(min, max);
	}

	public static SurfaceRules.ConditionSource downfallBelow(float threshold) {
		return new DownfallRule(Float.NEGATIVE_INFINITY, threshold);
	}

	public static SurfaceRules.ConditionSource downfallAbove(float threshold) {
		return new DownfallRule(threshold, Float.POSITIVE_INFINITY);
	}

	@Override
	public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
		return CODEC;
	}
}
