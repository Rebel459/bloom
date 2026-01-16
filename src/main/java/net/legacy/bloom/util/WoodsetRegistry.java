package net.legacy.bloom.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.legacy.bloom.registry.BloomBlocks;
import net.legacy.bloom.registry.BloomItems;
import net.legacy.bloom.registry.BloomParticleTypes;
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
import net.minecraft.world.item.CreativeModeTabs;
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

@SuppressWarnings("unused")
public class WoodsetRegistry {
	public static final List<WoodsetRegistry> WOODSETS = new ArrayList<>();

    private final List<Block> registeredBlocksList = new ArrayList<>();
    private final List<Item> registeredItemsList = new ArrayList<>();

    private static final List<Block> signBlocks = new ArrayList<>();
    private static final List<Block> hangingSignBlocks = new ArrayList<>();


    private final Identifier name;
    private final MapColor sideColor;
    private final MapColor topColor;
    private BlockSetType blockSetType;
    private WoodType woodType;
    private SoundType leaveSounds;

    private Block log;
    private Block strippedLog;
    private Block wood;
    private Block strippedWood;
    private Block leaves;
    private Block planks;
    private Block stairs;
    private Block slab;
    private Block mosaic;
    private Block mosaicStairs;
    private Block mosaicSlab;
    private Block fence;
    private Block fenceGate;
    private Block pressurePlate;
    private Block button;
    private Block door;
    private Block trapDoor;
    private Block sign;
    private Block wallSign;
    private Block hangingSign;
    private Block wallHangingSign;
    private Block shelf;

    private Item signItem;
    private Item hangingSignItem;
    private Item boatItem;
    private Item chestBoatItem;

    private EntityType<Boat> boat;
    private EntityType<ChestBoat> chestBoat;

    private BlockFamily.Builder blockFamily;
    private final Settings woodsetSettings;

    private void registerWood(){
        blockSetType = createBlockSetType();
        woodType = WoodTypeBuilder.copyOf(woodsetSettings.woodPreset.woodType).register(this.getNameID(), getBlockSetType());

        planks = createPlanks();

        log = createLog();
        strippedLog = createStrippedLog();
        StrippableBlockRegistry.register(log, strippedLog);

        if (this.getWoodPreset() != WoodPreset.BAMBOO){
            wood = createWood();
            strippedWood = createStrippedWood();
            StrippableBlockRegistry.register(wood, strippedWood);
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

        blockFamily = new BlockFamily.Builder(planks).recipeGroupPrefix("wooden").recipeUnlockedBy(hasPlanks());
        blockFamily.stairs(stairs);
        blockFamily.slab(slab);
        if (woodsetSettings.hasMosaic()){
            blockFamily.customFence(fence);
            blockFamily.customFenceGate(fenceGate);
        }else{
            blockFamily.fence(fence);
            blockFamily.fenceGate(fenceGate);
        }
        blockFamily.door(door);
        blockFamily.trapdoor(trapDoor);
        blockFamily.sign(sign, wallSign);
        blockFamily.button(button);
        blockFamily.pressurePlate(pressurePlate);

        signBlocks.add(sign);
        signBlocks.add(wallSign);

        hangingSignBlocks.add(hangingSign);
        hangingSignBlocks.add(wallHangingSign);

        var signs = (FabricBlockEntityType) BlockEntityType.SIGN;
        signs.addSupportedBlock(sign);
        signs.addSupportedBlock(wallSign);

        var hangingSigns = (FabricBlockEntityType) BlockEntityType.HANGING_SIGN;
        hangingSigns.addSupportedBlock(hangingSign);
        hangingSigns.addSupportedBlock(wallHangingSign);

        var shelves = (FabricBlockEntityType) BlockEntityType.SHELF;
        shelves.addSupportedBlock(shelf);

        if (woodsetSettings.woodPreset != WoodPreset.NETHER){
            registerWoodsetFlammables();
        }
        registerFuels();
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
    private Block createBlockWithItem(String blockID, BlockBehaviour.Properties settings){
        return createBlockWithItem(blockID, Block::new, settings);
    }
    private Block createBlockWithItem(String blockID, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings){
        Block block = Blocks.register(blockKey(blockID), factory, settings);
		BloomBlocks.TRANSLATABLE_BLOCKS.add(block);
        registeredBlocksList.add(block);
        Items.registerBlock(block);
        return block;
    }
    private Block createBlockWithoutItem(String blockID, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings){
        Block block = Blocks.register(blockKey(blockID), factory, settings);
		BloomBlocks.TRANSLATABLE_BLOCKS.add(block);
        registeredBlocksList.add(block);
        return block;
    }
    public Item createItem(String blockID, Function<Item.Properties, Item> factory, Item.Properties settings){
        Item item = Items.registerItem(itemKey(blockID), factory, settings);
		BloomItems.TRANSLATABLE_ITEMS.add(item);
        registeredItemsList.add(item);
        return item;
    }
    private ResourceKey<EntityType<?>> entityKey(String id) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(this.getNamespace(), id));
    }
    public <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type){
        return register(entityKey(name), type);
    }
	public <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> name, EntityType.Builder<T> type){
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, name, type.build(name));
	}
    private static EntityType.EntityFactory<Boat> boatFactory(Item item) {
        return (entityType, world) -> new Boat(entityType, world, () -> item);
    }
    private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(Item item) {
        return (entityType, world) -> new ChestBoat(entityType, world, () -> item);
    }

    private BlockBehaviour.Properties createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return BlockBehaviour.Properties.of().mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sound(this.getWoodType().soundType());
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

    public Block getButton() {
        return button;
    }

    public Block getFence() {
        return fence;
    }

    public Block getPlanks() {
        return planks;
    }

    public Block getSlab() {
        return slab;
    }

    public Block getFenceGate() {
        return fenceGate;
    }

    public Block getStairs() {
        return stairs;
    }

    public Block getDoor() {
        return door;
    }

    public Block getHangingSign() {
        return hangingSign;
    }

    public Block getWallHangingSign() {
        return wallHangingSign;
    }

    public Block getPressurePlate() {
        return pressurePlate;
    }

    public Block getSign() {
        return sign;
    }

    public Block getTrapDoor() {
        return trapDoor;
    }

    public Block getWallSign() {
        return wallSign;
    }

    public Item getHangingSignItem() {
        return hangingSignItem;
    }

    public Item getSignItem() {
        return signItem;
    }

    public Block getLog() {
        return log;
    }

    public Block getStrippedLog() {
        return strippedLog;
    }

    public Block getWood() {
        return wood;
    }

    public Block getStrippedWood() {
        return strippedWood;
    }

    public Block getMosaic() {
        return mosaic;
    }

    public Block getMosaicStairs() {
        return mosaicStairs;
    }

    public Block getMosaicSlab() {
        return mosaicSlab;
    }

    public Block getLeaves() {
        return leaves;
    }

    public EntityType<Boat> getBoat() {
        return boat;
    }

    public EntityType<ChestBoat> getChestBoat() {
        return chestBoat;
    }

    public Item getBoatItem() {
        return boatItem;
    }

    public Item getChestBoatItem() {
        return chestBoatItem;
    }

    public List<Block> getRegisteredBlocksList() {
        return registeredBlocksList;
    }

    public List<Item> getRegisteredItemsList() {
        return registeredItemsList;
    }

    public static List<Block> getAllSigns(){
        return signBlocks;
    }
    public static List<Block> getAllHangingSigns(){
        return hangingSignBlocks;
    }

    public Block getShelf() {
        return shelf;
    }

    public BlockFamily getBlockFamily() {
        return blockFamily.getFamily();
    }
    private Block createLog() {
        return createBlockWithItem(this.getName() + "_" + woodsetSettings.getLogName(), RotatedPillarBlock::new, createLogBlock(this.getSideColor(), this.getTopColor()));
    }
    private Block createStrippedLog() {
        return createBlockWithItem("stripped_" + this.getName() + "_" + woodsetSettings.getLogName(), RotatedPillarBlock::new, createLogBlock(this.getSideColor(), this.getTopColor()));
    }
    private Block createWood() {
        return createBlockWithItem(this.getName() + "_" +woodsetSettings.getWoodName(), RotatedPillarBlock::new, createLogBlock(this.getSideColor(), this.getSideColor()));
    }
    private Block createStrippedWood() {
        return createBlockWithItem("stripped_" + this.getName() + "_" +woodsetSettings.getWoodName(), RotatedPillarBlock::new, createLogBlock(this.getTopColor(), this.getTopColor()));
    }
    private Block createLeaves() {
		if (Objects.equals(getName(), "jacaranda")) return createBlockWithItem(this.getName() + "_leaves", settings -> new UntintedParticleLeavesBlock(0.1F, BloomParticleTypes.JACARANDA_LEAVES, settings), createLeavesBlock());
		else if (Objects.equals(getName(), "pine")) return createBlockWithItem(this.getName() + "_leaves", settings -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 7639389), settings), createLeavesBlock());
		else if (Objects.equals(getName(), "golden_birch")) return createBlockWithItem(this.getName() + "_leaves", settings -> new UntintedParticleLeavesBlock(0.01F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, 13807429), settings), createLeavesBlock());
		else return createBlockWithItem(this.getName() + "_leaves", settings -> new TintedParticleLeavesBlock(0.01F, settings), createLeavesBlock());
    }
    private Block createPlanks(){
        return createBlockWithItem(this.getName() + "_planks", BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createStairs(){
        return createBlockWithItem(this.getName() + "_stairs", settings -> new StairBlock(getBase().defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createSlab(){
        return createBlockWithItem(this.getName() + "_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createMosaic(){
        return createBlockWithItem(this.getName() + "_mosaic", BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createMosaicStairs(){
        return createBlockWithItem(this.getName() + "_mosaic_stairs", settings -> new StairBlock(getBase().defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createMosaicSlab(){
        return createBlockWithItem(this.getName() + "_mosaic_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createFence(){
        return createBlockWithItem(this.getName() + "_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createFenceGate(){
        return createBlockWithItem(this.getName() + "_fence_gate", settings -> new FenceGateBlock(this.getWoodType(), settings), BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createPressurePlate(){
        return createBlockWithItem(this.getName() + "_pressure_plate", settings -> new PressurePlateBlock(this.getBlockSetType(), settings), BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createButton(){
        return createBlockWithItem(this.getName() + "_button", settings -> new ButtonBlock(this.getBlockSetType(), 30, settings), BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createDoor(){
        return createBlockWithItem(this.getName() + "_door", settings -> new DoorBlock(this.getBlockSetType(), settings), BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createTrapDoor(){
        return createBlockWithItem(this.getName() + "_trapdoor", settings -> new TrapDoorBlock(this.getBlockSetType(), settings), BlockBehaviour.Properties.ofFullCopy(getBase()).sound(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createSign(){
        return createBlockWithoutItem(this.getName() + "_sign", settings -> new StandingSignBlock(
                        this.woodType, settings),
                BlockBehaviour.Properties.ofFullCopy(getSignBase()).mapColor(this.getTopColor()));
    }
    private Block createWallSign(){
        return createBlockWithoutItem(this.getName() + "_wall_sign", settings -> new WallSignBlock(
                        this.woodType, settings),
                BlockBehaviour.Properties.ofFullCopy(getSignBase()).mapColor(this.getTopColor()).overrideLootTable(sign.getLootTable()));
    }

    private Block createHangingSign(){
        return createBlockWithoutItem(this.getName() + "_hanging_sign", settings -> new CeilingHangingSignBlock(
                        this.woodType, settings),
                BlockBehaviour.Properties.ofFullCopy(getHangingSignBase()).mapColor(this.getTopColor()));
    }
    private Block createWallHangingSign(){
        return createBlockWithoutItem(this.getName() + "_wall_hanging_sign", settings -> new WallHangingSignBlock(
                        this.woodType, settings),
                BlockBehaviour.Properties.ofFullCopy(getHangingSignBase()).mapColor(this.getTopColor()).overrideLootTable(hangingSign.getLootTable()));
    }

    private Block createShelf(){
        return createBlockWithItem(this.getName() + "_shelf", ShelfBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SHELF).mapColor(topColor));
    }

    private Item createSignItem(){
        return createItem(this.getName() + "_sign", settings -> new SignItem(this.getSign(), this.getWallSign(), settings), new Item.Properties().stacksTo(16));
    }
    private Item createHangingSignItem(){
        return createItem(this.getName() + "_hanging_sign", settings -> new HangingSignItem(this.getHangingSign(), this.getWallHangingSign(), settings), new Item.Properties().stacksTo(16));
    }

    private EntityType<Boat> createBoatEntity(){
        return register(this.getName() + "_" + woodsetSettings.getBoatName(), EntityType.Builder.of(boatFactory(boatItem), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    }
    private EntityType<ChestBoat> createChestBoatEntity(){
        return register(this.getName() + "_chest_" + woodsetSettings.getBoatName(), EntityType.Builder.of(chestBoatFactory(chestBoatItem), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    }
    private Item createBoatItem(){
        return createItem(this.getName() + "_" + woodsetSettings.getBoatName(), settings -> new BoatItem(boat, settings), new Item.Properties().stacksTo(1));
    }
    private Item createChestBoatItem(){
        return createItem(this.getName() + "_chest_" + woodsetSettings.getBoatName(), settings -> new BoatItem(chestBoat, settings), new Item.Properties().stacksTo(1));
    }

    public void registerFuels(){
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(log, 300);
            builder.add(strippedLog, 300);
            if (woodsetSettings.woodPreset == WoodPreset.BAMBOO){
                builder.add(mosaic, 300);
                builder.add(mosaicSlab, 150);
                builder.add(mosaicStairs, 300);
            }
            else{
                builder.add(wood, 300);
                builder.add(strippedWood, 300);
            }
            builder.add(pressurePlate, 300);
            builder.add(button, 100);
            builder.add(trapDoor, 300);
            builder.add(door, 300);
            builder.add(fence, 300);
            builder.add(fenceGate, 300);
            builder.add(signItem, 300);
            builder.add(hangingSignItem, 800);
			builder.add(shelf, 300);

            if (woodsetSettings.hasBoats()){
                builder.add(boatItem, 1200);
                builder.add(chestBoatItem, 1200);
            }
        });
    }
    public void registerWoodsetFlammables(){
        addFlammable(getLog(), 5, 5);
        addFlammable(getStrippedLog(), 5, 5);

        if (getWoodPreset() != WoodPreset.BAMBOO){
            addFlammable(getWood(), 5, 5);
            addFlammable(getStrippedWood(), 5, 5);
        }
        if (woodsetSettings.hasMosaic()){
            addFlammable(getMosaic(), 5, 20);
            addFlammable(getMosaicStairs(), 5, 20);
            addFlammable(getMosaicSlab(), 5, 20);
        }
        if (isOverworldTreeWood()){
            addFlammable(getLeaves(), 30, 60);
        }

        addFlammable(getPlanks(), 5, 20);
        addFlammable(getSlab(), 5, 20);
        addFlammable(getStairs(), 5, 20);
        addFlammable(getFence(), 5, 20);
        addFlammable(getFenceGate(), 5, 20);

        addFlammable(getSign(), 5, 20);
        addFlammable(getWallSign(), 5, 20);

        addFlammable(getHangingSign(), 5, 20);
        addFlammable(getWallHangingSign(), 5, 20);
		addFlammable(getShelf(), 30, 20);
    }
    public static void addFlammable(Block block, int burn, int spread){
        FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread);
    }

    public void fullWoodset(BlockModelGenerators generator){
		BlockModelGenerators.BlockFamilyProvider pool = generator.family(getPlanks());
        pool.generateFor(getBlockFamily());

        if (isOverworldTreeWood()){
			generator.createTrivialBlock(getLeaves(), TexturedModel.LEAVES);
        }

        if (notBambooVariant()){
			generator.woodProvider(getLog()).logWithHorizontal(getLog()).wood(getWood());
			generator.woodProvider(getStrippedLog()).logWithHorizontal(getStrippedLog()).wood(getStrippedWood());
        }
        if (woodsetSettings.hasMosaic()){
			generator.woodProvider(getLog()).logUVLocked(getLog());
			generator.woodProvider(getStrippedLog()).logUVLocked(getStrippedLog());
        }

		generator.createHangingSign(strippedLog, hangingSign, wallHangingSign);

		generator.registerSimpleFlatItemModel(getBoatItem());
		generator.registerSimpleFlatItemModel(getChestBoatItem());

		generator.createShelf(this.getShelf(), this.getStrippedLog());
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

    public BlockBehaviour.Properties createLeavesBlock() {
        return createLeavesBlock(MapColor.COLOR_GREEN);
    }
    public BlockBehaviour.Properties createLeavesBlock(MapColor color) {
        return BlockBehaviour.Properties.of().mapColor(color).strength(0.2F).randomTicks().sound(woodsetSettings.leaveSoundGroup).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never);
    }



	public static void addToBuildingTab(Item preceedingItem, WoodsetRegistry woodset){
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
			entries.addAfter(preceedingItem, woodset.getPlanks(), woodset.getStairs(), woodset.getSlab(),
				woodset.getFence(), woodset.getFenceGate(),
				woodset.getDoor(), woodset.getTrapDoor(),
				woodset.getPressurePlate(), woodset.getButton());
			if (woodset.notBambooVariant()){
				entries.addAfter(preceedingItem, woodset.getWood(), woodset.getStrippedWood());
			}
			if (woodset.getWoodsetSettings().hasMosaic()){
				entries.addAfter(preceedingItem, woodset.getMosaic(), woodset.getMosaicStairs(), woodset.getMosaicSlab());
			}
			entries.addAfter(preceedingItem, woodset.getLog(), woodset.getStrippedLog());
		});
	}

	public static void addToNaturalTab(Item preceedingItem, WoodsetRegistry woodset, Block sapling){
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
			entries.addAfter(preceedingItem, woodset.getLeaves(), sapling.asItem());
		});
	}

	public static void addToFunctionalTab(Item preceedingItem, WoodsetRegistry woodset){
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
			entries.addAfter(preceedingItem, woodset.shelf, woodset.signItem, woodset.hangingSignItem);
		});
	}

	public static void addToUtilitiesTab(Item preceedingItem, WoodsetRegistry woodset){
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
			entries.addAfter(preceedingItem, woodset.boatItem, woodset.chestBoatItem);
		});
	}

    private String hasPlanks(){
        return RecipeProvider.getHasName(getPlanks());
    }

    public void generateRecipes(RecipeProvider recipeGenerator, RecipeOutput exporter, TagKey<Item> logs){
        recipeGenerator.planksFromLog(getPlanks(), logs, 4);
        recipeGenerator.stairBuilder(this.getStairs(), Ingredient.of(this.getPlanks())).unlockedBy(hasPlanks(), recipeGenerator.has(this.getPlanks())).save(exporter);
        recipeGenerator.slab(RecipeCategory.BUILDING_BLOCKS, this.getSlab(), this.getPlanks());
        recipeGenerator.generateRecipes(getBlockFamily(), FeatureFlagSet.of());
        if (notBambooVariant()){
            recipeGenerator.woodFromLogs(getWood(), getLog());
            recipeGenerator.woodFromLogs(getStrippedWood(), getStrippedLog());
        }
        if (getWoodsetSettings().hasMosaic()){
            recipeGenerator.mosaicBuilder(RecipeCategory.BUILDING_BLOCKS, this.getMosaic(), this.getSlab());
            recipeGenerator.stairBuilder(this.getMosaicStairs(), Ingredient.of(this.getPlanks())).unlockedBy(hasPlanks(), recipeGenerator.has(this.getMosaic())).save(exporter);
            recipeGenerator.slab(RecipeCategory.BUILDING_BLOCKS, this.getMosaicStairs(), this.getPlanks());
        }
        recipeGenerator.fenceBuilder(fence, Ingredient.of(planks)).unlockedBy(hasPlanks(), recipeGenerator.has(planks)).save(exporter);
        recipeGenerator.fenceGateBuilder(fenceGate, Ingredient.of(planks)).unlockedBy(hasPlanks(), recipeGenerator.has(planks)).save(exporter);
        recipeGenerator.doorBuilder(door, Ingredient.of(planks)).unlockedBy(hasPlanks(), recipeGenerator.has(planks)).save(exporter);
        recipeGenerator.trapdoorBuilder(trapDoor, Ingredient.of(planks)).unlockedBy(hasPlanks(), recipeGenerator.has(planks)).save(exporter);

        recipeGenerator.signBuilder(signItem, Ingredient.of(planks)).unlockedBy(hasPlanks(), recipeGenerator.has(planks)).save(exporter);
        recipeGenerator.hangingSign(getHangingSignItem(), getStrippedLog());

        recipeGenerator.woodenBoat(getBoatItem(), getPlanks());
        recipeGenerator.chestBoat(getChestBoatItem(), getBoatItem());

        recipeGenerator.shelf(getShelf(), getStrippedLog());
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
