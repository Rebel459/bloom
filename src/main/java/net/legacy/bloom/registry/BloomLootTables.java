package net.legacy.bloom.registry;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.legacy.bloom.config.BloomConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

public final class BloomLootTables {

    public BloomLootTables() {}

    public static final ResourceKey<LootTable> BIRCH_RUINS = registerLegaciesAndLegends("chests/forest_ruins/birch");
    public static final ResourceKey<LootTable> CHERRY_RUINS = registerLegaciesAndLegends("chests/forest_ruins/cherry");
    public static final ResourceKey<LootTable> MAPLE_RUINS = registerLegaciesAndLegends("chests/forest_ruins/maple");
    public static final ResourceKey<LootTable> DEEP_RUINS = registerLegaciesAndLegends("chests/deep_ruins/deep");
    public static final ResourceKey<LootTable> SCULK_RUINS = registerLegaciesAndLegends("chests/deep_ruins/sculk");
    public static final ResourceKey<LootTable> PALE_CABIN = registerLegaciesAndLegends("chests/pale_cabin/chest");
    public static final ResourceKey<LootTable> PALE_CABIN_SECRET = registerLegaciesAndLegends("chests/pale_cabin/secret");
    public static final ResourceKey<LootTable> RUINED_AETHER_PORTAL = registerLegaciesAndLegends("chests/ruined_aether_portal");
    public static final ResourceKey<LootTable> RUINED_LIBRARY = registerLegaciesAndLegends("chests/ruined_library");
    public static final ResourceKey<LootTable> END_RUINS = registerLegaciesAndLegends("chests/end_ruins");
    public static final ResourceKey<LootTable> SWAMP_HUT = registerLegaciesAndLegends("chests/swamp_hut");
    public static final ResourceKey<LootTable> RUINS = registerLegaciesAndLegends("chests/ruins");
    public static final ResourceKey<LootTable> UNDERGROUND_CABIN = registerLegaciesAndLegends("chests/cabin/underground");
    public static final ResourceKey<LootTable> DEEP_CABIN = registerLegaciesAndLegends("chests/cabin/deep");
    public static final ResourceKey<LootTable> SPIRE = registerLegaciesAndLegends("chests/spire");
    public static final ResourceKey<LootTable> SPIRE_BASE = registerLegaciesAndLegends("chests/spire_base");

    public static final ResourceKey<LootTable> DUNGEON_CHEST = registerLegaciesAndLegends("chests/dungeon/chest");
    public static final ResourceKey<LootTable> DUNGEON_CHEST_SIMPLE = registerLegaciesAndLegends("chests/dungeon/simple/chest");
    public static final ResourceKey<LootTable> DUNGEON_CHEST_DEEP = registerLegaciesAndLegends("chests/dungeon/deep/chest");
    public static final ResourceKey<LootTable> DUNGEON_CHEST_ARID = registerLegaciesAndLegends("chests/dungeon/arid/chest");
    public static final ResourceKey<LootTable> DUNGEON_CHEST_FROZEN = registerLegaciesAndLegends("chests/dungeon/frozen/chest");
    public static final ResourceKey<LootTable> DUNGEON_CHEST_VERDANT = registerLegaciesAndLegends("chests/dungeon/verdant/chest");
    public static final ResourceKey<LootTable> DUNGEON_CHEST_INFERNAL = registerLegaciesAndLegends("chests/dungeon/infernal/chest");

    public static void init() {
        LootTableEvents.MODIFY.register((id, tableBuilder, source, registries) -> {
            LootPool.Builder pool;

            if (BloomConfig.get.farming.cotton) {
				if (BuiltInLootTables.VILLAGE_SHEPHERD.equals(id)) {
					pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
						.add(EmptyLootItem.emptyItem().setWeight(2))
						.add(LootItem.lootTableItem(BloomItems.YARN)).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)));
					tableBuilder.withPool(pool);
				}
				if (BuiltInLootTables.JUNGLE_TEMPLE.equals(id) || BuiltInLootTables.SIMPLE_DUNGEON.equals(id) || DUNGEON_CHEST_VERDANT.equals(id)) {
					pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
						.add(EmptyLootItem.emptyItem().setWeight(5))
						.add(LootItem.lootTableItem(BloomItems.COTTON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))));
					tableBuilder.withPool(pool);
				}
				if (BuiltInLootTables.VILLAGE_SHEPHERD.equals(id) || BuiltInLootTables.WOODLAND_MANSION.equals(id)) {
					pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
						.add(EmptyLootItem.emptyItem().setWeight(8))
						.add(LootItem.lootTableItem(BloomItems.COTTON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))));
					tableBuilder.withPool(pool);
				}
				if (DUNGEON_CHEST_SIMPLE.equals(id)) {
					pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
						.add(EmptyLootItem.emptyItem().setWeight(11))
						.add(LootItem.lootTableItem(BloomItems.COTTON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))));
					tableBuilder.withPool(pool);
				}
            }
        });
    }

    private static @NotNull ResourceKey<LootTable> registerLegaciesAndLegends(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath("legacies_and_legends", path));
    }
}
