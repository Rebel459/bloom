package net.legacy.bloom.registry.data;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.legacy.bloom.registry.BloomBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.*;
import java.util.function.BiFunction;

public class StoneOresRegistry {
    private final Map<OreType, Block> oresMap = new HashMap<>();
    private BiFunction<OreType, BlockBehaviour.Properties, BlockBehaviour.Properties> manualOverrideFunction = (oreType, properties) -> properties;
    private float strengthIncrease = 1.5f;
    private float explosionResistance = 3.0f;
    private final Block baseStone;
    private final BiFunction<OreType, BlockBehaviour.Properties, Block> normalFunction;

    public StoneOresRegistry(Block baseStone, BiFunction<OreType, BlockBehaviour.Properties, Block> normalFunction){
        this.baseStone = baseStone;
        this.normalFunction = normalFunction;
    }
    public StoneOresRegistry(Block baseStone){
        this(baseStone, (type, properties) -> {
            if (type == OreType.REDSTONE){
                return new RedStoneOreBlock(properties);
            }
            return new DropExperienceBlock(type.xpProvider, properties);
        });
    }

    public StoneOresRegistry setManualOverrideFunction(BiFunction<OreType, BlockBehaviour.Properties, BlockBehaviour.Properties> manualOverrideFunction) {
        this.manualOverrideFunction = manualOverrideFunction;
        return this;
    }

    public float getStrengthIncrease() {
        return strengthIncrease;
    }

    public float getExplosionResistance() {
        return explosionResistance;
    }

    public StoneOresRegistry setStrengthIncrease(float strengthIncrease) {
        this.strengthIncrease = strengthIncrease;
        return this;
    }

    public StoneOresRegistry setExplosionResistance(float explosionResistance) {
        this.explosionResistance = explosionResistance;
        return this;
    }

    public Map<OreType, Block> getOresMap() {
        return oresMap;
    }

    public void addToCreativeTab(){
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            for (OreType value : OreType.ORES) {
                entries.addAfter(value.baseBlock.asItem(), getOresMap().get(value));
            }
        });
    }

    public StoneOresRegistry build(){
        for (OreType value : OreType.ORES) {
            if (value.neededMods.isEmpty() || value.neededMods.stream().allMatch(s -> FabricLoader.getInstance().isModLoaded(s))){
                BlockBehaviour.Properties finalProperties = manualOverrideFunction.apply(value, BlockBehaviour.Properties.ofFullCopy(baseStone).strength(baseStone.defaultDestroyTime() + getStrengthIncrease()).explosionResistance(getExplosionResistance()));
                Block block = BloomBlocks.register(
                        BuiltInRegistries.BLOCK.getKey(baseStone).getPath() + "_" + value.name + "_ore", properties -> normalFunction.apply(value, properties), finalProperties
                );
                oresMap.put(value, block);
            }
        }
        return this;
    }

    public static class OreType {
        public static List<OreType> ORES = new ArrayList<>();


        public static final OreType COAL = new OreType("coal", Blocks.COAL_ORE, UniformInt.of(0, 2));
        public static final OreType COPPER = new OreType("copper", Blocks.COPPER_ORE, ConstantInt.of(0));
        public static final OreType IRON = new OreType("iron", Blocks.IRON_ORE, ConstantInt.of(0));
        public static final OreType REDSTONE = new OreType("redstone", Blocks.REDSTONE_ORE, ConstantInt.of(0));
        public static final OreType GOLD = new OreType("gold", Blocks.GOLD_ORE, ConstantInt.of(0));
        public static final OreType DIAMOND = new OreType("diamond", Blocks.DIAMOND_ORE, ConstantInt.of(0));
        public static final OreType EMERALD = new OreType("emerald", Blocks.EMERALD_ORE, ConstantInt.of(0));
        public static final OreType LAPIS = new OreType("lapis", Blocks.LAPIS_ORE, ConstantInt.of(0));
        public static final OreType SAPPHIRE = OreType.compatOreType("sapphire", Identifier.fromNamespaceAndPath("", ""), ConstantInt.of(0)).addNeededMod("legacies_and_legends");


        private final String name;
        private final Block baseBlock;
        private final IntProvider xpProvider;
        private final List<String> neededMods = new ArrayList<>();

        public OreType(String name, Block baseBlock, IntProvider xpProvider){
            this.name = name;
            this.baseBlock = baseBlock;
            this.xpProvider = xpProvider;
            ORES.add(this);
        }
        public OreType addNeededMod(String... ids){
            neededMods.addAll(Arrays.asList(ids));
            return this;
        }
        public static OreType compatOreType(String name, Identifier baseBlock, IntProvider xpProvider){
            return new OreType(name, BuiltInRegistries.BLOCK.getValue(baseBlock), xpProvider);
        }
    }
}
