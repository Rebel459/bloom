package net.rebel459.bloom.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.rebel459.bloom.mixin.worldgen.SurfaceRulesContextAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class NoiseRules {

	public enum Type {
		HUMIDITY,
		CONTINENTALNESS,
		TEMPERATURE,
		EROSION,
		WEIRDNESS,
		DEPTH,
		HEIGHTMAP_DEPTH
	}

	public static class Temperature implements SurfaceRules.ConditionSource {

		public static final KeyDispatchDataCodec<Temperature> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
					Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
				).apply(instance, Temperature::new)
			)
		);

		private final float min;
		private final float max;

		public Temperature(float min, float max) {
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

			private double cachedNoise;
			private int cachedX;
			private int cachedZ;

			private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			Condition(SurfaceRules.Context context) {
				this.accessor = (SurfaceRulesContextAccessor) (Object) context;
				this.seaLevel = accessor.getSystem().getSeaLevel();
			}

			@Override
			public boolean test() {
				int x = accessor.getBlockX();
				int y = this.seaLevel;
				int z = accessor.getBlockZ();
				pos.set(x, y, z);

				// Handle caching
				if (x != cachedX || z != cachedZ) {
					cachedX = x;
					cachedZ = z;

					this.cachedNoise = accessor.getRandomState().router().temperature().compute(new DensityFunction.FunctionContext() {
						@Override
						public int blockX() {
							return x;
						}

						@Override
						public int blockY() {
							return y;
						}

						@Override
						public int blockZ() {
							return z;
						}
					});
				}

				return cachedNoise >= Temperature.this.min && cachedNoise <= Temperature.this.max;
			}
		}

		public static SurfaceRules.ConditionSource point(float point) {
			return new Temperature(point - 0.001F, point + 0.001F);
		}

		public static SurfaceRules.ConditionSource range(float min, float max) {
			return new Temperature(min, max);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}

	public static class Humidity implements SurfaceRules.ConditionSource {

		public static final KeyDispatchDataCodec<Humidity> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
					Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
				).apply(instance, Humidity::new)
			)
		);

		private final float min;
		private final float max;

		public Humidity(float min, float max) {
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

			private double cachedNoise;
			private int cachedX;
			private int cachedZ;

			private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			Condition(SurfaceRules.Context context) {
				this.accessor = (SurfaceRulesContextAccessor) (Object) context;
				this.seaLevel = accessor.getSystem().getSeaLevel();
			}

			@Override
			public boolean test() {
				int x = accessor.getBlockX();
				int y = this.seaLevel;
				int z = accessor.getBlockZ();
				pos.set(x, y, z);

				// Handle caching
				if (x != cachedX || z != cachedZ) {
					cachedX = x;
					cachedZ = z;

					this.cachedNoise = accessor.getRandomState().router().vegetation().compute(new DensityFunction.FunctionContext() {
						@Override
						public int blockX() {
							return x;
						}

						@Override
						public int blockY() {
							return y;
						}

						@Override
						public int blockZ() {
							return z;
						}
					});
				}

				return cachedNoise >= Humidity.this.min && cachedNoise <= Humidity.this.max;
			}
		}

		public static SurfaceRules.ConditionSource point(float point) {
			return new Humidity(point - 0.001F, point + 0.001F);
		}

		public static SurfaceRules.ConditionSource range(float min, float max) {
			return new Humidity(min, max);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}

	public static class Erosion implements SurfaceRules.ConditionSource {

		public static final KeyDispatchDataCodec<Erosion> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
					Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
				).apply(instance, Erosion::new)
			)
		);

		private final float min;
		private final float max;

		public Erosion(float min, float max) {
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

			private double cachedNoise;
			private int cachedX;
			private int cachedZ;

			private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			Condition(SurfaceRules.Context context) {
				this.accessor = (SurfaceRulesContextAccessor) (Object) context;
				this.seaLevel = accessor.getSystem().getSeaLevel();
			}

			@Override
			public boolean test() {
				int x = accessor.getBlockX();
				int y = this.seaLevel;
				int z = accessor.getBlockZ();
				pos.set(x, y, z);

				// Handle caching
				if (x != cachedX || z != cachedZ) {
					cachedX = x;
					cachedZ = z;

					this.cachedNoise = accessor.getRandomState().router().erosion().compute(new DensityFunction.FunctionContext() {
						@Override
						public int blockX() {
							return x;
						}

						@Override
						public int blockY() {
							return y;
						}

						@Override
						public int blockZ() {
							return z;
						}
					});
				}

				return cachedNoise >= Erosion.this.min && cachedNoise <= Erosion.this.max;
			}
		}

		public static SurfaceRules.ConditionSource point(float point) {
			return new Erosion(point - 0.001F, point + 0.001F);
		}

		public static SurfaceRules.ConditionSource range(float min, float max) {
			return new Erosion(min, max);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}

	public static class Continentalness implements SurfaceRules.ConditionSource {

		public static final KeyDispatchDataCodec<Continentalness> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
					Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
				).apply(instance, Continentalness::new)
			)
		);

		private final float min;
		private final float max;

		public Continentalness(float min, float max) {
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

			private double cachedNoise;
			private int cachedX;
			private int cachedZ;

			private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			Condition(SurfaceRules.Context context) {
				this.accessor = (SurfaceRulesContextAccessor) (Object) context;
				this.seaLevel = accessor.getSystem().getSeaLevel();
			}

			@Override
			public boolean test() {
				int x = accessor.getBlockX();
				int y = this.seaLevel;
				int z = accessor.getBlockZ();
				pos.set(x, y, z);

				// Handle caching
				if (x != cachedX || z != cachedZ) {
					cachedX = x;
					cachedZ = z;

					this.cachedNoise = accessor.getRandomState().router().continents().compute(new DensityFunction.FunctionContext() {
						@Override
						public int blockX() {
							return x;
						}

						@Override
						public int blockY() {
							return y;
						}

						@Override
						public int blockZ() {
							return z;
						}
					});
				}

				return cachedNoise >= Continentalness.this.min && cachedNoise <= Continentalness.this.max;
			}
		}

		public static SurfaceRules.ConditionSource point(float point) {
			return new Continentalness(point - 0.001F, point + 0.001F);
		}

		public static SurfaceRules.ConditionSource range(float min, float max) {
			return new Continentalness(min, max);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}

	public static class Weirdness implements SurfaceRules.ConditionSource {

		public static final KeyDispatchDataCodec<Weirdness> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
					Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
				).apply(instance, Weirdness::new)
			)
		);

		private final float min;
		private final float max;

		public Weirdness(float min, float max) {
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

			private double cachedNoise;
			private int cachedX;
			private int cachedZ;

			private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			Condition(SurfaceRules.Context context) {
				this.accessor = (SurfaceRulesContextAccessor) (Object) context;
				this.seaLevel = accessor.getSystem().getSeaLevel();
			}

			@Override
			public boolean test() {
				int x = accessor.getBlockX();
				int y = this.seaLevel;
				int z = accessor.getBlockZ();
				pos.set(x, y, z);

				// Handle caching
				if (x != cachedX || z != cachedZ) {
					cachedX = x;
					cachedZ = z;

					this.cachedNoise = accessor.getRandomState().router().ridges().compute(new DensityFunction.FunctionContext() {
						@Override
						public int blockX() {
							return x;
						}

						@Override
						public int blockY() {
							return y;
						}

						@Override
						public int blockZ() {
							return z;
						}
					});
				}

				return cachedNoise >= Weirdness.this.min && cachedNoise <= Weirdness.this.max;
			}
		}

		public static SurfaceRules.ConditionSource point(float point) {
			return new Weirdness(point - 0.001F, point + 0.001F);
		}

		public static SurfaceRules.ConditionSource range(float min, float max) {
			return new Weirdness(min, max);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}

	public static class Depth implements SurfaceRules.ConditionSource {

		public static final KeyDispatchDataCodec<Depth> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
					Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
				).apply(instance, Depth::new)
			)
		);

		private final float min;
		private final float max;

		public Depth(float min, float max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public SurfaceRules.Condition apply(SurfaceRules.Context context) {
			return new Condition(context);
		}

		private final class Condition implements SurfaceRules.Condition {
			private final SurfaceRulesContextAccessor accessor;

			Condition(SurfaceRules.Context context) {
				this.accessor = (SurfaceRulesContextAccessor) (Object) context;
			}

			@Override
			public boolean test() {
				int x = accessor.getBlockX();
				int y = accessor.getBlockY();
				int z = accessor.getBlockZ();

				double noise = accessor.getRandomState().router().depth().compute(new DensityFunction.FunctionContext() {
					@Override
					public int blockX() {
						return x;
					}

					@Override
					public int blockY() {
						return y;
					}

					@Override
					public int blockZ() {
						return z;
					}
				});

				return noise >= Depth.this.min && noise <= Depth.this.max;
			}
		}

		public static SurfaceRules.ConditionSource point(float point) {
			return new Depth(point - 0.001F, point + 0.001F);
		}

		public static SurfaceRules.ConditionSource range(float min, float max) {
			return new Depth(min, max);
		}

		public static SurfaceRules.ConditionSource above(float above) {
			return new Depth(above, Float.MAX_VALUE);
		}

		public static SurfaceRules.ConditionSource below(float below) {
			return new Depth(Float.MIN_VALUE, below);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}

	public static class HeightmapDepth implements SurfaceRules.ConditionSource {

		public static final KeyDispatchDataCodec<HeightmapDepth> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					Codec.FLOAT.fieldOf("min").forGetter(r -> r.min),
					Codec.FLOAT.fieldOf("max").forGetter(r -> r.max)
				).apply(instance, HeightmapDepth::new)
			)
		);

		private final float min;
		private final float max;

		public HeightmapDepth(float min, float max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public SurfaceRules.Condition apply(SurfaceRules.Context context) {
			return new Condition(context);
		}

		private final class Condition implements SurfaceRules.Condition {
			private final SurfaceRulesContextAccessor accessor;

			private double cachedNoise;

			private int cachedX = Integer.MIN_VALUE;
			private int cachedZ = Integer.MIN_VALUE;

			Condition(SurfaceRules.Context context) {
				this.accessor = (SurfaceRulesContextAccessor) (Object) context;
			}

			@Override
			public boolean test() {
				int x = accessor.getBlockX();
				int z = accessor.getBlockZ();

				// Handle caching
				if (x != cachedX || z != cachedZ) {
					cachedX = x;
					cachedZ = z;

					cachedNoise = accessor.getRandomState().router().depth().compute(new DensityFunction.FunctionContext() {
						@Override
						public int blockX() {
							return x;
						}

						@Override
						public int blockY() {
							return accessor.getChunk().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z);
						}

						@Override
						public int blockZ() {
							return z;
						}
					});
				}

				return cachedNoise >= HeightmapDepth.this.min && cachedNoise <= HeightmapDepth.this.max;
			}
		}

		public static SurfaceRules.ConditionSource point(float point) {
			return new Depth(point - 0.001F, point + 0.001F);
		}

		public static SurfaceRules.ConditionSource range(float min, float max) {
			return new Depth(min, max);
		}

		public static SurfaceRules.ConditionSource above(float above) {
			return new Depth(above, Float.MAX_VALUE);
		}

		public static SurfaceRules.ConditionSource below(float below) {
			return new Depth(Float.MIN_VALUE, below);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}
}
