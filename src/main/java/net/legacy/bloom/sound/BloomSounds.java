package net.legacy.bloom.sound;

import net.legacy.bloom.Bloom;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class BloomSounds {
	public static final Holder.Reference<SoundEvent> MUSIC_BIOME_FEN = registerForHolder("music.overworld.fen");
	public static final Holder.Reference<SoundEvent> MUSIC_BIOME_TAIGA = registerForHolder("music.overworld.taiga");
	public static final Holder.Reference<SoundEvent> MUSIC_BIOME_GOLDEN_BIRCH_FOREST = registerForHolder("music.overworld.golden_birch_forest");

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
