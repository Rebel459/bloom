package net.rebel459.bloom.client;

import net.minecraft.client.renderer.SpriteMapper;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.rebel459.bloom.Bloom;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import java.util.Arrays;
import java.util.Comparator;

public class SleepingBagRenderHelper {
	public static final Identifier SLEEPING_BAG_SHEET = Bloom.id("textures/atlas/sleeping_bags.png");
	public static final SpriteMapper SLEEPING_BAG_MAPPER = new SpriteMapper(SLEEPING_BAG_SHEET, "entity/sleeping_bag");
	private static final SpriteId[] SLEEPING_BAG_TEXTURES = Arrays.stream(DyeColor.values())
		.sorted(Comparator.comparingInt(DyeColor::getId))
		.map(SleepingBagRenderHelper::createSleepingBagMaterial)
		.toArray(SpriteId[]::new);

	public static SpriteId createSleepingBagMaterial(DyeColor color) {
		return SLEEPING_BAG_MAPPER.apply(Sheets.colorToResourceSprite(color));
	}

	public static SpriteId getSleepingBagMaterial(DyeColor color) {
		return SLEEPING_BAG_TEXTURES[color.getId()];
	}

	public static LayerDefinition createHeadLayer() {
		final MeshDefinition mesh = new MeshDefinition();
		final PartDefinition root = mesh.getRoot();
		root.addOrReplaceChild(
			"main",
			CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(0F, 0F, 8F, 16F, 16F, 1F),
			PartPose.ZERO
		);
		return LayerDefinition.create(mesh, 64, 64);
	}

	public static LayerDefinition createFootLayer() {
		final MeshDefinition mesh = new MeshDefinition();
		final PartDefinition root = mesh.getRoot();
		root.addOrReplaceChild(
			"main",
			CubeListBuilder.create()
				.texOffs(0, 17)
				.addBox(0F, 0F, 8F, 16F, 16F, 1F),
			PartPose.ZERO
		);
		return LayerDefinition.create(mesh, 64, 64);
	}

}
