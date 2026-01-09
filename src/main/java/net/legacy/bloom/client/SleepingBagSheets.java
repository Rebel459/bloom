package net.legacy.bloom.client;

import net.legacy.bloom.Bloom;
import net.minecraft.client.renderer.MaterialMapper;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import java.util.Arrays;
import java.util.Comparator;

public class SleepingBagSheets {
	public static final Identifier SLEEPING_BAG_SHEET = Bloom.id("textures/atlas/sleeping_bags.png");

	public static final MaterialMapper SLEEPING_BAG_MAPPER = new MaterialMapper(SLEEPING_BAG_SHEET, "entity/sleeping_bag");

	private static final Material[] SLEEPING_BAG_TEXTURES = Arrays.stream(DyeColor.values())
		.sorted(Comparator.comparingInt(DyeColor::getId))
		.map(SleepingBagSheets::createSleepingBagMaterial)
		.toArray(Material[]::new);

	public static Material createSleepingBagMaterial(DyeColor dyeColor) {
		return SLEEPING_BAG_MAPPER.apply(Sheets.colorToResourceMaterial(dyeColor));
	}

	public static Material getSleepingBagMaterial(DyeColor dyeColor) {
		return SLEEPING_BAG_TEXTURES[dyeColor.getId()];
	}

}
