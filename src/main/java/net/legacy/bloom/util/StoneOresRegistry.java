package net.legacy.bloom.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import com.google.common.collect.ImmutableBiMap;
import net.fabricmc.loader.api.FabricLoader;
import net.legacy.bloom.Bloom;
import net.legacy.bloom.registry.BloomBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class StoneOresRegistry {
	public static List<StoneOresRegistry> ALL_REGISTRIES = new ArrayList<>();
    private final Map<OreType, Block> oresMap = new HashMap<>();
    private BiFunction<OreType, BlockBehaviour.Properties, BlockBehaviour.Properties> manualOverrideFunction = (oreType, properties) -> properties;
    private float strengthIncrease = 1.5F;
    private float explosionResistance = 3.0F;
    private final Block baseStone;
    public final boolean deep;
    private final BiFunction<OreType, BlockBehaviour.Properties, Block> normalFunction;
	public static Map<Block, BiFunction<BlockModelGenerators, Block, Boolean>> STONE_MODELS_MAP = Map.ofEntries(
		Map.entry(Blocks.SANDSTONE, (generators, block) -> {
			StoneCustomDatagens.sandstoneBlockModel(generators, Blocks.SANDSTONE, block);
			return true;
		}),
		Map.entry(Blocks.RED_SANDSTONE, (generators, block) -> {
			StoneCustomDatagens.sandstoneBlockModel(generators, Blocks.RED_SANDSTONE, block);
			return true;
		})
	);

    public StoneOresRegistry(Block baseStone, boolean deep, BiFunction<OreType, BlockBehaviour.Properties, Block> normalFunction) {
        this.baseStone = baseStone;
        this.deep = deep;
        this.normalFunction = normalFunction;
		ALL_REGISTRIES.add(this);
    }
    public StoneOresRegistry(Block baseStone, boolean deep){
        this(baseStone, deep, (type, properties) -> {
            if (type == OreType.REDSTONE) return new RedStoneOreBlock(properties);
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

	public Block getBaseStone() {
		return baseStone;
	}

	public StoneOresRegistry build(){
        for (OreType type : OreType.ORES) {
            if (!type.modRequirements.isEmpty() && !type.modRequirements.stream().allMatch(s -> FabricLoader.getInstance().isModLoaded(s))) continue;

			final BlockBehaviour.Properties finalProperties = manualOverrideFunction.apply(
				type,
				BlockBehaviour.Properties.ofFullCopy(baseStone)
					.strength(baseStone.defaultDestroyTime() + getStrengthIncrease()).explosionResistance(getExplosionResistance())
			);
			final Block block = BloomBlocks.register(
				BuiltInRegistries.BLOCK.getKey(baseStone).getPath() + "_" + type.name + "_ore",
				properties -> normalFunction.apply(type, properties),
				finalProperties,
				true
			);
			oresMap.put(type, block);
        }

        return this;
    }

    public static class OreType {
        public static List<OreType> ORES = new ArrayList<>();

        public static final OreType COAL = new OreType("coal", Blocks.COAL_ORE, UniformInt.of(0, 2));
        public static final OreType COPPER = new OreType("copper", Blocks.COPPER_ORE, ConstantInt.of(0));
        public static final OreType IRON = new OreType("iron", Blocks.IRON_ORE, ConstantInt.of(0));
        public static final OreType REDSTONE = new OreType("redstone", Blocks.REDSTONE_ORE, null);
        public static final OreType GOLD = new OreType("gold", Blocks.GOLD_ORE, ConstantInt.of(0));
        public static final OreType DIAMOND = new OreType("diamond", Blocks.DIAMOND_ORE, UniformInt.of(3, 7));
        public static final OreType EMERALD = new OreType("emerald", Blocks.EMERALD_ORE, UniformInt.of(3, 7));
        public static final OreType LAPIS = new OreType("lapis", Blocks.LAPIS_ORE, UniformInt.of(2, 5));
        public static final OreType SAPPHIRE = OreType.compatOreType(
			"sapphire",
			Identifier.fromNamespaceAndPath("legacies_and_legends", "sapphire_ore"),
			UniformInt.of(2, 5)
		).addModRequirement("legacies_and_legends");

        public final String name;
        public final Block baseBlock;
        private final IntProvider xpProvider;
        private final List<String> modRequirements = new ArrayList<>();

        public OreType(String name, Block baseBlock, IntProvider xpProvider) {
            this.name = name;
            this.baseBlock = baseBlock;
            this.xpProvider = xpProvider;
            ORES.add(this);
        }

        public OreType addModRequirement(String... ids){
            modRequirements.addAll(Arrays.asList(ids));
            return this;
        }

        public static OreType compatOreType(String name, Identifier baseBlock, IntProvider xpProvider){
            return new OreType(name, BuiltInRegistries.BLOCK.getValue(baseBlock), xpProvider);
        }
    }

	private static class StoneCustomDatagens{
		private static void sandstoneBlockModel(BlockModelGenerators blockModelGenerators, Block stone, Block ore){
			Identifier oreID = TextureMapping.getBlockTexture(ore);
			TexturedModel texturedModel = TexturedModel.TOP_BOTTOM_WITH_WALL.get(stone)
				.updateTextures(textureMapping -> textureMapping.put(TextureSlot.SIDE, oreID).put(TextureSlot.BOTTOM, oreID)
				);
			MultiVariant multiVariant = BlockModelGenerators.plainVariant(texturedModel.create(ore, blockModelGenerators.modelOutput));
			blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ore, multiVariant));
		}
	}
}
