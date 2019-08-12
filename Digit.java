package io.alialaware.caliala.core;

public class Digit {
	
	public static long getNilaiInPos(char ch, int basis, int pos) {
		int x = toInt(ch);
		return x * getPangkatLong(basis, pos);
	}
	
	public static long getPangkatLong(int basis, int pos) {
		return ((Double)  Math.pow(basis, pos)).longValue();
	}
	
	public static String getDecPoint(String str) {
		return insertChar(str, 3, ',');
	}
	
	public static String changeDecPoint(String str) {
		return str.replaceAll(",", ".");
	}
	
	public static String getSpace(String str, int offset) {
		return insertChar(str, offset, ' ');
	}
	
	public static String insertChar(String str, int offset, char chr) {
		StringBuilder sB = new StringBuilder(str);
		int n = sB.length();
		while (n > 0) {
			n -= offset;
			if(n > 0)
				sB.insert(n, chr);
		}
		return sB.toString();
	}
	
	public static int toInt(char ch) {
		int x = 0;
		if (ch >= 'A' && ch <= 'F' || ch >= 'a' && ch <= 'f') {
			x = getHexInt(ch);
		}else {
			x = Integer.valueOf(String.valueOf(ch));
		}
		return x;
	}
	
	private static int getHexInt(char ch) {
		if (ch == 'A' || ch == 'a') {
			return 10;
		}else if (ch == 'B' || ch == 'b') {
			return 11;
		}else if (ch == 'C' || ch == 'c') {
			return 12;
		}else if (ch == 'D' || ch == 'd') {
			return 13;
		}else if (ch == 'E' || ch == 'e') {
			return 14;
		}else if (ch == 'F' || ch == 'f') {
			return 15;
		}else  {
			return Integer.valueOf(String.valueOf(ch));
		}
	}
	
	public static char getHexChar(int intgr) {
		if(intgr >= 0 && intgr <= 15) {
			switch (intgr) {
			case 10:
				return 'A';
			case 11:
				return 'B';
			case 12:
				return 'C';
			case 13:
				return 'D';
			case 14:
				return 'E';
			case 15:
				return 'F';
			default:
				return String.valueOf(intgr).charAt(0);
			}
		}
		return 'z';
	}
	
	public static String getPangkat(int digitPosition) {
		String p = String.valueOf(digitPosition);
		String pangkat = "";
		char[] angka = {'0', '1', '2', '3', '4', '5', '6', '7'
				, '8', '9'};
		String[] supScript = {"\u2070", "\u00b9", "\u00b2", "\u00b3", "\u2074"
				, "\u2075", "\u2076", "\u2077", "\u2078", "\u2079"}; 
		for(int i = 0; i < p.length(); i++) {
			for(int j = 0; j < angka.length; j++) {
				if(p.charAt(i) == (angka[j])) {
					pangkat += supScript[j];
					break;
				}
			}
		}
		return pangkat;
		
	}

	public static String removeZeroFront(String str) {
		if (str != null && !str.equals("")) {
			return str.replaceFirst("0*", "");
		}
		return "string null or empty";
	}
}
