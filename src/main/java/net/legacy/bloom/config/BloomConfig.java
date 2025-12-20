package net.legacy.bloom.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.legacy.bloom.Bloom;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


@Config(name = Bloom.MOD_ID)
public class BloomConfig implements ConfigData {

    @Contract(pure = true)
    public static @NotNull Path configPath(boolean json5) {
        return Path.of("./config/" + Bloom.MOD_ID + "." + (json5 ? "json5" : "json"));
    }

    public static BloomConfig get;

    public static void init() {
        AutoConfig.register(BloomConfig.class, JanksonConfigSerializer::new);
        get = AutoConfig.getConfigHolder(BloomConfig.class).getConfig();
    }

    @ConfigEntry.Gui.CollapsibleObject
    public BiomeConfig biomes = new BiomeConfig();

    public static class BiomeConfig {
        @ConfigEntry.Category("config")
        @ConfigEntry.Gui.Tooltip
        public boolean placeholder = true;
    }
}