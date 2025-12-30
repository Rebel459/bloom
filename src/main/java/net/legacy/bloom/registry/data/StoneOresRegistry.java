package net.legacy.bloom.registry.data;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.legacy.bloom.registry.BloomBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class StoneOresRegistry {

    private final Map<OreType, Block> oresMap = new HashMap<>();
    private BiFunction<OreType, BlockBehaviour.Properties, BlockBehaviour.Properties> manualOverrideFunction = (oreType, properties) -> properties;
    private float strengthIncrease = 1.5f;
    private float explosionResistance = 3.0f;

    public StoneOresRegistry(Block baseStone){
        for (OreType value : OreType.values()) {
            BlockBehaviour.Properties finalProperties = manualOverrideFunction.apply(value, BlockBehaviour.Properties.ofFullCopy(baseStone).strength(baseStone.defaultDestroyTime() + getStrengthIncrease()).explosionResistance(getExplosionResistance()));
            Function<BlockBehaviour.Properties, Block> propertiesBlockFunction = properties -> new DropExperienceBlock(value.xpProvider, properties);
            if (value == OreType.REDSTONE){
                propertiesBlockFunction = RedStoneOreBlock::new;
            }
            Block block = BloomBlocks.register(
                    BuiltInRegistries.BLOCK.getKey(baseStone).getPath() + "_" + value.name + "_ore", propertiesBlockFunction, finalProperties
            );
            oresMap.put(value, block);
        }
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
            for (OreType value : OreType.values()) {
                entries.addAfter(value.baseBlock.asItem(), getOresMap().get(value));
            }
        });
    }

    public enum OreType implements StringRepresentable {
        COAL("coal", Blocks.COAL_ORE, UniformInt.of(0, 2)),
        COPPER("copper", Blocks.COPPER_ORE, ConstantInt.of(0)),
        IRON("iron", Blocks.IRON_ORE, ConstantInt.of(0)),
        REDSTONE("redstone", Blocks.REDSTONE_ORE, ConstantInt.of(0)),
        GOLD("gold", Blocks.GOLD_ORE, ConstantInt.of(0)),
        DIAMOND("diamond", Blocks.DIAMOND_ORE, ConstantInt.of(0));

        private final String name;
        private final Block baseBlock;
        private final IntProvider xpProvider;

        OreType(String name, Block baseBlock, IntProvider xpProvider){
            this.name = name;
            this.baseBlock = baseBlock;
            this.xpProvider = xpProvider;
        }
        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
