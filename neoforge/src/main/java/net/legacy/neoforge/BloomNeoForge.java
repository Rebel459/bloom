package net.legacy.neoforge;

import net.legacy.Bloom;
import net.neoforged.fml.common.Mod;

@Mod(Bloom.MOD_ID)
public final class BloomNeoForge {
    public BloomNeoForge() {
        // Run our common setup.
        Bloom.init();
    }
}
