package net.legacy.bloom.util;

import org.apache.commons.lang3.tuple.Triple;

public record NoiseRule(Triple<Type, Float, Float> rules) {

	public static NoiseRule create(Type type, float min, float max) {
		return new NoiseRule(Triple.of(type, min, max));
	}

	public Type getType() {
		return rules.getLeft();
	}

	public float getMin() {
		return rules.getMiddle();
	}

	public float getMax() {
		return rules.getRight();
	}

	public enum Type {
		HUMIDITY,
		CONTINENTALNESS,
		TEMPERATURE,
		EROSION,
		WEIRDNESS,
		DEPTH,
		HEIGHTMAP_DEPTH
	}
}
