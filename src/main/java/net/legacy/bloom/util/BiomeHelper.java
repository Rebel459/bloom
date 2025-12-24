package net.legacy.bloom.util;

import net.minecraft.core.Holder;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.attribute.BackgroundMusic;

import java.util.Optional;

public class BiomeHelper {
    public static final int STOCK_FOG_COLOR = 12638463;
    public static final int COLD_WATER_COLOR = 4020182;
    public static final int COLD_WATER_FOG_COLOR = 329011;
    public static final int STOCK_WATER_COLOR = 4159204;
    public static final int STOCK_WATER_FOG_COLOR = 329011;
    public static final int WARM_WATER_COLOR = 4566514;
    public static final int WARM_WATER_FOG_COLOR = 267827;

    public static float TEMPERATURE_0 = -1F;
    public static float TEMPERATURE_1 = -0.45F;
    public static float TEMPERATURE_2 = -0.15F;
    public static float TEMPERATURE_3 = 0.2F;
    public static float TEMPERATURE_4 = 0.55F;
    public static float TEMPERATURE_MAX = 1F;

    public static float HUMIDITY_0 = -1F;
    public static float HUMIDITY_1 = -0.35F;
    public static float HUMIDITY_2 = -0.1F;
    public static float HUMIDITY_3 = 0.1F;
    public static float HUMIDITY_4 = 0.3F;
    public static float HUMIDITY_MAX = 1F;

    public static float EROSION_0 = -1F;
    public static float EROSION_1 = -0.78F;
    public static float EROSION_2 = -0.375F;
    public static float EROSION_3 = -0.2225F;
    public static float EROSION_4 = 0.05F;
    public static float EROSION_5 = 0.45F;
    public static float EROSION_6 = 0.55F;
    public static float EROSION_MAX = 1F;

    public static BackgroundMusic music(Holder<SoundEvent> music, boolean underWater) {
        if (underWater) {
            return new BackgroundMusic(
                    Optional.of(Musics.createGameMusic(music)),
                    Optional.of(Musics.CREATIVE),
                    Optional.of(Musics.UNDER_WATER)
            );
        }
        else {
            return new BackgroundMusic(
                    Optional.of(Musics.createGameMusic(music)),
                    Optional.of(Musics.CREATIVE),
                    Optional.empty()
            );
        }
    }
}
