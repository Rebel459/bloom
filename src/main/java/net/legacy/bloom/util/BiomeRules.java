package net.legacy.bloom.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.legacy.bloom.mixin.worldgen.SurfaceRulesContextAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class BiomeRules {

	public enum Type {
		ACTUAL,
		SURFACE,
		HEIGHTMAP
	}

	public static class HeightmapBiome implements SurfaceRules.ConditionSource {
		public static final KeyDispatchDataCodec<HeightmapBiome> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					ResourceKey.codec(Registries.BIOME).fieldOf("biome").forGetter(r -> r.biome)
				).apply(instance, HeightmapBiome::new)
			)
		);

		ResourceKey<Biome> biome;

		public HeightmapBiome(ResourceKey<Biome> biome) {
			this.biome = biome;
		}

		@Override
		public SurfaceRules.Condition apply(SurfaceRules.Context context) {
			return new HeightmapBiome.Condition(context);
		}

		private final class Condition implements SurfaceRules.Condition {
			private final SurfaceRulesContextAccessor accessor;

			private final BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos();
			private final BlockPos.MutableBlockPos surfacePos = new BlockPos.MutableBlockPos();

			private Holder<Biome> cachedHeightmapBiome = null;
			private int cachedX = Integer.MIN_VALUE;
			private int cachedZ = Integer.MIN_VALUE;

			Condition(SurfaceRules.Context context) {
				this.accessor = (SurfaceRulesContextAccessor) (Object) context;
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
				}

				return cachedHeightmapBiome.is(biome);
			}
		}

		public static SurfaceRules.ConditionSource isBiome(ResourceKey<Biome> biome) {
			return new HeightmapBiome(biome);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}

	public static class HeightmapBiomeTag implements SurfaceRules.ConditionSource {
		public static final KeyDispatchDataCodec<HeightmapBiomeTag> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					TagKey.codec(Registries.BIOME).fieldOf("tag").forGetter(r -> r.biomes)
				).apply(instance, HeightmapBiomeTag::new)
			)
		);

		TagKey<Biome> biomes;

		public HeightmapBiomeTag(TagKey<Biome> biomes) {
			this.biomes = biomes;
		}

		@Override
		public SurfaceRules.Condition apply(SurfaceRules.Context context) {
			return new HeightmapBiomeTag.Condition(context);
		}

		private final class Condition implements SurfaceRules.Condition {
			private final SurfaceRulesContextAccessor accessor;

			private final BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos();
			private final BlockPos.MutableBlockPos surfacePos = new BlockPos.MutableBlockPos();

			private Holder<Biome> cachedHeightmapBiome = null;
			private int cachedX = Integer.MIN_VALUE;
			private int cachedZ = Integer.MIN_VALUE;

			Condition(SurfaceRules.Context context) {
				this.accessor = (SurfaceRulesContextAccessor) (Object) context;
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
				}

				return cachedHeightmapBiome.is(biomes);
			}
		}

		public static SurfaceRules.ConditionSource isBiomeTag(TagKey<Biome> biome) {
			return new HeightmapBiomeTag(biome);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}

	public static class SurfaceBiome implements SurfaceRules.ConditionSource {
		public static final KeyDispatchDataCodec<SurfaceBiome> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					ResourceKey.codec(Registries.BIOME).fieldOf("biome").forGetter(r -> r.biome)
				).apply(instance, SurfaceBiome::new)
			)
		);

		ResourceKey<Biome> biome;

		public SurfaceBiome(ResourceKey<Biome> biome) {
			this.biome = biome;
		}

		@Override
		public SurfaceRules.Condition apply(SurfaceRules.Context context) {
			return new SurfaceBiome.Condition(context);
		}

		private final class Condition implements SurfaceRules.Condition {
			private final SurfaceRulesContextAccessor accessor;
			private final int seaLevel;

			private final BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos();
			private final BlockPos.MutableBlockPos surfacePos = new BlockPos.MutableBlockPos();

			private Holder<Biome> cachedSurfaceBiome = null;
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
					surfacePos.set(x, Math.max(surfaceY, seaLevel), z);
					cachedSurfaceBiome = accessor.getBiomeAtPos().apply(surfacePos);
				}

				return cachedSurfaceBiome.is(biome);
			}
		}

		public static SurfaceRules.ConditionSource isBiome(ResourceKey<Biome> biome) {
			return new SurfaceBiome(biome);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}

	public static class SurfaceBiomeTag implements SurfaceRules.ConditionSource {
		public static final KeyDispatchDataCodec<SurfaceBiomeTag> CODEC = KeyDispatchDataCodec.of(
			RecordCodecBuilder.mapCodec(instance ->
				instance.group(
					TagKey.codec(Registries.BIOME).fieldOf("tag").forGetter(r -> r.biomes)
				).apply(instance, SurfaceBiomeTag::new)
			)
		);

		TagKey<Biome> biomes;

		public SurfaceBiomeTag(TagKey<Biome> biomes) {
			this.biomes = biomes;
		}

		@Override
		public SurfaceRules.Condition apply(SurfaceRules.Context context) {
			return new SurfaceBiomeTag.Condition(context);
		}

		private final class Condition implements SurfaceRules.Condition {
			private final SurfaceRulesContextAccessor accessor;
			private final int seaLevel;

			private final BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos();
			private final BlockPos.MutableBlockPos surfacePos = new BlockPos.MutableBlockPos();

			private Holder<Biome> cachedSurfaceBiome = null;
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
					surfacePos.set(x, Math.max(surfaceY, seaLevel), z);
					cachedSurfaceBiome = accessor.getBiomeAtPos().apply(surfacePos);
				}

				return cachedSurfaceBiome.is(biomes);
			}
		}

		public static SurfaceRules.ConditionSource isBiomeTag(TagKey<Biome> biome) {
			return new SurfaceBiomeTag(biome);
		}

		@Override
		public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
			return CODEC;
		}
	}
}
