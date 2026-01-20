package net.legacy.bloom.sound;

import net.legacy.bloom.Bloom;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class BloomSounds {
	public static final SoundEvent DOLERITE_BREAK = register("block.dolerite.break");
	public static final SoundEvent DOLERITE_STEP = register("block.dolerite.step");
	public static final SoundEvent DOLERITE_PLACE = register("block.dolerite.place");
	public static final SoundEvent DOLERITE_HIT = register("block.dolerite.hit");
	public static final SoundEvent DOLERITE_FALL = register("block.dolerite.fall");

	public static final Holder.Reference<SoundEvent> MUSIC_BIOME_FEN = registerForHolder("music.overworld.fen");
	public static final Holder.Reference<SoundEvent> MUSIC_BIOME_TAIGA = registerForHolder("music.overworld.taiga");
	public static final Holder.Reference<SoundEvent> MUSIC_BIOME_OLD_GROWTH_TAIGA = registerForHolder("music.overworld.old_growth_taiga");
	public static final Holder.Reference<SoundEvent> MUSIC_BIOME_GOLDEN_FOREST = registerForHolder("music.overworld.golden_forest");

	private static SoundEvent register(String string) {
		Identifier identifier = Bloom.id(string);
		return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
	}

	private static Holder.Reference<SoundEvent> registerForHolder(String id) {
		return registerForHolder(Bloom.id(id));
	}

	private static Holder.Reference<SoundEvent> registerForHolder(Identifier id) {
		return registerForHolder(id, id);
	}

	private static Holder.Reference<SoundEvent> registerForHolder(Identifier id, Identifier soundId) {
		return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId));
	}

	public static void init() {}
}
