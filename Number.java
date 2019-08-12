package io.alialaware.caliala.core;

public class Number {
	
	public static String getGroupCharBck(String initialValue, int divider, int i) {
		return initialValue.substring(
				Math.max(0, initialValue.length() - (divider * (i + 1))), 
				Math.min(initialValue.length(), initialValue.length() - (divider * i)) 
				);
	}
}
