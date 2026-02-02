package net.legacy.bloom.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.block.AridVegetationBlock;
import net.legacy.bloom.block.HalfSubmergedBlock;
import net.legacy.bloom.block.LargeFlowerBlock;
import net.legacy.bloom.block.SleepingBagBlock;
import net.legacy.bloom.block.WideFlowerBlock;
import net.legacy.bloom.block.WildCropBlock;
import net.legacy.bloom.sound.BloomBlockSounds;
import net.legacy.bloom.util.StoneOresRegistry;
import net.legacy.bloom.util.WoodsetRegistry;
import net.legacy.bloom.worldgen.sapling.BloomTreeGrowers;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class BloomBlocks {
	public static List<Block> TRANSLATABLE_BLOCKS = new ArrayList<>();
	public static List<Block> SMALL_FLOWERS = new ArrayList<>();
	public static List<Block> LARGE_FLOWERS = new ArrayList<>();

	// Cotton

	public static final Block COTTON = registerWithoutItem(
		"cotton",
		CropBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(blockState -> blockState.getValue(CropBlock.AGE) >= 7 ? MapColor.COLOR_BROWN : MapColor.PLANT)
			.noCollision()
			.randomTicks()
			.instabreak()
			.sound(SoundType.CROP)
			.pushReaction(PushReaction.DESTROY)
	);

	public static final Block WILD_COTTON = register("wild_cotton",
		(properties) -> new WildCropBlock(MobEffects.SLOW_FALLING, 4, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
	);

	public static final Block WHITE_SLEEPING_BAG = registerSleepingBag("white_sleeping_bag", DyeColor.WHITE);
	public static final Block ORANGE_SLEEPING_BAG = registerSleepingBag("orange_sleeping_bag", DyeColor.ORANGE);
	public static final Block MAGENTA_SLEEPING_BAG = registerSleepingBag("magenta_sleeping_bag", DyeColor.MAGENTA);
	public static final Block LIGHT_BLUE_SLEEPING_BAG = registerSleepingBag("light_blue_sleeping_bag", DyeColor.LIGHT_BLUE);
	public static final Block YELLOW_SLEEPING_BAG = registerSleepingBag("yellow_sleeping_bag", DyeColor.YELLOW);
	public static final Block LIME_SLEEPING_BAG = registerSleepingBag("lime_sleeping_bag", DyeColor.LIME);
	public static final Block PINK_SLEEPING_BAG = registerSleepingBag("pink_sleeping_bag", DyeColor.PINK);
	public static final Block GRAY_SLEEPING_BAG = registerSleepingBag("gray_sleeping_bag", DyeColor.GRAY);
	public static final Block LIGHT_GRAY_SLEEPING_BAG = registerSleepingBag("light_gray_sleeping_bag", DyeColor.LIGHT_GRAY);
	public static final Block CYAN_SLEEPING_BAG = registerSleepingBag("cyan_sleeping_bag", DyeColor.CYAN);
	public static final Block PURPLE_SLEEPING_BAG = registerSleepingBag("purple_sleeping_bag", DyeColor.PURPLE);
	public static final Block BLUE_SLEEPING_BAG = registerSleepingBag("blue_sleeping_bag", DyeColor.BLUE);
	public static final Block BROWN_SLEEPING_BAG = registerSleepingBag("brown_sleeping_bag", DyeColor.BROWN);
	public static final Block GREEN_SLEEPING_BAG = registerSleepingBag("green_sleeping_bag", DyeColor.GREEN);
	public static final Block RED_SLEEPING_BAG = registerSleepingBag("red_sleeping_bag", DyeColor.RED);
	public static final Block BLACK_SLEEPING_BAG = registerSleepingBag("black_sleeping_bag", DyeColor.BLACK);

	public static final Block WHITE_RUG = registerRug("white_rug", DyeColor.WHITE);
	public static final Block ORANGE_RUG = registerRug("orange_rug", DyeColor.ORANGE);
	public static final Block MAGENTA_RUG = registerRug("magenta_rug", DyeColor.MAGENTA);
	public static final Block LIGHT_BLUE_RUG = registerRug("light_blue_rug", DyeColor.LIGHT_BLUE);
	public static final Block YELLOW_RUG = registerRug("yellow_rug", DyeColor.YELLOW);
	public static final Block LIME_RUG = registerRug("lime_rug", DyeColor.LIME);
	public static final Block PINK_RUG = registerRug("pink_rug", DyeColor.PINK);
	public static final Block GRAY_RUG = registerRug("gray_rug", DyeColor.GRAY);
	public static final Block LIGHT_GRAY_RUG = registerRug("light_gray_rug", DyeColor.LIGHT_GRAY);
	public static final Block CYAN_RUG = registerRug("cyan_rug", DyeColor.CYAN);
	public static final Block PURPLE_RUG = registerRug("purple_rug", DyeColor.PURPLE);
	public static final Block BLUE_RUG = registerRug("blue_rug", DyeColor.BLUE);
	public static final Block BROWN_RUG = registerRug("brown_rug", DyeColor.BROWN);
	public static final Block GREEN_RUG = registerRug("green_rug", DyeColor.GREEN);
	public static final Block RED_RUG = registerRug("red_rug", DyeColor.RED);
	public static final Block BLACK_RUG = registerRug("black_rug", DyeColor.BLACK);

    // Flora

	public static final Block HELLEBORE = register("hellebore",
		properties -> new WideFlowerBlock(MobEffects.SPEED, 5F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_HELLEBORE = registerWithoutItem("potted_hellebore",
		properties -> new FlowerPotBlock(HELLEBORE, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block BROMELIAD = register("bromeliad",
		properties -> new LargeFlowerBlock(MobEffects.JUMP_BOOST, 5F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_BROMELIAD = registerWithoutItem("potted_bromeliad",
		properties -> new FlowerPotBlock(BROMELIAD, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block PINK_ORCHID = register("pink_orchid",
		properties -> new FlowerBlock(MobEffects.SATURATION, 0.35F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_PINK_ORCHID = registerWithoutItem("potted_pink_orchid",
		properties -> new FlowerPotBlock(PINK_ORCHID, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block CALLA_LILY = register("calla_lily",
		properties -> new FlowerBlock(MobEffects.POISON, 11F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_CALLA_LILY = registerWithoutItem("potted_calla_lily",
		properties -> new FlowerPotBlock(CALLA_LILY, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block DIANTHUS = register("dianthus",
		properties -> new WideFlowerBlock(MobEffects.REGENERATION, 7F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_DIANTHUS = registerWithoutItem("potted_dianthus",
		properties -> new FlowerPotBlock(DIANTHUS, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block GOLDENROD = register("goldenrod",
		properties -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 3F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_GOLDENROD = registerWithoutItem("potted_goldenrod",
		properties -> new FlowerPotBlock(GOLDENROD, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block ORANGE_DAISY = register("orange_daisy",
		properties -> new FlowerBlock(MobEffects.REGENERATION, 7F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_ORANGE_DAISY = registerWithoutItem("potted_orange_daisy",
		properties -> new FlowerPotBlock(ORANGE_DAISY, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block SCILLA = register("scilla",
		properties -> new WideFlowerBlock(MobEffects.BLINDNESS, 11F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_SCILLA = registerWithoutItem("potted_scilla",
		properties -> new FlowerPotBlock(SCILLA, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block HYACINTH = register("hyacinth",
		properties -> new FlowerBlock(MobEffects.POISON, 11F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_HYACINTH = registerWithoutItem("potted_hyacinth",
		properties -> new FlowerPotBlock(HYACINTH, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block QUEENCUP = register("queencup",
		properties -> new FlowerBlock(MobEffects.SPEED, 5F, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_QUEENCUP = registerWithoutItem("potted_queencup",
		properties -> new FlowerPotBlock(QUEENCUP, properties),
		Blocks.flowerPotProperties()
	);

	public static final Block BELLFLOWER = register("bellflower",
		TallFlowerBlock::new,
		BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)
	);
	public static final Block HYDRANGEA = register("hydrangea",
		TallFlowerBlock::new,
		BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)
	);

    public static final Block SUCCULENT = register("succulent",
		AridVegetationBlock::new,
		BlockBehaviour.Properties.ofFullCopy(Blocks.BUSH)
    );

    public static final Block REEDS = register("reeds",
		HalfSubmergedBlock::new,
		BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_SEAGRASS)
    );

	public static final Block DOLERITE = register("dolerite",
		Block::new,
		BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
			.strength(1.8F, 8F)
			.sound(BloomBlockSounds.DOLERITE)
	);

	public static final Block POLISHED_DOLERITE = register("polished_dolerite",
		Block::new,
		BlockBehaviour.Properties.ofFullCopy(DOLERITE)
	);
	public static final Block POLISHED_DOLERITE_SLAB = register("polished_dolerite_slab",
		SlabBlock::new,
		BlockBehaviour.Properties.ofFullCopy(POLISHED_DOLERITE)
	);
	public static final Block POLISHED_DOLERITE_STAIRS = registerStair("polished_dolerite_stairs", POLISHED_DOLERITE);
	public static final Block POLISHED_DOLERITE_WALL = register("polished_dolerite_wall",
		WallBlock::new,
		BlockBehaviour.Properties.ofFullCopy(POLISHED_DOLERITE)
			.forceSolidOn()
	);

	public static final Block DOLERITE_BRICKS = register("dolerite_bricks",
		Block::new,
		BlockBehaviour.Properties.ofFullCopy(DOLERITE)
	);
	public static final Block DOLERITE_BRICK_SLAB = register("dolerite_brick_slab",
		SlabBlock::new,
		BlockBehaviour.Properties.ofFullCopy(DOLERITE_BRICKS)
	);
	public static final Block DOLERITE_BRICK_STAIRS = registerStair("dolerite_brick_stairs", DOLERITE_BRICKS);
	public static final Block DOLERITE_BRICK_WALL = register("dolerite_brick_wall",
		WallBlock::new,
		BlockBehaviour.Properties.ofFullCopy(DOLERITE_BRICKS)
			.forceSolidOn()
	);

	public static final Block DOLERITE_TILES = register("dolerite_tiles",
		Block::new,
		BlockBehaviour.Properties.ofFullCopy(DOLERITE)
	);
	public static final Block DOLERITE_TILE_SLAB = register("dolerite_tile_slab",
		SlabBlock::new,
		BlockBehaviour.Properties.ofFullCopy(DOLERITE_TILES)
	);
	public static final Block DOLERITE_TILE_STAIRS = registerStair("dolerite_tile_stairs", DOLERITE_TILES);
	public static final Block DOLERITE_TILE_WALL = register("dolerite_tile_wall",
		WallBlock::new,
		BlockBehaviour.Properties.ofFullCopy(DOLERITE_TILES)
			.forceSolidOn()
	);

	public static final WoodsetRegistry JACARANDA = new WoodsetRegistry(Bloom.id("jacaranda"), MapColor.COLOR_PURPLE, MapColor.COLOR_BROWN, new WoodsetRegistry.Settings.Builder().woodPreset(WoodsetRegistry.WoodPreset.FANCY));
	public static final SaplingBlock JACARANDA_SAPLING = register("jacaranda_sapling",
		properties -> new SaplingBlock(BloomTreeGrowers.JACARANDA, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SAPLING)
	);
	public static final Block POTTED_JACARANDA_SAPLING = registerWithoutItem("potted_jacaranda_sapling",
		properties -> new FlowerPotBlock(JACARANDA_SAPLING, properties),
		Blocks.flowerPotProperties()
	);

	public static final WoodsetRegistry GOLDEN_BIRCH = new WoodsetRegistry(Bloom.id("golden_birch"), MapColor.COLOR_YELLOW, MapColor.COLOR_BROWN, new WoodsetRegistry.Settings.Builder().woodPreset(WoodsetRegistry.WoodPreset.DEFAULT));
	public static final SaplingBlock GOLDEN_BIRCH_SAPLING = register("golden_birch_sapling",
		properties -> new SaplingBlock(BloomTreeGrowers.GOLDEN_BIRCH, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SAPLING)
			.mapColor(MapColor.COLOR_YELLOW)
	);
	public static final Block POTTED_GOLDEN_BIRCH_SAPLING = registerWithoutItem("potted_golden_birch_sapling",
		properties -> new FlowerPotBlock(JACARANDA_SAPLING, properties),
		Blocks.flowerPotProperties()
	);

	public static final WoodsetRegistry PINE = new WoodsetRegistry(Bloom.id("pine"), MapColor.COLOR_BROWN, MapColor.COLOR_BROWN, new WoodsetRegistry.Settings.Builder().woodPreset(WoodsetRegistry.WoodPreset.DEFAULT));
	public static final SaplingBlock PINE_SAPLING = register("pine_sapling",
		properties -> new SaplingBlock(BloomTreeGrowers.PINE, properties),
		BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)
			.mapColor(MapColor.COLOR_GREEN)
	);
	public static final Block POTTED_PINE_SAPLING = registerWithoutItem("potted_pine_sapling",
		properties -> new FlowerPotBlock(JACARANDA_SAPLING, properties),
		Blocks.flowerPotProperties()
	);

	public static final StoneOresRegistry TUFF_ORES = new StoneOresRegistry(Blocks.TUFF, true).build();
    public static final StoneOresRegistry GRANITE_ORES = new StoneOresRegistry(Blocks.GRANITE, false).build();
    public static final StoneOresRegistry ANDESITE_ORES = new StoneOresRegistry(Blocks.ANDESITE, false).build();
    public static final StoneOresRegistry DIORITE_ORES = new StoneOresRegistry(Blocks.DIORITE, false).build();
    public static final StoneOresRegistry DOLERITE_ORES = new StoneOresRegistry(BloomBlocks.DOLERITE, false).build();
    public static final StoneOresRegistry SANDSTONE_ORES = new StoneOresRegistry(Blocks.SANDSTONE, false).build();
    public static final StoneOresRegistry RED_SANDSTONE_ORES = new StoneOresRegistry(Blocks.RED_SANDSTONE, false).build();

	public static void registerBlockProperties() {
		registerStrippable();
		registerComposting();
		registerFlammability();
		registerFuels();
	}

	private static void registerStrippable() {
	}

	private static void registerComposting() {
		CompostingChanceRegistry.INSTANCE.add(HELLEBORE, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(BROMELIAD, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(PINK_ORCHID, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(BELLFLOWER, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(HYDRANGEA, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(CALLA_LILY, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(DIANTHUS, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(GOLDENROD, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(ORANGE_DAISY, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(SCILLA, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(SUCCULENT, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(BloomItems.COTTON, 0.65F);
		CompostingChanceRegistry.INSTANCE.add(BloomItems.COTTON_SEEDS, 0.3F);
	}

	private static void registerFlammability() {
		final var flammableBlockRegistry = FlammableBlockRegistry.getDefaultInstance();
		flammableBlockRegistry.add(HELLEBORE, 60, 100);
		flammableBlockRegistry.add(BROMELIAD, 60, 100);
		flammableBlockRegistry.add(PINK_ORCHID, 60, 100);
		flammableBlockRegistry.add(BELLFLOWER, 60, 100);
		flammableBlockRegistry.add(HYDRANGEA, 60, 100);
		flammableBlockRegistry.add(CALLA_LILY, 60, 100);
		flammableBlockRegistry.add(DIANTHUS, 60, 100);
		flammableBlockRegistry.add(GOLDENROD, 60, 100);
		flammableBlockRegistry.add(ORANGE_DAISY, 60, 100);
		flammableBlockRegistry.add(SCILLA, 60, 100);
		flammableBlockRegistry.add(SUCCULENT, 60, 100);

    }

	private static void registerFuels() {
		FuelRegistryEvents.BUILD.register((builder, context) -> {
		});
	}

	public static void init() {
		registerBlockProperties();
	}

	private static Block registerStair(String string, Block block) {
		return register(string, properties -> new StairBlock(block.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(block));
	}

	private static <T extends Block> T registerWithoutItem(String path, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
		final Identifier id = Bloom.id(path);
		return doRegister(id, makeBlock(block, properties, id));
	}

	public static <T extends Block> T register(String path, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
		return register(path, block, properties, false);
	}
	public static <T extends Block> T register(String path, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties, boolean skipNameGen) {
		final T registered = registerWithoutItem(path, block, properties);
		registerBlockItem(registered, skipNameGen);
		return registered;
	}

	private static <T extends Block> T doRegister(Identifier id, T block) {
		if (BuiltInRegistries.BLOCK.getOptional(id).isEmpty()) return Registry.register(BuiltInRegistries.BLOCK, id, block);
		throw new IllegalArgumentException("Block with id " + id + " is already in the block registry.");
	}

	private static <T extends Block> T makeBlock(Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties, Identifier id) {
		return function.apply(properties.setId(ResourceKey.create(Registries.BLOCK, id)));
	}

	private static void registerBlockItem(Block block, boolean skipNameGen) {
		if (!skipNameGen) TRANSLATABLE_BLOCKS.add(block);
		if (block instanceof FlowerBlock || block instanceof WideFlowerBlock || block instanceof LargeFlowerBlock) SMALL_FLOWERS.add(block);
		else if (block instanceof TallFlowerBlock) LARGE_FLOWERS.add(block);
		BiFunction<Block, Item.Properties, Item> itemSupplier = BlockItem::new;
		if (block instanceof DoorBlock || block instanceof TallFlowerBlock) itemSupplier = DoubleHighBlockItem::new;
		if (block instanceof ShelfBlock) itemSupplier = (shelfBlock, properties) -> new BlockItem(shelfBlock, properties.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
		Items.registerBlock(block, itemSupplier);
	}

	private static Block registerSleepingBag(String string, DyeColor color) {
		return registerSleepingBag(string, color, false);
	}

	private static Block registerSleepingBag(String string, DyeColor color, boolean skipNameGen) {
		Block block = registerWithoutItem(
			string,
			(properties) -> new SleepingBagBlock(color, properties),
			BlockBehaviour.Properties.of()
				.mapColor((state) -> state.getValue(SleepingBagBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WOOL)
				.sound(SoundType.WOOL).strength(0.1F)
				.noOcclusion()
				.ignitedByLava()
				.pushReaction(PushReaction.DESTROY)
		);
		if (!skipNameGen) TRANSLATABLE_BLOCKS.add(block);
		return block;
	}

	private static Block registerRug(String string, DyeColor dyeColor) {
		return registerRug(string, dyeColor, false);
	}

	private static Block registerRug(String string, DyeColor dyeColor, boolean skipNameGen) {
		return register(string, (properties) -> new WoolCarpetBlock(dyeColor, properties), BlockBehaviour.Properties.of().mapColor(dyeColor.getMapColor()).sound(SoundType.WOOL).strength(0.1F).ignitedByLava());
	}
}
