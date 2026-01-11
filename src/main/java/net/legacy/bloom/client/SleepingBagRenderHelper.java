package net.legacy.bloom.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.legacy.bloom.Bloom;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MaterialMapper;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import java.util.Arrays;
import java.util.Comparator;

@Environment(EnvType.CLIENT)
public class SleepingBagRenderHelper {
	public static final RenderStateDataKey<Boolean> IS_SLEEPING_BAG = RenderStateDataKey.create();
	public static final Identifier SLEEPING_BAG_SHEET = Bloom.id("textures/atlas/sleeping_bags.png");
	public static final MaterialMapper SLEEPING_BAG_MAPPER = new MaterialMapper(SLEEPING_BAG_SHEET, "entity/sleeping_bag");
	private static final Material[] SLEEPING_BAG_TEXTURES = Arrays.stream(DyeColor.values())
		.sorted(Comparator.comparingInt(DyeColor::getId))
		.map(SleepingBagRenderHelper::createSleepingBagMaterial)
		.toArray(Material[]::new);

	public static Material createSleepingBagMaterial(DyeColor color) {
		return SLEEPING_BAG_MAPPER.apply(Sheets.colorToResourceMaterial(color));
	}

	public static Material getSleepingBagMaterial(DyeColor color) {
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
