package net.rebel459.bloom.sound;

import net.minecraft.world.level.block.SoundType;

public final class BloomBlockSounds {

    public static final SoundType DOLERITE = new SoundType(1F, 1F,
            BloomSounds.DOLERITE_BREAK.get(),
            BloomSounds.DOLERITE_STEP.get(),
            BloomSounds.DOLERITE_PLACE.get(),
            BloomSounds.DOLERITE_HIT.get(),
            BloomSounds.DOLERITE_FALL.get()
    );

    public static void init() {
    }
}
