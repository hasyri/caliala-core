package io.alialaware.caliala.core;

public interface NumberKind {
	
	public boolean isTrue(String str);
	
	public boolean isDigUnderLimit(String str);
	
	public boolean isNumber(String str);
	
	public boolean isDigit(char ch);
}
