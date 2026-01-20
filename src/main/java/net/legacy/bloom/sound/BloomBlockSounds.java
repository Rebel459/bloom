package net.legacy.bloom.sound;

import net.minecraft.world.level.block.SoundType;

public final class BloomBlockSounds {

    public static final SoundType DOLERITE = new SoundType(1F, 1F,
            BloomSounds.DOLERITE_BREAK,
            BloomSounds.DOLERITE_STEP,
            BloomSounds.DOLERITE_PLACE,
            BloomSounds.DOLERITE_HIT,
            BloomSounds.DOLERITE_FALL
    );

    public static void init() {
    }
}
