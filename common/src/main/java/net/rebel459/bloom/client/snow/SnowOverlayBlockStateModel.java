package net.rebel459.bloom.client.snow;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.chunk.RenderSectionRegion;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

public final class SnowOverlayBlockStateModel implements BlockStateModel {
	private final BlockStateModel original;
	private final BlockAndTintGetter region;
	private final BlockPos pos;
	private final BlockState state;
	private final boolean includeOriginal;
	private final Vec3 overlayOffset;

	public SnowOverlayBlockStateModel(
		BlockStateModel original,
		BlockAndTintGetter region,
		BlockPos pos,
		BlockState state,
		boolean includeOriginal,
		Vec3 overlayOffset
	) {
		this.original = original;
		this.region = region;
		this.pos = pos.immutable();
		this.state = state;
		this.includeOriginal = includeOriginal;
		this.overlayOffset = overlayOffset;
	}

	@Override
	public void collectParts(RandomSource random, List<BlockStateModelPart> parts) {
		if (this.includeOriginal) {
			this.original.collectParts(random, parts);
		}

		SnowOverlayBlockStateModelPart part = new SnowOverlayBlockStateModelPart(
			this.region,
			this.pos,
			this.state,
			this.overlayOffset
		);

		if (!part.isEmpty()) {
			parts.add(part);
		}
	}

	@Override
	public Material.Baked particleMaterial() {
		return this.original.particleMaterial();
	}

	@Override
	public int materialFlags() {
		return this.original.materialFlags();
	}
}
