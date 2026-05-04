package net.rebel459.bloom.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.block.UntintedParticleExtendedLeavesBlock;
import net.rebel459.bloom.registry.BloomBlocks;
import net.rebel459.bloom.registry.BloomItems;
import net.rebel459.bloom.registry.BloomParticleTypes;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.rebel459.bloom.util.fabric.WoodTypeBuilder;
import net.rebel459.unified.platform.UnifiedHelpers;
import net.rebel459.unified.platform.UnifiedPlatform;
import net.rebel459.unified.platform.UnifiedRegistries;
import net.rebel459.unified.util.CreativeModeTabs;
import net.rebel459.unified.util.LoaderType;
import net.rebel459.unified.util.SuppliedBlock;
import net.rebel459.unified.util.SuppliedItem;

@SuppressWarnings("unused")
public class WoodsetRegistry {
	public static final List<WoodsetRegistry> WOODSETS = new ArrayList<>();

    private final List<SuppliedBlock> registeredBlocksList = new ArrayList<>();
    private final List<SuppliedItem> registeredItemsList = new ArrayList<>();

    private static final List<SuppliedBlock> signBlocks = new ArrayList<>();
    private static final List<SuppliedBlock> hangingSignBlocks = new ArrayList<>();


    private final Identifier name;
    private final MapColor sideColor;
    private final MapColor topColor;
    private BlockSetType blockSetType;
    private WoodType woodType;
    private SoundType leaveSounds;

    private SuppliedBlock log;
    private SuppliedBlock strippedLog;
    private SuppliedBlock wood;
    private SuppliedBlock strippedWood;
    private SuppliedBlock leaves;
    private SuppliedBlock planks;
    private SuppliedBlock stairs;
    private SuppliedBlock slab;
    private SuppliedBlock mosaic;
    private SuppliedBlock mosaicStairs;
    private SuppliedBlock mosaicSlab;
    private SuppliedBlock fence;
    private SuppliedBlock fenceGate;
    private SuppliedBlock pressurePlate;
    private SuppliedBlock button;
    private SuppliedBlock door;
    private SuppliedBlock trapDoor;
    private SuppliedBlock sign;
    private SuppliedBlock wallSign;
    private SuppliedBlock hangingSign;
    private SuppliedBlock wallHangingSign;
    private SuppliedBlock shelf;

    private SuppliedItem signItem;
    private SuppliedItem hangingSignItem;
    private SuppliedItem boatItem;
    private SuppliedItem chestBoatItem;

    private Supplier<EntityType<Boat>> boat;
    private Supplier<EntityType<ChestBoat>> chestBoat;

    private BlockFamily.Builder blockFamily;
    private final Settings woodsetSettings;

    private void registerWood() {
        blockSetType = createBlockSetType();
        woodType = WoodTypeBuilder.copyOf(woodsetSettings.woodPreset.woodType).register(this.getNameID(), getBlockSetType());

        planks = createPlanks();

        log = createLog();
        strippedLog = createStrippedLog();
		if (this.notBambooVariant()) {
			wood = createWood();
			strippedWood = createStrippedWood();
		}

        if (woodsetSettings.hasMosaic()){
            mosaic = createMosaic();
            mosaicStairs = createMosaicStairs();
            mosaicSlab = createMosaicSlab();
        }
        if (this.isOverworldTreeWood()){
            leaves = createLeaves();
        }
        stairs = createStairs();
        slab = createSlab();
        fence = createFence();
        fenceGate = createFenceGate();
        pressurePlate = createPressurePlate();
        button = createButton();
        door = createDoor();
        trapDoor = createTrapDoor();
        sign = createSign();
        wallSign = createWallSign();
        hangingSign = createHangingSign();
        wallHangingSign = createWallHangingSign();
        shelf = createShelf();

        signItem = createSignItem();
        hangingSignItem = createHangingSignItem();

        if (woodsetSettings.hasBoats){
            boat = createBoatEntity();
            chestBoat = createChestBoatEntity();
            boatItem = createBoatItem();
            chestBoatItem = createChestBoatItem();
        }

		if (UnifiedPlatform.getLoader() == LoaderType.FABRIC) {
			blockFamily = new BlockFamily.Builder(planks.get()).recipeGroupPrefix("wooden").recipeUnlockedBy(hasPlanks());
			blockFamily.stairs(stairs.get());
			blockFamily.slab(slab.get());
			if (woodsetSettings.hasMosaic()) {
				blockFamily.customFence(fence.get());
				blockFamily.customFenceGate(fenceGate.get());
			} else {
				blockFamily.fence(fence.get());
				blockFamily.fenceGate(fenceGate.get());
			}
			blockFamily.door(door.get());
			blockFamily.trapdoor(trapDoor.get());
			blockFamily.sign(sign.get(), wallSign.get());
			blockFamily.button(button.get());
			blockFamily.pressurePlate(pressurePlate.get());
		}

        signBlocks.add(sign);
        signBlocks.add(wallSign);

        hangingSignBlocks.add(hangingSign);
        hangingSignBlocks.add(wallHangingSign);
    }


    public WoodsetRegistry(Identifier name, MapColor sideColor, MapColor topColor, Settings.Builder settings){
        this.woodsetSettings = settings.build();
        this.name = name;
        this.sideColor = sideColor;
        this.topColor = topColor;
        registerWood();
        WOODSETS.add(this);
    }

    public WoodsetRegistry(Identifier name, MapColor sideColor, MapColor topColor){
        this(name, sideColor, topColor, new Settings.Builder());
    }
    private ResourceKey<Item> itemKey(String id) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(this.getNamespace(), id));
    }
    private ResourceKey<Block> blockKey(String id) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(this.getNamespace(), id));
    }
    private SuppliedBlock createBlockWithItem(String blockID, Supplier<BlockBehaviour.Properties> settings){
        return createBlockWithItem(blockID, Block::new, settings);
    }
	private SuppliedBlock createBlockWithItem(String blockID, Function<BlockBehaviour.Properties, Block> factory, Supplier<BlockBehaviour.Properties> settings){
		SuppliedBlock block = BloomBlocks.register(blockID, factory, settings);
		BloomBlocks.checkDatagen(block);
		registeredBlocksList.add(block);
		return block;
	}
	private SuppliedBlock createBlockWithItem(String blockID, Function<BlockBehaviour.Properties, Block> factory, Supplier<BlockBehaviour.Properties> settings, BlockEntityType<?> blockEntity){
		SuppliedBlock block = BloomBlocks.register(blockID, factory, settings, blockEntity);
		BloomBlocks.checkDatagen(block);
		registeredBlocksList.add(block);
		return block;
	}
	private SuppliedBlock createBlockWithoutItem(String blockID, Function<BlockBehaviour.Properties, Block> factory, Supplier<BlockBehaviour.Properties> settings){
		SuppliedBlock block = BloomBlocks.registerWithoutItem(blockID, factory, settings);
		BloomBlocks.checkDatagen(block);
		registeredBlocksList.add(block);
		return block;
	}
	private SuppliedBlock createBlockWithoutItem(String blockID, Function<BlockBehaviour.Properties, Block> factory, Supplier<BlockBehaviour.Properties> settings, BlockEntityType<?> blockEntity){
		SuppliedBlock block = BloomBlocks.registerWithoutItem(blockID, factory, settings, blockEntity);
		BloomBlocks.checkDatagen(block);
		registeredBlocksList.add(block);
		return block;
	}
    public SuppliedItem createItem(String blockID, Function<Item.Properties, Item> factory, Supplier<Item.Properties> settings){
		SuppliedItem item = BloomItems.register(blockID, factory, settings);
		BloomItems.checkDatagen(item);
        registeredItemsList.add(item);
        return item;
    }
	public SuppliedItem createBlockItem(String blockID, Supplier<Block> block, Supplier<Item.Properties> settings){
		SuppliedItem item = BloomItems.registerBlockItem(blockID, block, settings);
		BloomItems.checkDatagen(item);
		registeredItemsList.add(item);
		return item;
	}
    private ResourceKey<EntityType<?>> entityKey(String id) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(this.getNamespace(), id));
    }

	private static UnifiedRegistries.EntityTypes ENTITIES = UnifiedRegistries.EntityTypes.create(Bloom.MOD_ID);

	public <T extends Entity> Supplier<EntityType<T>> register(String name, EntityType.Builder<T> type){
		return ENTITIES.register(name, type);
	}

    private Supplier<BlockBehaviour.Properties> createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return () -> BlockBehaviour.Properties.of().mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sound(this.getWoodType().soundType());
    }

    public Settings getWoodsetSettings() {
        return woodsetSettings;
    }

    public static List<WoodType> getAllWoodTypes(){
        if (WOODSETS.isEmpty()){
            return null;
        }
        List<WoodType> types = new ArrayList<>();
        for (WoodsetRegistry set : WOODSETS){
            types.add(set.getWoodType());
        }
        return types;
    }
    public Identifier getNameID() {
        return name;
    }
    public String getName() {
        return name.getPath();
    }
    public String getNamespace() {
        return name.getNamespace();
    }

    public BlockSetType getBlockSetType() {
        return blockSetType;
    }

    public WoodPreset getWoodPreset() {
        return woodsetSettings.woodPreset;
    }

    public MapColor getSideColor() {
        return sideColor;
    }

    public MapColor getTopColor() {
        return topColor;
    }

    public WoodType getWoodType() {
        return woodType;
    }

    public SuppliedBlock getButton() {
        return button;
    }

    public SuppliedBlock getFence() {
        return fence;
    }

    public SuppliedBlock getPlanks() {
        return planks;
    }

    public SuppliedBlock getSlab() {
        return slab;
    }

    public SuppliedBlock getFenceGate() {
        return fenceGate;
    }

    public SuppliedBlock getStairs() {
        return stairs;
    }

    public SuppliedBlock getDoor() {
        return door;
    }

    public SuppliedBlock getHangingSign() {
        return hangingSign;
    }

    public SuppliedBlock getWallHangingSign() {
        return wallHangingSign;
    }

    public SuppliedBlock getPressurePlate() {
        return pressurePlate;
    }

    public SuppliedBlock getSign() {
        return sign;
    }

    public SuppliedBlock getTrapDoor() {
        return trapDoor;
    }

    public SuppliedBlock getWallSign() {
        return wallSign;
    }

    public SuppliedItem getHangingSignItem() {
        return hangingSignItem;
    }

    public SuppliedItem getSignItem() {
        return signItem;
    }

    public SuppliedBlock getLog() {
        return log;
    }

    public SuppliedBlock getStrippedLog() {
        return strippedLog;
    }

    public SuppliedBlock getWood() {
        return wood;
    }

    public SuppliedBlock getStrippedWood() {
        return strippedWood;
    }

    public SuppliedBlock getMosaic() {
        return mosaic;
    }

    public SuppliedBlock getMosaicStairs() {
        return mosaicStairs;
    }

    public SuppliedBlock getMosaicSlab() {
        return mosaicSlab;
    }

    public SuppliedBlock getLeaves() {
        return leaves;
    }

    public Supplier<EntityType<Boat>> getBoat() {
        return boat;
    }

    public Supplier<EntityType<ChestBoat>> getChestBoat() {
        return chestBoat;
    }

    public SuppliedItem getBoatItem() {
        return boatItem;
    }

    public SuppliedItem getChestBoatItem() {
        return chestBoatItem;
    }

    public List<SuppliedBlock> getRegisteredBlocksList() {
        return registeredBlocksList;
    }

    public List<SuppliedItem> getRegisteredItemsList() {
        return registeredItemsList;
    }

    public static List<SuppliedBlock> getAllSigns(){
        return signBlocks;
    }
    public static List<SuppliedBlock> getAllHangingSigns(){
        return hangingSignBlocks;
    }

    public SuppliedBlock getShelf() {
        return shelf;
    }

    public BlockFamily getBlockFamily() {
        return blockFamily.getFamily();
    }
    private SuppliedBlock createLog() {
        return createBlockWithItem(this.getName() + "_" + woodsetSettings.getLogName(), RotatedPillarBlock::new, createLogBlock(this.getSideColor(), this.getTopColor()));
    }
    private SuppliedBlock createStrippedLog() {
        return createBlockWithItem("stripped_" + this.getName() + "_" + woodsetSettings.getLogName(), RotatedPillarBlock::new, createLogBlock(this.getSideColor(), this.getTopColor()));
    }
    private SuppliedBlock createWood() {
        return createBlockWithItem(this.getName() + "_" +woodsetSettings.getWoodName(), RotatedPillarBlock::new, createLogBlock(this.getSideColor(), this.getSideColor()));
    }
    private SuppliedBlock createStrippedWood() {
        return createBlockWithItem("stripped_" + this.getName() + "_" +woodsetSettings.getWoodName(), RotatedPillarBlock::new, createLogBlock(this.getTopColor(), this.getTopColor()));
    }
    private SuppliedBlock createLeaves() {
		if (Objects.equals(getName(), "jacaranda")) return createBlockWithItem(this.getName() + "_leaves", settings -> new UntintedParticleLeavesBlock(0.1F, BloomParticleTypes.JACARANDA_LEAVES.get(), settings), createLeavesBlock());
		else if (Objects.equals(getName(), "pine")) return createBlockWithItem(this.getName() + "_leaves", settings -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 5468745), settings), createLeavesBlock());
		else if (Objects.equals(getName(), "golden_birch")) return createBlockWithItem(this.getName() + "_leaves", settings -> new UntintedParticleExtendedLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 13807429), settings), createLeavesBlock());
		else return createBlockWithItem(this.getName() + "_leaves", settings -> new TintedParticleLeavesBlock(0.01F, settings), createLeavesBlock());
    }
    private SuppliedBlock createPlanks(){
        return createBlockWithItem(this.getName() + "_planks", () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createStairs(){
        return createBlockWithItem(this.getName() + "_stairs", settings -> new StairBlock(getBase().defaultBlockState(), settings), () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createSlab(){
        return createBlockWithItem(this.getName() + "_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createMosaic(){
        return createBlockWithItem(this.getName() + "_mosaic", () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createMosaicStairs(){
        return createBlockWithItem(this.getName() + "_mosaic_stairs", settings -> new StairBlock(getBase().defaultBlockState(), settings), () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createMosaicSlab(){
        return createBlockWithItem(this.getName() + "_mosaic_slab", SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createFence(){
        return createBlockWithItem(this.getName() + "_fence", FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createFenceGate(){
        return createBlockWithItem(this.getName() + "_fence_gate", settings -> new FenceGateBlock(this.getWoodType(), settings), () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createPressurePlate(){
        return createBlockWithItem(this.getName() + "_pressure_plate", settings -> new PressurePlateBlock(this.getBlockSetType(), settings), () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createButton(){
        return createBlockWithItem(this.getName() + "_button", settings -> new ButtonBlock(this.getBlockSetType(), 30, settings), () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createDoor(){
        return createBlockWithItem(this.getName() + "_door", settings -> new DoorBlock(this.getBlockSetType(), settings), () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createTrapDoor(){
        return createBlockWithItem(this.getName() + "_trapdoor", settings -> new TrapDoorBlock(this.getBlockSetType(), settings), () -> BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private SuppliedBlock createSign(){
        return createBlockWithoutItem(this.getName() + "_sign", settings -> new StandingSignBlock(
                        this.woodType, settings),
			() -> BlockBehaviour.Properties.ofFullCopy(getSignBase()).mapColor(this.getTopColor()),
			BlockEntityType.SIGN
		);
    }
    private SuppliedBlock createWallSign(){
        return createBlockWithoutItem(this.getName() + "_wall_sign", settings -> new WallSignBlock(
                        this.woodType, settings),
			() -> BlockBehaviour.Properties.ofFullCopy(getSignBase()).mapColor(this.getTopColor()).overrideLootTable(sign.get().getLootTable()),
			BlockEntityType.SIGN
		);
    }

    private SuppliedBlock createHangingSign(){
        return createBlockWithoutItem(this.getName() + "_hanging_sign", settings -> new CeilingHangingSignBlock(
                        this.woodType, settings),
			() -> BlockBehaviour.Properties.ofFullCopy(getHangingSignBase()).mapColor(this.getTopColor()),
			BlockEntityType.HANGING_SIGN
		);
    }
    private SuppliedBlock createWallHangingSign(){
        return createBlockWithoutItem(this.getName() + "_wall_hanging_sign", settings -> new WallHangingSignBlock(
                        this.woodType, settings),
			() -> BlockBehaviour.Properties.ofFullCopy(getHangingSignBase()).mapColor(this.getTopColor()).overrideLootTable(hangingSign.get().getLootTable()),
			BlockEntityType.HANGING_SIGN
		);
    }

    private SuppliedBlock createShelf(){
        return createBlockWithItem(this.getName() + "_shelf", ShelfBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SHELF).mapColor(topColor), BlockEntityType.SHELF);
    }

    private SuppliedItem createSignItem(){
        return createItem(this.getName() + "_sign", settings -> new SignItem(this.getSign().get(), this.getWallSign().get(), settings), () -> new Item.Properties().stacksTo(16));
    }
    private SuppliedItem createHangingSignItem(){
        return createItem(this.getName() + "_hanging_sign", settings -> new HangingSignItem(this.getHangingSign().get(), this.getWallHangingSign().get(), settings), () -> new Item.Properties().stacksTo(16));
    }

    private Supplier<EntityType<Boat>> createBoatEntity(){
        return register(this.getName() + "_" + woodsetSettings.getBoatName(), EntityType.Builder.of(EntityType.boatFactory(boatItem), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    }
    private Supplier<EntityType<ChestBoat>> createChestBoatEntity(){
        return register(this.getName() + "_chest_" + woodsetSettings.getBoatName(), EntityType.Builder.of(EntityType.chestBoatFactory(chestBoatItem), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    }
    private SuppliedItem createBoatItem(){
        return createItem(this.getName() + "_" + woodsetSettings.getBoatName(), settings -> new BoatItem(boat.get(), settings), () -> new Item.Properties().stacksTo(1));
    }
    private SuppliedItem createChestBoatItem(){
        return createItem(this.getName() + "_chest_" + woodsetSettings.getBoatName(), settings -> new BoatItem(chestBoat.get(), settings), () -> new Item.Properties().stacksTo(1));
    }

    private Block getBase(){
        switch (getWoodPreset()){
            case NETHER -> {
                return Blocks.CRIMSON_PLANKS;
            }
            case BAMBOO -> {
                return Blocks.BAMBOO_PLANKS;
            }
            case FANCY -> {
                return Blocks.CHERRY_PLANKS;
            }
            default -> {
                return Blocks.OAK_PLANKS;
            }
        }
    }
    private Block getSignBase(){
        switch (getWoodPreset()){
            case NETHER -> {
                return Blocks.CRIMSON_SIGN;
            }
            case BAMBOO -> {
                return Blocks.BAMBOO_SIGN;
            }
            case FANCY -> {
                return Blocks.CHERRY_SIGN;
            }
            default -> {
                return Blocks.OAK_SIGN;
            }
        }
    }
    private Block getHangingSignBase(){
        switch (getWoodPreset()){
            case NETHER -> {
                return Blocks.CRIMSON_HANGING_SIGN;
            }
            case BAMBOO -> {
                return Blocks.BAMBOO_HANGING_SIGN;
            }
            case FANCY -> {
                return Blocks.CHERRY_HANGING_SIGN;
            }
            default -> {
                return Blocks.OAK_HANGING_SIGN;
            }
        }
    }

    private BlockSetType createBlockSetType(){
        return getWoodPreset().blockSetType();
    }

    public boolean isOverworldTreeWood(){
        return this.getWoodPreset().isOverworldTree();
    }
    public boolean notBambooVariant(){
        return this.getWoodPreset() != WoodPreset.BAMBOO;
    }

    public Supplier<BlockBehaviour.Properties> createLeavesBlock() {
        return createLeavesBlock(MapColor.COLOR_GREEN);
    }
    public Supplier<BlockBehaviour.Properties> createLeavesBlock(MapColor color) {
        return () -> BlockBehaviour.Properties.of().mapColor(color).strength(0.2F).randomTicks().sound(woodsetSettings.leaveSoundGroup).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating((_, _, _) -> false).isViewBlocking((_, _, _) -> false).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor((_, _, _) -> false);
    }



	public static void addToBuildingTab(Item preceedingItem, WoodsetRegistry woodset){
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.BUILDING_BLOCKS,
			preceedingItem,
			woodset.getPlanks(),
			woodset.getStairs(),
			woodset.getSlab(),
			woodset.getFence(),
			woodset.getFenceGate(),
			woodset.getDoor(),
			woodset.getTrapDoor(),
			woodset.getPressurePlate(),
			woodset.getButton()
		);
		if (woodset.notBambooVariant()){
			UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.BUILDING_BLOCKS, preceedingItem, woodset.getWood(), woodset.getStrippedWood());
		}
		if (woodset.getWoodsetSettings().hasMosaic()){
			UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.BUILDING_BLOCKS, preceedingItem, woodset.getMosaic(), woodset.getMosaicStairs(), woodset.getMosaicSlab());
		}
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.BUILDING_BLOCKS, preceedingItem, woodset.getLog(), woodset.getStrippedLog());
	}

	public static void addToNaturalTab(Item preceedingItem, WoodsetRegistry woodset, SuppliedBlock sapling){
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.NATURAL_BLOCKS, preceedingItem, woodset.getLeaves(), sapling.asItem());
	}

	public static void addToFunctionalTab(Item preceedingItem, WoodsetRegistry woodset){
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.FUNCTIONAL_BLOCKS, preceedingItem, woodset.shelf, woodset.signItem, woodset.hangingSignItem);
	}

	public static void addToUtilitiesTab(Item preceedingItem, WoodsetRegistry woodset){
		UnifiedHelpers.CREATIVE_ENTRIES.insertAfter(CreativeModeTabs.TOOLS_AND_UTILITIES, preceedingItem, woodset.boatItem, woodset.chestBoatItem);
	}

    public String hasPlanks(){
        return RecipeProvider.getHasName(getPlanks().get());
    }

    public enum WoodPreset {
        DEFAULT(WoodType.OAK),
        FANCY(WoodType.CHERRY),
        NETHER(WoodType.CRIMSON),
        BAMBOO(WoodType.BAMBOO);

        private final WoodType woodType;

        WoodPreset(WoodType type){
            this.woodType = type;
        }

        public WoodType getWoodType() {
            return woodType;
        }

        boolean isOverworldTree(){
            return this == DEFAULT || this == FANCY;
        }
        public BlockSetType blockSetType(){
            return woodType.setType();
        }
    }
    public static class Settings{
        public enum BoatType {BOAT, RAFT}
        private String logName = null;
        private String woodName = null;
        private BoatType boatType = null;

        private boolean hasBoats = true;
        private boolean hasMosaic = false;
        private SoundType leaveSoundGroup = SoundType.GRASS;
        private WoodPreset woodPreset = WoodPreset.DEFAULT;

        private Settings() {
        }

        public BoatType getBoatType() {
            return boatType;
        }

        public SoundType getLeaveSoundGroup() {
            return leaveSoundGroup;
        }

        public String getLogName() {
            return logName;
        }

        public String getWoodName() {
            return woodName;
        }

        public boolean hasMosaic() {
            return hasMosaic;
        }

        public boolean hasBoats() {
            return hasBoats;
        }

        public WoodPreset getWoodPreset() {
            return woodPreset;
        }

        private String getBoatName(){
            return this.getBoatType() == BoatType.RAFT ? "raft" : "boat";
        }


        public static class Builder {
            private final Settings settings = new Settings();
            public Settings build(){
                settings.boatType = getBoatType();
                settings.woodName = getWoodName();
                settings.logName = getLogName();
                return settings;
            }

            public Builder woodName(String woodName) {
                settings.woodName = woodName;
                return this;
            }

            public Builder woodName(boolean hasBoats) {
                settings.hasBoats = hasBoats;
                return this;
            }

            public Builder logName(String logName) {
                settings.logName = logName;
                return this;
            }

            public Builder setBoatType(BoatType type) {
                settings.boatType = type;
                return this;
            }

            public Builder woodPreset(WoodPreset woodPreset) {
                settings.woodPreset = woodPreset;
                return this;
            }

            public Builder leaveSoundGroup(SoundType soundGroup){
                settings.leaveSoundGroup = soundGroup;
                return this;
            }

            public Builder hasMosaic(){
                settings.hasMosaic = true;
                return this;
            }

            private String getLogName(){
                if (settings.logName != null){
                    return settings.logName;
                }
                switch (settings.woodPreset){
                    case NETHER -> {
                        return "stem";
                    }
                    case BAMBOO -> {
                        return "block";
                    }
                    default -> {
                        return "log";
                    }
                }
            }
            public BoatType getBoatType() {
                return Objects.requireNonNullElseGet(settings.boatType, () -> settings.woodPreset == WoodPreset.BAMBOO ? BoatType.RAFT : BoatType.BOAT);
            }

            private String getWoodName(){
                return Objects.requireNonNullElseGet(settings.woodName, () -> settings.woodPreset == WoodPreset.NETHER ? "hyphae" : "wood");
            }
        }
    }
}
