package net.rebel459.bloom.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.block.AridVegetationBlock;
import net.rebel459.bloom.block.HalfSubmergedBlock;
import net.rebel459.bloom.block.LargeFlowerBlock;
import net.rebel459.bloom.block.SleepingBagBlock;
import net.rebel459.bloom.block.WideFlowerBlock;
import net.rebel459.bloom.block.WildCropBlock;
import net.rebel459.bloom.sound.BloomBlockSounds;
import net.rebel459.bloom.tag.BloomBlockTags;
import net.rebel459.bloom.tag.BloomItemTags;
import net.rebel459.bloom.util.StoneOresRegistry;
import net.rebel459.bloom.worldgen.sapling.BloomTreeGrowers;
import net.rebel459.unified.api.asset.BlockAssets;
import net.rebel459.unified.api.builder.WoodPreset;
import net.rebel459.unified.api.builder.WoodSet;
import net.rebel459.unified.api.core.SuppliedBlock;
import net.rebel459.unified.api.core.UnifiedData;
import net.rebel459.unified.api.core.UnifiedHelpers;
import net.rebel459.unified.api.core.UnifiedPlatform;
import net.rebel459.unified.api.core.UnifiedRegistries;
import net.rebel459.unified.api.platform.ModLoader;
import net.rebel459.unified.api.registry.UnifiedDataComponents;
import net.rebel459.unified.api.registry.VanillaBlockCodecs;
import net.rebel459.unified.impl.util.BlockItemTagId;

public final class BloomBlocks {
	public static List<Block> TRANSLATABLE_BLOCKS = new ArrayList<>();
	public static List<Block> SMALL_FLOWERS = new ArrayList<>();
	public static List<Block> LARGE_FLOWERS = new ArrayList<>();

	private static final UnifiedRegistries.Blocks BLOCKS = UnifiedRegistries.Blocks.create(Bloom.MOD_ID);
	private static final UnifiedData.Sets SETS = Bloom.DATA.sets();

	// Cotton

	public static final SuppliedBlock COTTON = registerWithoutItem(
		"cotton",
		CropBlock::new,
		() -> BlockBehaviour.Properties.of()
			.mapColor(blockState -> blockState.getValue(CropBlock.AGE) >= 7 ? MapColor.COLOR_BROWN : MapColor.PLANT)
			.noCollision()
			.randomTicks()
			.instabreak()
			.sound(SoundType.CROP)
			.pushReaction(PushReaction.DESTROY)
	);

	public static final SuppliedBlock WILD_COTTON = register("wild_cotton",
		(properties) -> new WildCropBlock(MobEffects.SLOW_FALLING, 4, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
	);

	public static final SuppliedBlock WHITE_SLEEPING_BAG = registerSleepingBag("white_sleeping_bag", DyeColor.WHITE);
	public static final SuppliedBlock ORANGE_SLEEPING_BAG = registerSleepingBag("orange_sleeping_bag", DyeColor.ORANGE);
	public static final SuppliedBlock MAGENTA_SLEEPING_BAG = registerSleepingBag("magenta_sleeping_bag", DyeColor.MAGENTA);
	public static final SuppliedBlock LIGHT_BLUE_SLEEPING_BAG = registerSleepingBag("light_blue_sleeping_bag", DyeColor.LIGHT_BLUE);
	public static final SuppliedBlock YELLOW_SLEEPING_BAG = registerSleepingBag("yellow_sleeping_bag", DyeColor.YELLOW);
	public static final SuppliedBlock LIME_SLEEPING_BAG = registerSleepingBag("lime_sleeping_bag", DyeColor.LIME);
	public static final SuppliedBlock PINK_SLEEPING_BAG = registerSleepingBag("pink_sleeping_bag", DyeColor.PINK);
	public static final SuppliedBlock GRAY_SLEEPING_BAG = registerSleepingBag("gray_sleeping_bag", DyeColor.GRAY);
	public static final SuppliedBlock LIGHT_GRAY_SLEEPING_BAG = registerSleepingBag("light_gray_sleeping_bag", DyeColor.LIGHT_GRAY);
	public static final SuppliedBlock CYAN_SLEEPING_BAG = registerSleepingBag("cyan_sleeping_bag", DyeColor.CYAN);
	public static final SuppliedBlock PURPLE_SLEEPING_BAG = registerSleepingBag("purple_sleeping_bag", DyeColor.PURPLE);
	public static final SuppliedBlock BLUE_SLEEPING_BAG = registerSleepingBag("blue_sleeping_bag", DyeColor.BLUE);
	public static final SuppliedBlock BROWN_SLEEPING_BAG = registerSleepingBag("brown_sleeping_bag", DyeColor.BROWN);
	public static final SuppliedBlock GREEN_SLEEPING_BAG = registerSleepingBag("green_sleeping_bag", DyeColor.GREEN);
	public static final SuppliedBlock RED_SLEEPING_BAG = registerSleepingBag("red_sleeping_bag", DyeColor.RED);
	public static final SuppliedBlock BLACK_SLEEPING_BAG = registerSleepingBag("black_sleeping_bag", DyeColor.BLACK);

	public static final SuppliedBlock WHITE_RUG = registerRug("white_rug", DyeColor.WHITE);
	public static final SuppliedBlock ORANGE_RUG = registerRug("orange_rug", DyeColor.ORANGE);
	public static final SuppliedBlock MAGENTA_RUG = registerRug("magenta_rug", DyeColor.MAGENTA);
	public static final SuppliedBlock LIGHT_BLUE_RUG = registerRug("light_blue_rug", DyeColor.LIGHT_BLUE);
	public static final SuppliedBlock YELLOW_RUG = registerRug("yellow_rug", DyeColor.YELLOW);
	public static final SuppliedBlock LIME_RUG = registerRug("lime_rug", DyeColor.LIME);
	public static final SuppliedBlock PINK_RUG = registerRug("pink_rug", DyeColor.PINK);
	public static final SuppliedBlock GRAY_RUG = registerRug("gray_rug", DyeColor.GRAY);
	public static final SuppliedBlock LIGHT_GRAY_RUG = registerRug("light_gray_rug", DyeColor.LIGHT_GRAY);
	public static final SuppliedBlock CYAN_RUG = registerRug("cyan_rug", DyeColor.CYAN);
	public static final SuppliedBlock PURPLE_RUG = registerRug("purple_rug", DyeColor.PURPLE);
	public static final SuppliedBlock BLUE_RUG = registerRug("blue_rug", DyeColor.BLUE);
	public static final SuppliedBlock BROWN_RUG = registerRug("brown_rug", DyeColor.BROWN);
	public static final SuppliedBlock GREEN_RUG = registerRug("green_rug", DyeColor.GREEN);
	public static final SuppliedBlock RED_RUG = registerRug("red_rug", DyeColor.RED);
	public static final SuppliedBlock BLACK_RUG = registerRug("black_rug", DyeColor.BLACK);

    // Flora

	public static final SuppliedBlock HELLEBORE = register("hellebore",
		properties -> new WideFlowerBlock(MobEffects.SPEED, 5F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_HELLEBORE = registerWithoutItem("potted_hellebore",
		properties -> new FlowerPotBlock(HELLEBORE.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock BROMELIAD = register("bromeliad",
		properties -> new LargeFlowerBlock(MobEffects.JUMP_BOOST, 5F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_BROMELIAD = registerWithoutItem("potted_bromeliad",
		properties -> new FlowerPotBlock(BROMELIAD.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock PINK_ORCHID = register("pink_orchid",
		properties -> new FlowerBlock(MobEffects.SATURATION, 0.35F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_PINK_ORCHID = registerWithoutItem("potted_pink_orchid",
		properties -> new FlowerPotBlock(PINK_ORCHID.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock CALLA_LILY = register("calla_lily",
		properties -> new FlowerBlock(MobEffects.POISON, 11F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_CALLA_LILY = registerWithoutItem("potted_calla_lily",
		properties -> new FlowerPotBlock(CALLA_LILY.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock DIANTHUS = register("dianthus",
		properties -> new WideFlowerBlock(MobEffects.REGENERATION, 7F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_DIANTHUS = registerWithoutItem("potted_dianthus",
		properties -> new FlowerPotBlock(DIANTHUS.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock GOLDENROD = register("goldenrod",
		properties -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 3F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_GOLDENROD = registerWithoutItem("potted_goldenrod",
		properties -> new FlowerPotBlock(GOLDENROD.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock ORANGE_DAISY = register("orange_daisy",
		properties -> new FlowerBlock(MobEffects.REGENERATION, 7F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_ORANGE_DAISY = registerWithoutItem("potted_orange_daisy",
		properties -> new FlowerPotBlock(ORANGE_DAISY.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock SCILLA = register("scilla",
		properties -> new WideFlowerBlock(MobEffects.BLINDNESS, 11F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_SCILLA = registerWithoutItem("potted_scilla",
		properties -> new FlowerPotBlock(SCILLA.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock HYACINTH = register("hyacinth",
		properties -> new FlowerBlock(MobEffects.POISON, 11F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_HYACINTH = registerWithoutItem("potted_hyacinth",
		properties -> new FlowerPotBlock(HYACINTH.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock QUEENCUP = register("queencup",
		properties -> new FlowerBlock(MobEffects.SPEED, 5F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_QUEENCUP = registerWithoutItem("potted_queencup",
		properties -> new FlowerPotBlock(QUEENCUP.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock LAVENDER = register("lavender",
		properties -> new LargeFlowerBlock(MobEffects.REGENERATION, 7F, properties),
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final SuppliedBlock POTTED_LAVENDER = registerWithoutItem("potted_lavender",
		properties -> new FlowerPotBlock(QUEENCUP.get(), properties),
		Blocks::flowerPotProperties
	);

	public static final SuppliedBlock BELLFLOWER = register("bellflower",
		TallFlowerBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)
	);
	public static final SuppliedBlock HYDRANGEA = register("hydrangea",
		TallFlowerBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)
	);

    public static final SuppliedBlock SUCCULENT = register("succulent",
		AridVegetationBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.BUSH)
    );

    public static final SuppliedBlock REEDS = register("reeds",
		HalfSubmergedBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_SEAGRASS)
    );

	public static final SuppliedBlock DOLERITE = register("dolerite",
		Block::new,
		() -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
			.strength(1.8F, 8F)
			.sound(BloomBlockSounds.DOLERITE)
	);

	public static final SuppliedBlock POLISHED_DOLERITE = register("polished_dolerite",
		Block::new,
		() -> BlockBehaviour.Properties.ofFullCopy(DOLERITE.get())
	);
	public static final SuppliedBlock POLISHED_DOLERITE_SLAB = register("polished_dolerite_slab",
		SlabBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(POLISHED_DOLERITE.get())
	);
	public static final SuppliedBlock POLISHED_DOLERITE_STAIRS = registerStair("polished_dolerite_stairs", POLISHED_DOLERITE);
	public static final SuppliedBlock POLISHED_DOLERITE_WALL = register("polished_dolerite_wall",
		WallBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(POLISHED_DOLERITE.get())
			.forceSolidOn()
	);

	public static final SuppliedBlock DOLERITE_BRICKS = register("dolerite_bricks",
		Block::new,
		() -> BlockBehaviour.Properties.ofFullCopy(DOLERITE.get())
	);
	public static final SuppliedBlock DOLERITE_BRICK_SLAB = register("dolerite_brick_slab",
		SlabBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(DOLERITE_BRICKS.get())
	);
	public static final SuppliedBlock DOLERITE_BRICK_STAIRS = registerStair("dolerite_brick_stairs", DOLERITE_BRICKS);
	public static final SuppliedBlock DOLERITE_BRICK_WALL = register("dolerite_brick_wall",
		WallBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(DOLERITE_BRICKS.get())
			.forceSolidOn()
	);

	public static final SuppliedBlock DOLERITE_TILES = register("dolerite_tiles",
		Block::new,
		() -> BlockBehaviour.Properties.ofFullCopy(DOLERITE.get())
	);
	public static final SuppliedBlock DOLERITE_TILE_SLAB = register("dolerite_tile_slab",
		SlabBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(DOLERITE_TILES.get())
	);
	public static final SuppliedBlock DOLERITE_TILE_STAIRS = registerStair("dolerite_tile_stairs", DOLERITE_TILES);
	public static final SuppliedBlock DOLERITE_TILE_WALL = register("dolerite_tile_wall",
		WallBlock::new,
		() -> BlockBehaviour.Properties.ofFullCopy(DOLERITE_TILES.get())
			.forceSolidOn()
	);

	public static final WoodSet JACARANDA = SETS.woodSet("jacaranda", WoodPreset.CHERRY, new BlockItemTagId(BloomBlockTags.JACARANDA_LOGS, BloomItemTags.JACARANDA_LOGS), MapColor.COLOR_PURPLE, MapColor.COLOR_BROWN)
		.createLeaves(WoodSet.Leaves.base(
			VanillaBlockCodecs.UNTINTED_PARTICLE_LEAVES.create(() -> new VanillaBlockCodecs.ParticleLeaves(0.1F, BloomParticleTypes.JACARANDA_LEAVES.get())),
			MapColor.COLOR_PURPLE,
			BlockAssets.LEAVES,
			(block, provider) -> provider.createLeavesDrops(block, block, 0.05F, 0.0625F, 0.083333336F, 0.1F),
			() -> Items.CHERRY_LEAVES
		))
		.createSapling(VanillaBlockCodecs.SAPLING.create(() -> BloomTreeGrowers.JACARANDA), MapColor.COLOR_PURPLE, BlockAssets.PlantType.NOT_TINTED, () -> Items.CHERRY_SAPLING)
		.creativeInventoryPlacement(() -> Items.CHERRY_BUTTON, () -> Items.CHERRY_SAPLING, () -> Items.CHERRY_SHELF, () -> Items.CHERRY_HANGING_SIGN, () -> Items.CHERRY_CHEST_BOAT)
		.build();

	public static final WoodSet GOLDEN_BIRCH = SETS.woodSet("golden_birch", WoodPreset.DEFAULT, new BlockItemTagId(BloomBlockTags.GOLDEN_BIRCH_LOGS, BloomItemTags.GOLDEN_BIRCH_LOGS), MapColor.COLOR_YELLOW, MapColor.COLOR_BROWN)
		.createLeaves(WoodSet.Leaves.base(
			BloomBlockCodecs.UNTINTED_PARTICLE_EXTENDED_LEAVES.create(() -> new VanillaBlockCodecs.ParticleLeaves(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 13807429))),
			MapColor.COLOR_YELLOW,
			BlockAssets.LEAVES,
			(block, provider) -> provider.createLeavesDrops(block, block, 0.05F, 0.0625F, 0.083333336F, 0.1F),
			() -> Items.BIRCH_LEAVES
		))
		.createSapling(VanillaBlockCodecs.SAPLING.create(() -> BloomTreeGrowers.GOLDEN_BIRCH), MapColor.COLOR_YELLOW, BlockAssets.PlantType.NOT_TINTED, () -> Items.BIRCH_SAPLING)
		.creativeInventoryPlacement(() -> Items.BIRCH_BUTTON, () -> Items.BIRCH_SAPLING, () -> Items.BIRCH_SHELF, () -> Items.BIRCH_HANGING_SIGN, () -> Items.BIRCH_CHEST_BOAT)
		.build();

	public static final WoodSet PINE = SETS.woodSet("pine", WoodPreset.DEFAULT, new BlockItemTagId(BloomBlockTags.PINE_LOGS, BloomItemTags.PINE_LOGS), MapColor.COLOR_BROWN, MapColor.COLOR_BROWN)
		.createLeaves(WoodSet.Leaves.base(
			VanillaBlockCodecs.UNTINTED_PARTICLE_LEAVES.create(() -> new VanillaBlockCodecs.ParticleLeaves(0.1F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 5468745))),
			MapColor.COLOR_GREEN,
			BlockAssets.LEAVES,
			(block, provider) -> provider.createLeavesDrops(block, block, 0.05F, 0.0625F, 0.083333336F, 0.1F),
			() -> Items.SPRUCE_LEAVES
		))
		.createSapling(VanillaBlockCodecs.SAPLING.create(() -> BloomTreeGrowers.PINE), MapColor.COLOR_BROWN, BlockAssets.PlantType.NOT_TINTED, () -> Items.SPRUCE_SAPLING)
		.creativeInventoryPlacement(() -> Items.SPRUCE_BUTTON, () -> Items.SPRUCE_SAPLING, () -> Items.SPRUCE_SHELF, () -> Items.SPRUCE_HANGING_SIGN, () -> Items.SPRUCE_CHEST_BOAT)
		.build();

	public static final StoneOresRegistry TUFF_ORES = new StoneOresRegistry(() -> Blocks.TUFF, true).build();
    public static final StoneOresRegistry GRANITE_ORES = new StoneOresRegistry(() -> Blocks.GRANITE, false).build();
    public static final StoneOresRegistry ANDESITE_ORES = new StoneOresRegistry(() -> Blocks.ANDESITE, false).build();
    public static final StoneOresRegistry DIORITE_ORES = new StoneOresRegistry(() -> Blocks.DIORITE, false).build();
    public static final StoneOresRegistry DOLERITE_ORES = new StoneOresRegistry(BloomBlocks.DOLERITE, false, Optional.of("dolerite")).build();
    public static final StoneOresRegistry SANDSTONE_ORES = new StoneOresRegistry(() -> Blocks.SANDSTONE, false).build();
    public static final StoneOresRegistry RED_SANDSTONE_ORES = new StoneOresRegistry(() -> Blocks.RED_SANDSTONE, false).build();

	public static void registerBlockProperties() {
		registerComposting();
		registerFlammability();
	}

	private static void registerComposting() {
		UnifiedHelpers.DATA_COMPONENTS.add(HELLEBORE, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(BROMELIAD, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(PINK_ORCHID, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(BELLFLOWER, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(HYDRANGEA, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(CALLA_LILY, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(DIANTHUS, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(GOLDENROD, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(ORANGE_DAISY, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(SCILLA, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(SUCCULENT, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(BloomItems.COTTON, UnifiedDataComponents.COMPOST.get(), 0.65F);
		UnifiedHelpers.DATA_COMPONENTS.add(BloomItems.COTTON_SEEDS, UnifiedDataComponents.COMPOST.get(), 0.3F);
	}

	private static void registerFlammability() {
		final FireBlock fire = (FireBlock) Blocks.FIRE;
		fire.setFlammable(HELLEBORE.get(), 60, 100);
		fire.setFlammable(BROMELIAD.get(), 60, 100);
		fire.setFlammable(PINK_ORCHID.get(), 60, 100);
		fire.setFlammable(BELLFLOWER.get(), 60, 100);
		fire.setFlammable(HYDRANGEA.get(), 60, 100);
		fire.setFlammable(CALLA_LILY.get(), 60, 100);
		fire.setFlammable(DIANTHUS.get(), 60, 100);
		fire.setFlammable(GOLDENROD.get(), 60, 100);
		fire.setFlammable(ORANGE_DAISY.get(), 60, 100);
		fire.setFlammable(SCILLA.get(), 60, 100);
		fire.setFlammable(SUCCULENT.get(), 60, 100);
    }

	public static void init() {}

	private static SuppliedBlock registerStair(String string, Supplier<Block> block) {
		return register(string, properties -> new StairBlock(block.get().defaultBlockState(), properties), () -> BlockBehaviour.Properties.ofFullCopy(block.get()));
	}

	public static <T extends Block> SuppliedBlock registerWithoutItem(String path, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
		return BLOCKS.registerWithoutItem(path, block, properties);
	}

	public static <T extends Block> SuppliedBlock register(String path, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
		return register(path, block, properties, false);
	}
	public static <T extends Block> SuppliedBlock register(String path, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties, boolean skipNameGen) {
		final SuppliedBlock registered = BLOCKS.register(path, block, properties);
		checkDatagen(registered, skipNameGen);
		return registered;
	}
	public static <T extends Block, Y extends BlockEntity> SuppliedBlock register(String path, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties, Supplier<BlockEntityType<Y>> blockEntity) {
		final SuppliedBlock registered = BLOCKS.register(path, block, properties, blockEntity);
		checkDatagen(registered);
		return registered;
	}

	private static SuppliedBlock registerSleepingBag(String string, DyeColor color) {
		return registerSleepingBag(string, color, false);
	}

	private static SuppliedBlock registerSleepingBag(String string, DyeColor color, boolean skipNameGen) {
		SuppliedBlock block = BLOCKS.registerWithoutItem(
			string,
			(properties) -> new SleepingBagBlock(color, properties),
			() -> BlockBehaviour.Properties.of()
				.mapColor((state) -> state.getValue(SleepingBagBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WOOL)
				.sound(SoundType.WOOL).strength(0.1F)
				.noOcclusion()
				.ignitedByLava()
				.pushReaction(PushReaction.DESTROY),
			() -> BlockEntityType.BED
		);
		checkDatagen(block, skipNameGen);
		return block;
	}

	private static SuppliedBlock registerRug(String string, DyeColor dyeColor) {
		return registerRug(string, dyeColor, false);
	}

	private static SuppliedBlock registerRug(String string, DyeColor dyeColor, boolean skipNameGen) {
		return register(string, (properties) -> new WoolCarpetBlock(dyeColor, properties), () -> BlockBehaviour.Properties.of().mapColor(dyeColor.getMapColor()).sound(SoundType.WOOL).strength(0.1F).ignitedByLava());
	}

	public static void checkDatagen(SuppliedBlock suppliedBlock) {
		checkDatagen(suppliedBlock, false);
	}
	public static void checkDatagen(SuppliedBlock suppliedBlock, boolean skipNameGen) {
		if (UnifiedPlatform.getModLoader() == ModLoader.FABRIC) {
			Block block = suppliedBlock.get();
			if (!skipNameGen) TRANSLATABLE_BLOCKS.add(block);
			if (block instanceof FlowerBlock || block instanceof WideFlowerBlock || block instanceof LargeFlowerBlock) SMALL_FLOWERS.add(block);
			else if (block instanceof TallFlowerBlock) LARGE_FLOWERS.add(block);
		}
	}
}
