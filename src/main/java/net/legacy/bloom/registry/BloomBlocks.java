package net.legacy.bloom.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.block.AridVegetationBlock;
import net.legacy.bloom.block.LargeFlowerBlock;
import net.legacy.bloom.block.WideFlowerBlock;
import net.legacy.bloom.worldgen.sapling.BloomTreeGrowers;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class BloomBlocks {
    public static final BlockSetType JACARANDA_SET = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(Bloom.id("jacaranda"));
    public static final WoodType JACARANDA_WOOD_TYPE = WoodTypeBuilder.copyOf(WoodType.CHERRY).register(Bloom.id("jacaranda"), JACARANDA_SET);
    
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
    
    // Jacaranda

    public static final SaplingBlock JACARANDA_SAPLING = register("jacaranda_sapling",
            properties -> new SaplingBlock(BloomTreeGrowers.JACARANDA, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SAPLING)
    );
    public static final Block POTTED_JACARANDA_SAPLING = registerWithoutItem("potted_jacaranda_sapling",
            properties -> new FlowerPotBlock(JACARANDA_SAPLING, properties),
            Blocks.flowerPotProperties()
    );

    public static final Block JACARANDA_LEAVES = register(
            "jacaranda_leaves",
            properties -> new UntintedParticleLeavesBlock(0.1F, BloomParticleTypes.JACARANDA_LEAVES, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES)
    );

    public static final Block JACARANDA_PLANKS = register("jacaranda_planks",
            Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)
                    .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final StairBlock JACARANDA_STAIRS = register("jacaranda_stairs",
            properties -> new StairBlock(JACARANDA_PLANKS.defaultBlockState(), properties),
            BlockBehaviour.Properties.ofFullCopy(JACARANDA_PLANKS)
    );
    public static final Block JACARANDA_FENCE_GATE = register("jacaranda_fence_gate",
            properties -> new FenceGateBlock(JACARANDA_WOOD_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
                    .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final SlabBlock JACARANDA_SLAB = register("jacaranda_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
                    .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final Block JACARANDA_BUTTON = register("jacaranda_button",
            properties -> new ButtonBlock(JACARANDA_SET, 30, properties),
            Blocks.buttonProperties()
    );
    public static final PressurePlateBlock JACARANDA_PRESSURE_PLATE = register("jacaranda_pressure_plate",
            properties -> new PressurePlateBlock(JACARANDA_SET, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
                    .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final DoorBlock JACARANDA_DOOR = register("jacaranda_door",
            properties -> new DoorBlock(JACARANDA_SET, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)
                    .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final TrapDoorBlock JACARANDA_TRAPDOOR = register("jacaranda_trapdoor",
            properties -> new TrapDoorBlock(JACARANDA_SET, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
                    .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final FenceBlock JACARANDA_FENCE = register("jacaranda_fence",
            FenceBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
                    .mapColor(MapColor.COLOR_PURPLE)
    );

    public static final Block JACARANDA_LOG = register("jacaranda_log",
            RotatedPillarBlock::new,
            Blocks.logProperties(MapColor.COLOR_PURPLE, MapColor.TERRACOTTA_GRAY, SoundType.CHERRY_WOOD)
    );
    public static final SignBlock JACARANDA_SIGN = registerWithoutItem("jacaranda_sign",
            properties -> new StandingSignBlock(JACARANDA_WOOD_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)
                    .mapColor(JACARANDA_LOG.defaultMapColor())
    );
    public static final WallSignBlock JACARANDA_WALL_SIGN = registerWithoutItem("jacaranda_wall_sign",
            properties -> new WallSignBlock(JACARANDA_WOOD_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)
                    .mapColor(JACARANDA_LOG.defaultMapColor())
                    .overrideDescription(JACARANDA_SIGN.getDescriptionId())
                    .overrideLootTable(JACARANDA_SIGN.getLootTable())
    );

    public static final Block JACARANDA_SHELF = register(
            "jacaranda_shelf",
            ShelfBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.SHELF)
                    .ignitedByLava()
                    .strength(2F, 3F)
    );

    public static final BlockFamily FAMILY_JACARANDA = BlockFamilies.familyBuilder(JACARANDA_PLANKS)
            .button(JACARANDA_BUTTON)
            .slab(JACARANDA_SLAB)
            .stairs(JACARANDA_STAIRS)
            .fence(JACARANDA_FENCE)
            .fenceGate(JACARANDA_FENCE_GATE)
            .pressurePlate(JACARANDA_PRESSURE_PLATE)
            .sign(JACARANDA_SIGN, JACARANDA_WALL_SIGN)
            .door(JACARANDA_DOOR)
            .trapdoor(JACARANDA_TRAPDOOR)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static final CeilingHangingSignBlock JACARANDA_HANGING_SIGN = registerWithoutItem("jacaranda_hanging_sign",
            properties -> new CeilingHangingSignBlock(JACARANDA_WOOD_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)
                    .mapColor(JACARANDA_LOG.defaultMapColor())
    );
    public static final WallHangingSignBlock JACARANDA_WALL_HANGING_SIGN = registerWithoutItem("jacaranda_wall_hanging_sign",
            properties -> new WallHangingSignBlock(JACARANDA_WOOD_TYPE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)
                    .mapColor(JACARANDA_LOG.defaultMapColor())
                    .overrideDescription(JACARANDA_HANGING_SIGN.getDescriptionId())
                    .overrideLootTable(JACARANDA_HANGING_SIGN.getLootTable())
    );

    public static final Block STRIPPED_JACARANDA_LOG = register("stripped_jacaranda_log",
            RotatedPillarBlock::new,
            Blocks.logProperties(MapColor.COLOR_PURPLE, MapColor.TERRACOTTA_GRAY, SoundType.CHERRY_WOOD)
    );
    public static final RotatedPillarBlock STRIPPED_JACARANDA_WOOD = register("stripped_jacaranda_wood",
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    .mapColor(MapColor.COLOR_PURPLE)
    );
    public static final RotatedPillarBlock JACARANDA_WOOD = register("jacaranda_wood",
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    .mapColor(MapColor.COLOR_PURPLE)
    );

	public static void registerBlockProperties() {
        var sign = (FabricBlockEntityType) BlockEntityType.SIGN;
        sign.addSupportedBlock(JACARANDA_SIGN);
        sign.addSupportedBlock(JACARANDA_WALL_SIGN);

        var hangingSign = (FabricBlockEntityType) BlockEntityType.HANGING_SIGN;
        hangingSign.addSupportedBlock(JACARANDA_HANGING_SIGN);
        hangingSign.addSupportedBlock(JACARANDA_WALL_HANGING_SIGN);

		var shelf = (FabricBlockEntityType) BlockEntityType.SHELF;
        shelf.addSupportedBlock(JACARANDA_SHELF);

		registerStrippable();
		registerComposting();
		registerFlammability();
		registerFuels();
	}

	private static void registerStrippable() {
        StrippableBlockRegistry.register(JACARANDA_LOG, STRIPPED_JACARANDA_LOG);
        StrippableBlockRegistry.register(JACARANDA_WOOD, STRIPPED_JACARANDA_WOOD);
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

        flammableBlockRegistry.add(JACARANDA_LOG, 5, 5);
        flammableBlockRegistry.add(STRIPPED_JACARANDA_LOG, 5, 5);
        flammableBlockRegistry.add(JACARANDA_WOOD, 5, 5);
        flammableBlockRegistry.add(STRIPPED_JACARANDA_WOOD, 5, 5);
        flammableBlockRegistry.add(JACARANDA_PLANKS, 5, 20);
        flammableBlockRegistry.add(JACARANDA_STAIRS, 5, 20);
        flammableBlockRegistry.add(JACARANDA_DOOR, 5, 20);
        flammableBlockRegistry.add(JACARANDA_FENCE, 5, 20);
        flammableBlockRegistry.add(JACARANDA_SLAB, 5, 20);
        flammableBlockRegistry.add(JACARANDA_FENCE_GATE, 5, 20);
        flammableBlockRegistry.add(JACARANDA_PRESSURE_PLATE, 5, 20);
        flammableBlockRegistry.add(JACARANDA_TRAPDOOR, 5, 20);
        flammableBlockRegistry.add(JACARANDA_LEAVES, 30, 60);
        flammableBlockRegistry.add(JACARANDA_BUTTON, 5, 20);
        flammableBlockRegistry.add(JACARANDA_SIGN, 5, 20);
        flammableBlockRegistry.add(JACARANDA_WALL_SIGN, 5, 20);
        flammableBlockRegistry.add(JACARANDA_HANGING_SIGN, 5, 20);
        flammableBlockRegistry.add(JACARANDA_WALL_HANGING_SIGN, 5, 20);
        flammableBlockRegistry.add(JACARANDA_SHELF, 30, 20);

    }

	private static void registerFuels() {
		FuelRegistryEvents.BUILD.register((builder, context) -> {
            
            //builder.add(BloomItems.JACARANDA_BOAT, 1200);
            //builder.add(BloomItems.JACARANDA_CHEST_BOAT, 1200);
            builder.add(JACARANDA_LOG.asItem(), 300);
            builder.add(STRIPPED_JACARANDA_LOG.asItem(), 300);
            builder.add(JACARANDA_WOOD.asItem(), 300);
            builder.add(STRIPPED_JACARANDA_WOOD.asItem(), 300);
            builder.add(JACARANDA_PLANKS.asItem(), 300);
            builder.add(JACARANDA_SLAB.asItem(), 150);
            builder.add(JACARANDA_STAIRS.asItem(), 300);
            builder.add(JACARANDA_PRESSURE_PLATE.asItem(), 300);
            builder.add(JACARANDA_BUTTON.asItem(), 100);
            builder.add(JACARANDA_TRAPDOOR.asItem(), 300);
            builder.add(JACARANDA_FENCE_GATE.asItem(), 300);
            builder.add(JACARANDA_FENCE.asItem(), 300);
            //builder.add(BloomItems.JACARANDA_SIGN, 300);
            //builder.add(BloomItems.JACARANDA_HANGING_SIGN, 800);
            builder.add(JACARANDA_SAPLING.asItem(), 100);
		});
	}

	public static void init() {
		registerBlockProperties();
	}

	private static <T extends Block> T registerWithoutItem(String path, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
		Identifier id = Bloom.id(path);
		return doRegister(id, makeBlock(block, properties, id));
	}

	private static <T extends Block> T register(String path, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
		T registered = registerWithoutItem(path, block, properties);
		registerBlockItem(registered);
		return registered;
	}

	private static <T extends Block> T doRegister(Identifier id, T block) {
		if (BuiltInRegistries.BLOCK.getOptional(id).isEmpty()) {
			return Registry.register(BuiltInRegistries.BLOCK, id, block);
		}
		throw new IllegalArgumentException("Block with id " + id + " is already in the block registry.");
	}

	private static <T extends Block> T makeBlock(Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties, Identifier id) {
		return function.apply(properties.setId(ResourceKey.create(Registries.BLOCK, id)));
	}

	private static void registerBlockItem(Block block) {
		BiFunction<Block, Item.Properties, Item> itemSupplier = BlockItem::new;
		if (block instanceof DoorBlock || block instanceof TallFlowerBlock) itemSupplier = DoubleHighBlockItem::new;
		if (block instanceof ShelfBlock) itemSupplier = (shelfBlock, properties) -> new BlockItem(shelfBlock, properties.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
		Items.registerBlock(block, itemSupplier);
	}
}