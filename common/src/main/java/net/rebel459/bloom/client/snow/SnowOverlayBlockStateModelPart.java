package net.rebel459.bloom.client.snow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.chunk.RenderSectionRegion;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.rebel459.bloom.Bloom;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public final class SnowOverlayBlockStateModelPart implements BlockStateModelPart {
	private static final Identifier SNOW_VERTICAL = Bloom.id("block/snow_vertical");
	private static final Identifier VANILLA_SNOW = Identifier.withDefaultNamespace("block/snow");

	private static final float OFFSET = 0.01F;
	private static final float CORNER_EXTEND = 0.01F;

	private static final List<BakedQuad> NO_QUADS = List.of();

	private static Materials cachedMaterials;

	private final List<BakedQuad> northQuads;
	private final List<BakedQuad> southQuads;
	private final List<BakedQuad> westQuads;
	private final List<BakedQuad> eastQuads;
	private final List<BakedQuad> unculledQuads;

	private final float offsetX;
	private final float offsetY;
	private final float offsetZ;

	public SnowOverlayBlockStateModelPart(BlockAndTintGetter region, BlockPos pos, BlockState state, Vec3 overlayOffset) {
		this.offsetX = (float) -overlayOffset.x;
		this.offsetY = (float) -overlayOffset.y;
		this.offsetZ = (float) -overlayOffset.z;
		Materials materials = materials();

		BlockPos immutablePos = pos.immutable();
		BlockPos belowPos = immutablePos.below();

		boolean renderNorth = shouldRenderOverlaySide(region, immutablePos, belowPos, Direction.NORTH);
		boolean renderSouth = shouldRenderOverlaySide(region, immutablePos, belowPos, Direction.SOUTH);
		boolean renderWest = shouldRenderOverlaySide(region, immutablePos, belowPos, Direction.WEST);
		boolean renderEast = shouldRenderOverlaySide(region, immutablePos, belowPos, Direction.EAST);

		this.northQuads = renderNorth
			? List.of(quadNorth(materials.verticalSprite(), materials.verticalInfo(), -1.0F, 0.0F, 0.0F, 16.0F, renderWest, renderEast))
			: NO_QUADS;

		this.southQuads = renderSouth
			? List.of(quadSouth(materials.verticalSprite(), materials.verticalInfo(), -1.0F, 0.0F, 0.0F, 16.0F, renderWest, renderEast))
			: NO_QUADS;

		this.westQuads = renderWest
			? List.of(quadWest(materials.verticalSprite(), materials.verticalInfo(), -1.0F, 0.0F, 0.0F, 16.0F, renderNorth, renderSouth))
			: NO_QUADS;

		this.eastQuads = renderEast
			? List.of(quadEast(materials.verticalSprite(), materials.verticalInfo(), -1.0F, 0.0F, 0.0F, 16.0F, renderNorth, renderSouth))
			: NO_QUADS;

		List<BakedQuad> lips = null;

		if (renderNorth) {
			if (lips == null) lips = new ArrayList<>(4);
			lips.add(quadNorthLip(materials.snowSprite(), materials.snowInfo(), renderWest, renderEast));
		}

		if (renderSouth) {
			if (lips == null) lips = new ArrayList<>(4);
			lips.add(quadSouthLip(materials.snowSprite(), materials.snowInfo(), renderWest, renderEast));
		}

		if (renderWest) {
			if (lips == null) lips = new ArrayList<>(4);
			lips.add(quadWestLip(materials.snowSprite(), materials.snowInfo(), renderNorth, renderSouth));
		}

		if (renderEast) {
			if (lips == null) lips = new ArrayList<>(4);
			lips.add(quadEastLip(materials.snowSprite(), materials.snowInfo(), renderNorth, renderSouth));
		}

		this.unculledQuads = lips == null ? NO_QUADS : List.copyOf(lips);
	}

	public static boolean wouldRender(BlockAndTintGetter region, BlockPos pos, BlockState state) {
		if (!SnowOverlayHelper.isOverlaySource(state)) {
			return false;
		}

		BlockPos belowPos = pos.below();

		for (Direction direction : Direction.Plane.HORIZONTAL) {
			if (shouldRenderOverlaySide(region, pos, belowPos, direction)) {
				return true;
			}
		}

		return false;
	}

	public boolean isEmpty() {
		return this.northQuads.isEmpty()
			&& this.southQuads.isEmpty()
			&& this.westQuads.isEmpty()
			&& this.eastQuads.isEmpty()
			&& this.unculledQuads.isEmpty();
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable Direction direction) {
		return switch (direction) {
			case null -> this.unculledQuads;
			case NORTH -> this.northQuads;
			case SOUTH -> this.southQuads;
			case WEST -> this.westQuads;
			case EAST -> this.eastQuads;
			default -> NO_QUADS;
		};
	}

	@Override
	public boolean useAmbientOcclusion() {
		return true;
	}

	@Override
	public Material.Baked particleMaterial() {
		return materials().snowMaterial();
	}

	@Override
	public int materialFlags() {
		Materials materials = materials();
		return materials.snowInfo().flags() | materials.verticalInfo().flags();
	}

	private static boolean shouldRenderOverlaySide(BlockAndTintGetter level, BlockPos pos, BlockPos belowPos, Direction direction) {
		BlockState adjacentSourceState = level.getBlockState(pos.relative(direction));
		if (SnowOverlayHelper.isOverlaySource(adjacentSourceState)) {
			return false;
		}

		BlockState supportState = level.getBlockState(belowPos);

		boolean supportIsLeaves = supportState.is(BlockTags.LEAVES);

		if (!supportIsLeaves && !supportState.isFaceSturdy(level, belowPos, direction)) {
			return false;
		}

		BlockPos neighbourSupportPos = belowPos.relative(direction);
		BlockState neighbourSupportState = level.getBlockState(neighbourSupportPos);

		if (neighbourSupportState.isAir()) {
			return true;
		}

		if (neighbourSupportState.is(BlockTags.LEAVES)) {
			return false;
		}

		if (neighbourSupportState.getCollisionShape(level, neighbourSupportPos) == Shapes.block()) {
			return false;
		}

		return !neighbourSupportState.isFaceSturdy(level, neighbourSupportPos, direction.getOpposite());
	}

	private static Materials materials() {
		Materials cached = cachedMaterials;
		if (cached != null) {
			return cached;
		}

		var blockAtlas = Minecraft.getInstance()
			.getAtlasManager()
			.getAtlasOrThrow(Identifier.withDefaultNamespace("blocks"));

		TextureAtlasSprite verticalSprite = blockAtlas.getSprite(SNOW_VERTICAL);
		TextureAtlasSprite snowSprite = blockAtlas.getSprite(VANILLA_SNOW);

		Material.Baked verticalMaterial = new Material.Baked(verticalSprite, false);
		Material.Baked snowMaterial = new Material.Baked(snowSprite, false);

		BakedQuad.MaterialInfo verticalInfo = BakedQuad.MaterialInfo.of(
			verticalMaterial,
			com.mojang.blaze3d.platform.Transparency.TRANSPARENT,
			-1,
			true,
			0
		);

		BakedQuad.MaterialInfo snowInfo = BakedQuad.MaterialInfo.of(
			snowMaterial,
			com.mojang.blaze3d.platform.Transparency.TRANSPARENT,
			-1,
			true,
			0
		);

		cached = new Materials(verticalSprite, snowSprite, verticalMaterial, snowMaterial, verticalInfo, snowInfo);
		cachedMaterials = cached;
		return cached;
	}

	private BakedQuad quadNorth(
		TextureAtlasSprite sprite,
		BakedQuad.MaterialInfo materialInfo,
		float y0,
		float y1,
		float vStart,
		float vEnd,
		boolean extendWest,
		boolean extendEast
	) {
		float z = -OFFSET;
		float x0 = 0.0F - (extendWest ? CORNER_EXTEND : 0.0F);
		float x1 = 1.0F + (extendEast ? CORNER_EXTEND : 0.0F);

		return new BakedQuad(
			pos(x0, y1, z),
			pos(x1, y1, z),
			pos(x1, y0, z),
			pos(x0, y0, z),
			uv(sprite, 0.0F, vStart),
			uv(sprite, 16.0F, vStart),
			uv(sprite, 16.0F, vEnd),
			uv(sprite, 0.0F, vEnd),
			Direction.NORTH,
			materialInfo
		);
	}

	private BakedQuad quadSouth(
		TextureAtlasSprite sprite,
		BakedQuad.MaterialInfo materialInfo,
		float y0,
		float y1,
		float vStart,
		float vEnd,
		boolean extendWest,
		boolean extendEast
	) {
		float z = 1.0F + OFFSET;
		float x0 = 0.0F - (extendWest ? CORNER_EXTEND : 0.0F);
		float x1 = 1.0F + (extendEast ? CORNER_EXTEND : 0.0F);

		return new BakedQuad(
			pos(x1, y1, z),
			pos(x0, y1, z),
			pos(x0, y0, z),
			pos(x1, y0, z),
			uv(sprite, 0.0F, vStart),
			uv(sprite, 16.0F, vStart),
			uv(sprite, 16.0F, vEnd),
			uv(sprite, 0.0F, vEnd),
			Direction.SOUTH,
			materialInfo
		);
	}

	private BakedQuad quadWest(
		TextureAtlasSprite sprite,
		BakedQuad.MaterialInfo materialInfo,
		float y0,
		float y1,
		float vStart,
		float vEnd,
		boolean extendNorth,
		boolean extendSouth
	) {
		float x = -OFFSET;
		float z0 = 0.0F - (extendNorth ? CORNER_EXTEND : 0.0F);
		float z1 = 1.0F + (extendSouth ? CORNER_EXTEND : 0.0F);

		return new BakedQuad(
			pos(x, y1, z1),
			pos(x, y1, z0),
			pos(x, y0, z0),
			pos(x, y0, z1),
			uv(sprite, 0.0F, vStart),
			uv(sprite, 16.0F, vStart),
			uv(sprite, 16.0F, vEnd),
			uv(sprite, 0.0F, vEnd),
			Direction.WEST,
			materialInfo
		);
	}

	private BakedQuad quadEast(
		TextureAtlasSprite sprite,
		BakedQuad.MaterialInfo materialInfo,
		float y0,
		float y1,
		float vStart,
		float vEnd,
		boolean extendNorth,
		boolean extendSouth
	) {
		float x = 1.0F + OFFSET;
		float z0 = 0.0F - (extendNorth ? CORNER_EXTEND : 0.0F);
		float z1 = 1.0F + (extendSouth ? CORNER_EXTEND : 0.0F);

		return new BakedQuad(
			pos(x, y1, z0),
			pos(x, y1, z1),
			pos(x, y0, z1),
			pos(x, y0, z0),
			uv(sprite, 0.0F, vStart),
			uv(sprite, 16.0F, vStart),
			uv(sprite, 16.0F, vEnd),
			uv(sprite, 0.0F, vEnd),
			Direction.EAST,
			materialInfo
		);
	}

	private BakedQuad quadNorthLip(
		TextureAtlasSprite sprite,
		BakedQuad.MaterialInfo materialInfo,
		boolean extendWest,
		boolean extendEast
	) {
		float x0 = 0.0F - (extendWest ? CORNER_EXTEND : 0.0F);
		float x1 = 1.0F + (extendEast ? CORNER_EXTEND : 0.0F);

		return new BakedQuad(
			pos(x0, 0.0F, 0.0F),
			pos(x1, 0.0F, 0.0F),
			pos(x1, 0.0F, -OFFSET),
			pos(x0, 0.0F, -OFFSET),
			uv(sprite, 0.0F, 15.75F),
			uv(sprite, 16.0F, 15.75F),
			uv(sprite, 16.0F, 16.0F),
			uv(sprite, 0.0F, 16.0F),
			Direction.UP,
			materialInfo
		);
	}

	private BakedQuad quadSouthLip(
		TextureAtlasSprite sprite,
		BakedQuad.MaterialInfo materialInfo,
		boolean extendWest,
		boolean extendEast
	) {
		float x0 = 0.0F - (extendWest ? CORNER_EXTEND : 0.0F);
		float x1 = 1.0F + (extendEast ? CORNER_EXTEND : 0.0F);

		return new BakedQuad(
			pos(x1, 0.0F, 1.0F),
			pos(x0, 0.0F, 1.0F),
			pos(x0, 0.0F, 1.0F + OFFSET),
			pos(x1, 0.0F, 1.0F + OFFSET),
			uv(sprite, 0.0F, 15.75F),
			uv(sprite, 16.0F, 15.75F),
			uv(sprite, 16.0F, 16.0F),
			uv(sprite, 0.0F, 16.0F),
			Direction.UP,
			materialInfo
		);
	}

	private BakedQuad quadWestLip(
		TextureAtlasSprite sprite,
		BakedQuad.MaterialInfo materialInfo,
		boolean extendNorth,
		boolean extendSouth
	) {
		float z0 = 0.0F - (extendNorth ? CORNER_EXTEND : 0.0F);
		float z1 = 1.0F + (extendSouth ? CORNER_EXTEND : 0.0F);

		return new BakedQuad(
			pos(0.0F, 0.0F, z1),
			pos(0.0F, 0.0F, z0),
			pos(-OFFSET, 0.0F, z0),
			pos(-OFFSET, 0.0F, z1),
			uv(sprite, 0.0F, 15.75F),
			uv(sprite, 16.0F, 15.75F),
			uv(sprite, 16.0F, 16.0F),
			uv(sprite, 0.0F, 16.0F),
			Direction.UP,
			materialInfo
		);
	}

	private BakedQuad quadEastLip(
		TextureAtlasSprite sprite,
		BakedQuad.MaterialInfo materialInfo,
		boolean extendNorth,
		boolean extendSouth
	) {
		float z0 = 0.0F - (extendNorth ? CORNER_EXTEND : 0.0F);
		float z1 = 1.0F + (extendSouth ? CORNER_EXTEND : 0.0F);

		return new BakedQuad(
			pos(1.0F, 0.0F, z0),
			pos(1.0F, 0.0F, z1),
			pos(1.0F + OFFSET, 0.0F, z1),
			pos(1.0F + OFFSET, 0.0F, z0),
			uv(sprite, 0.0F, 15.75F),
			uv(sprite, 16.0F, 15.75F),
			uv(sprite, 16.0F, 16.0F),
			uv(sprite, 0.0F, 16.0F),
			Direction.UP,
			materialInfo
		);
	}

	private Vector3f pos(float x, float y, float z) {
		return new Vector3f(
			x + this.offsetX,
			y + this.offsetY,
			z + this.offsetZ
		);
	}

	private static long uv(TextureAtlasSprite sprite, float u, float v) {
		float atlasU = sprite.getU0() + (u / 16.0F) * (sprite.getU1() - sprite.getU0());
		float atlasV = sprite.getV0() + (v / 16.0F) * (sprite.getV1() - sprite.getV0());

		return ((long) Float.floatToRawIntBits(atlasU) << 32)
			| (Float.floatToRawIntBits(atlasV) & 0xFFFFFFFFL);
	}

	private record Materials(
		TextureAtlasSprite verticalSprite,
		TextureAtlasSprite snowSprite,
		Material.Baked verticalMaterial,
		Material.Baked snowMaterial,
		BakedQuad.MaterialInfo verticalInfo,
		BakedQuad.MaterialInfo snowInfo
	) {
	}
}
