package net.legacy.bloom.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.block.AridVegetationBlock;
import net.legacy.bloom.block.LargeFlowerBlock;
import net.legacy.bloom.block.WideFlowerBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
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

import java.util.function.BiFunction;
import java.util.function.Function;

public final class BloomBlocks {

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

	public static void registerBlockProperties() {
		var shelf = (FabricBlockEntityType) BlockEntityType.SHELF;

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