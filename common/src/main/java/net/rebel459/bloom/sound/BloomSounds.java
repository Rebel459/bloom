package net.rebel459.bloom.sound;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.rebel459.bloom.Bloom;
import net.rebel459.unified.api.core.Supplied;
import net.rebel459.unified.api.core.UnifiedRegistries;

public class BloomSounds {

	static UnifiedRegistries.SoundEvents SOUNDS = UnifiedRegistries.SoundEvents.create(Bloom.MOD_ID);

	public static final Supplied<SoundEvent> DOLERITE_BREAK = SOUNDS.register("block.dolerite.break");
	public static final Supplied<SoundEvent> DOLERITE_STEP = SOUNDS.register("block.dolerite.step");
	public static final Supplied<SoundEvent> DOLERITE_PLACE = SOUNDS.register("block.dolerite.place");
	public static final Supplied<SoundEvent> DOLERITE_HIT = SOUNDS.register("block.dolerite.hit");
	public static final Supplied<SoundEvent> DOLERITE_FALL = SOUNDS.register("block.dolerite.fall");

	public static final Holder<SoundEvent> MUSIC_BIOME_FEN = SOUNDS.register("music.overworld.fen").holder();
	public static final Holder<SoundEvent> MUSIC_BIOME_TAIGA = SOUNDS.register("music.overworld.taiga").holder();
	public static final Holder<SoundEvent> MUSIC_BIOME_OLD_GROWTH_TAIGA = SOUNDS.register("music.overworld.old_growth_taiga").holder();
	public static final Holder<SoundEvent> MUSIC_BIOME_WINDSWEPT_JUNGLE = SOUNDS.register("music.overworld.windswept_jungle").holder();
	public static final Holder<SoundEvent> MUSIC_BIOME_GOLDEN_FOREST = SOUNDS.register("music.overworld.golden_forest").holder();

	public static void init() {}
}
