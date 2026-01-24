package net.legacy.bloom.registry;

import net.legacy.bloom.worldgen.biome.AridShore;
import net.legacy.bloom.worldgen.biome.ColdBeach;
import net.legacy.bloom.worldgen.biome.ColdRiver;
import net.legacy.bloom.worldgen.biome.Fen;
import net.legacy.bloom.worldgen.biome.GoldenForest;
import net.legacy.bloom.worldgen.biome.LukewarmBeach;
import net.legacy.bloom.worldgen.biome.LukewarmRiver;
import net.legacy.bloom.worldgen.biome.PineTaiga;
import net.legacy.bloom.worldgen.biome.SnowyPineTaiga;
import net.legacy.bloom.worldgen.biome.SnowyShore;
import net.legacy.bloom.worldgen.biome.SparseWindsweptJungle;
import net.legacy.bloom.worldgen.biome.TropicalBeach;
import net.legacy.bloom.worldgen.biome.TropicalRiver;
import net.legacy.bloom.worldgen.biome.WarmRiver;
import net.legacy.bloom.worldgen.biome.WindsweptJungle;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class BloomBiomes {
    public static final ResourceKey<Biome> WARM_RIVER = WarmRiver.INSTANCE.getKey();
    public static final ResourceKey<Biome> ARID_SHORE = AridShore.INSTANCE.getKey();
    public static final ResourceKey<Biome> TROPICAL_RIVER = TropicalRiver.INSTANCE.getKey();
    public static final ResourceKey<Biome> TROPICAL_BEACH = TropicalBeach.INSTANCE.getKey();
    public static final ResourceKey<Biome> COLD_RIVER = ColdRiver.INSTANCE.getKey();
    public static final ResourceKey<Biome> COLD_BEACH = ColdBeach.INSTANCE.getKey();
    public static final ResourceKey<Biome> LUKEWARM_RIVER = LukewarmRiver.INSTANCE.getKey();
    public static final ResourceKey<Biome> LUKEWARM_BEACH = LukewarmBeach.INSTANCE.getKey();
    public static final ResourceKey<Biome> WINDSWEPT_JUNGLE = WindsweptJungle.INSTANCE.getKey();
	public static final ResourceKey<Biome> SPARSE_WINDSWEPT_JUNGLE = SparseWindsweptJungle.INSTANCE.getKey();
	public static final ResourceKey<Biome> FEN = Fen.INSTANCE.getKey();
	public static final ResourceKey<Biome> SNOWY_SHORE = SnowyShore.INSTANCE.getKey();
	public static final ResourceKey<Biome> PINE_TAIGA = PineTaiga.INSTANCE.getKey();
	public static final ResourceKey<Biome> SNOWY_PINE_TAIGA = SnowyPineTaiga.INSTANCE.getKey();
	public static final ResourceKey<Biome> GOLDEN_FOREST = GoldenForest.INSTANCE.getKey();

    public static void init() {}

    public static void bootstrap(BootstrapContext<Biome> context) {
        register(context, WARM_RIVER, WarmRiver.INSTANCE.create(context));
        register(context, ARID_SHORE, AridShore.INSTANCE.create(context));
        register(context, TROPICAL_RIVER, TropicalRiver.INSTANCE.create(context));
        register(context, TROPICAL_BEACH, TropicalBeach.INSTANCE.create(context));
        register(context, COLD_RIVER, ColdRiver.INSTANCE.create(context));
        register(context, COLD_BEACH, ColdBeach.INSTANCE.create(context));
        register(context, LUKEWARM_RIVER, LukewarmRiver.INSTANCE.create(context));
        register(context, LUKEWARM_BEACH, LukewarmBeach.INSTANCE.create(context));
		register(context, WINDSWEPT_JUNGLE, WindsweptJungle.INSTANCE.create(context));
		register(context, SPARSE_WINDSWEPT_JUNGLE, SparseWindsweptJungle.INSTANCE.create(context));
		register(context, FEN, Fen.INSTANCE.create(context));
		register(context, SNOWY_SHORE, SnowyShore.INSTANCE.create(context));
		register(context, PINE_TAIGA, PineTaiga.INSTANCE.create(context));
		register(context, SNOWY_PINE_TAIGA, SnowyPineTaiga.INSTANCE.create(context));
		register(context, GOLDEN_FOREST, GoldenForest.INSTANCE.create(context));
    }

    private static void register(BootstrapContext<Biome> entries, ResourceKey<Biome> key, Biome biome) {
        entries.register(key, biome);
    }
}
