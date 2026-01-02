package net.legacy.bloom.util;

import org.apache.commons.lang3.tuple.Triple;

public record NoiseRule(Triple<NoiseRules.Type, Float, Float> rules) {

	public static NoiseRule create(NoiseRules.Type type, float min, float max) {
		return new NoiseRule(Triple.of(type, min, max));
	}

	public NoiseRules.Type getType() {
		return rules.getLeft();
	}

	public float getMin() {
		return rules.getMiddle();
	}

	public float getMax() {
		return rules.getRight();
	}

}
