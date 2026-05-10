package net.rebel459.bloom.client.snow;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public final class SodiumSnowOverlayLightingContext {
	private static final ThreadLocal<FakeState> FAKE_STATE = new ThreadLocal<>();

	private SodiumSnowOverlayLightingContext() {
	}

	public static void push(BlockPos pos, BlockState state) {
		FAKE_STATE.set(new FakeState(pos.immutable(), state));
	}

	public static void pop() {
		FAKE_STATE.remove();
	}

	public static boolean isActiveFor(BlockPos pos) {
		FakeState fake = FAKE_STATE.get();
		return fake != null && fake.pos.equals(pos);
	}

	public static boolean isActiveFor(int x, int y, int z) {
		FakeState fake = FAKE_STATE.get();
		return fake != null && fake.pos.getX() == x && fake.pos.getY() == y && fake.pos.getZ() == z;
	}

	public static @Nullable BlockState get(BlockPos pos) {
		FakeState fake = FAKE_STATE.get();

		if (fake == null || !fake.pos.equals(pos)) {
			return null;
		}

		return fake.state;
	}

	private record FakeState(BlockPos pos, BlockState state) {
	}
}
