package net.legacy.bloom.config;

import java.nio.file.Path;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.legacy.bloom.Bloom;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


@Config(name = Bloom.MOD_ID)
public class BloomConfig implements ConfigData {

    @Contract(pure = true)
    public static Path configPath(boolean json5) {
        return Path.of("./config/" + Bloom.MOD_ID + "." + (json5 ? "json5" : "json"));
    }

    public static BloomConfig get;

    public static void init() {
        AutoConfig.register(BloomConfig.class, JanksonConfigSerializer::new);
        get = AutoConfig.getConfigHolder(BloomConfig.class).getConfig();
    }

	@ConfigEntry.Gui.CollapsibleObject
	public BiomeConfig biomes = new BiomeConfig();

	@ConfigEntry.Gui.CollapsibleObject
	public WorldgenConfig worldgen = new WorldgenConfig();

	@ConfigEntry.Gui.CollapsibleObject
	public FarmingConfig farming = new FarmingConfig();

    public static class BiomeConfig {
		@ConfigEntry.Category("config")
		public boolean golden_forest = true;
		@ConfigEntry.Category("config")
		public boolean fen = true;
		@ConfigEntry.Category("config")
		public boolean pine_taiga = true;
		@ConfigEntry.Category("config")
		public boolean snowy_pine_taiga = true;
		@ConfigEntry.Category("config")
		public boolean windswept_jungle = true;
		@ConfigEntry.Category("config")
		public boolean sparse_windswept_jungle = true;
		@ConfigEntry.Category("config")
		public boolean arid_shore = true;
		@ConfigEntry.Category("config")
		public boolean warm_river = true;
		@ConfigEntry.Category("config")
		public boolean cold_beach = true;
		@ConfigEntry.Category("config")
		public boolean cold_river = true;
		@ConfigEntry.Category("config")
		public boolean lukewarm_beach = true;
		@ConfigEntry.Category("config")
		public boolean lukewarm_river = true;
		@ConfigEntry.Category("config")
		public boolean snowy_shore = true;
		@ConfigEntry.Category("config")
		public boolean tropical_beach = true;
		@ConfigEntry.Category("config")
		public boolean tropical_river = true;
    }

	public static class WorldgenConfig {
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean pine_trees = true;
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean stony_cliffs = true;
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean ore_variants = true;
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean stone_variant_depth = true;
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean sandstone_depth = true;
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean dolerite_depth = true;
	}

	public static class FarmingConfig {
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean hoe_replanting = true;
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean cotton = true;
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean tradable_yarn = true;
		@ConfigEntry.Category("config")
		@ConfigEntry.Gui.Tooltip
		public boolean wild_crops = true;
	}
}
