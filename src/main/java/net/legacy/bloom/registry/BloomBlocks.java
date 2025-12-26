package net.legacy.bloom.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.block.WiderFlowerBlock;
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
			properties -> new WiderFlowerBlock(MobEffects.SATURATION, 0.35F, properties),
			BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_HELLEBORE = registerWithoutItem("potted_hellebore",
			properties -> new FlowerPotBlock(HELLEBORE, properties),
			Blocks.flowerPotProperties()
	);

	public static final Block BROMELIAD = register("bromeliad",
			properties -> new WiderFlowerBlock(MobEffects.JUMP_BOOST, 5F, properties),
			BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_BROMELIAD = registerWithoutItem("potted_bromeliad",
			properties -> new FlowerPotBlock(BROMELIAD, properties),
			Blocks.flowerPotProperties()
	);

	public static final Block PINK_ORCHID = register("pink_orchid",
			properties -> new FlowerBlock(MobEffects.WEAKNESS, 7F, properties),
			BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
	);
	public static final Block POTTED_PINK_ORCHID = registerWithoutItem("potted_pink_orchid",
			properties -> new FlowerPotBlock(PINK_ORCHID, properties),
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
	}

	private static void registerFlammability() {
		final var flammableBlockRegistry = FlammableBlockRegistry.getDefaultInstance();
		flammableBlockRegistry.add(HELLEBORE, 60, 100);
		flammableBlockRegistry.add(BROMELIAD, 60, 100);
		flammableBlockRegistry.add(PINK_ORCHID, 60, 100);
		flammableBlockRegistry.add(BELLFLOWER, 60, 100);
		flammableBlockRegistry.add(HYDRANGEA, 60, 100);
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