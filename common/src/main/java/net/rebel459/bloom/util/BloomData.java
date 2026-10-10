package net.rebel459.bloom.util;

import java.util.List;
import net.rebel459.bloom.Bloom;
import net.rebel459.bloom.config.BloomConfig;
import net.rebel459.unified.api.codec.ExtensibleCodec;
import net.rebel459.unified.api.codec.ExtensibleCodecs;
import net.rebel459.unified.api.core.UnifiedData;
import net.rebel459.unified.api.registry.UnifiedLoadRequirementCodecs;

public class BloomData {

	private static final ExtensibleCodec.Simple<Boolean> WILD_CROPS_ENABLED = ExtensibleCodecs.LOAD_REQUIREMENT.register(Bloom.id("wild_crops_enabled"), () -> BloomConfig.get().farming.wild_crops);
	private static final ExtensibleCodec.Simple<Boolean> COTTON_ENABLED = ExtensibleCodecs.LOAD_REQUIREMENT.register(Bloom.id("cotton_enabled"), () -> BloomConfig.get().farming.cotton);

	public static final UnifiedData FARMERS_DELIGHT = UnifiedData.create(Bloom.MOD_ID).autoName().requirement(UnifiedLoadRequirementCodecs.MODS_LOADED.create(() -> List.of("farmersdelight"))).build();

	public static final UnifiedData FARMERS_DELIGHT_AND_WILD_COTTON = UnifiedData.create(Bloom.MOD_ID).autoName().requirement(ExtensibleCodecs.LOAD_REQUIREMENT.allOf().create(() -> List.of(
		UnifiedLoadRequirementCodecs.MODS_LOADED.create(() -> List.of("farmersdelight")),
		WILD_CROPS_ENABLED.create(),
		COTTON_ENABLED.create()
	))).build();

	public static void init() {}
}
