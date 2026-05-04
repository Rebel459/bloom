package net.rebel459.bloom.sound;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.rebel459.bloom.Bloom;
import net.rebel459.unified.platform.UnifiedRegistries;
import java.util.function.Supplier;

public class BloomSounds {

	static UnifiedRegistries.SoundEvents SOUNDS = UnifiedRegistries.SoundEvents.create(Bloom.MOD_ID);

	public static final Supplier<SoundEvent> DOLERITE_BREAK = SOUNDS.register("block.dolerite.break");
	public static final Supplier<SoundEvent> DOLERITE_STEP = SOUNDS.register("block.dolerite.step");
	public static final Supplier<SoundEvent> DOLERITE_PLACE = SOUNDS.register("block.dolerite.place");
	public static final Supplier<SoundEvent> DOLERITE_HIT = SOUNDS.register("block.dolerite.hit");
	public static final Supplier<SoundEvent> DOLERITE_FALL = SOUNDS.register("block.dolerite.fall");

	public static final Holder<SoundEvent> MUSIC_BIOME_FEN = SOUNDS.registerForHolder("music.overworld.fen");
	public static final Holder<SoundEvent> MUSIC_BIOME_TAIGA = SOUNDS.registerForHolder("music.overworld.taiga");
	public static final Holder<SoundEvent> MUSIC_BIOME_OLD_GROWTH_TAIGA = SOUNDS.registerForHolder("music.overworld.old_growth_taiga");
	public static final Holder<SoundEvent> MUSIC_BIOME_WINDSWEPT_JUNGLE = SOUNDS.registerForHolder("music.overworld.windswept_jungle");
	public static final Holder<SoundEvent> MUSIC_BIOME_GOLDEN_FOREST = SOUNDS.registerForHolder("music.overworld.golden_forest");

	public static void init() {}
}
