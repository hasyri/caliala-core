package io.alialaware.caliala.core;

import java.util.ArrayList;
import java.util.List;

public class Decimal implements NumberKind {
	public static final int BASIS = 10; 
	public static final int DigLimit = 19;
    public static final String NOTNUMBER = " is not a decimal number";
	private static final String failedConvertMsg = 
			"Can't convert, please make sure it's a decimal number and has no more than 9,223,372,036,854,775,807"
			;
	private String initialValue;
	private List<MethodItem> methodToBin;
	private List<MethodItem> methodToOct;
	private List<MethodItem> methodToHex;
	
    public Decimal() {
    }

	public Decimal(String decimal) {
		initialValue = decimal;
		methodToBin = hitungKonversi(Binary.BASIS);
		methodToOct = hitungKonversi(Octal.BASIS);
		methodToHex = hitungKonversi(Hexadecimal.BASIS);
	}

	public String toBin() {
		if (isTrue(initialValue)) {
			return Digit.removeZeroFront(Long.toBinaryString(Long.parseLong(initialValue)));
		}
		return failedConvertMsg;
	}

	public String toOct() {
		if (isTrue(initialValue)) {
			return Long.toOctalString(Long.parseLong(initialValue));
		}
		return failedConvertMsg;
	}
	
	public String toHex() {
		if (isTrue(initialValue)) {
			return Long.toHexString(Long.parseLong(initialValue)).toUpperCase();
		}
		return failedConvertMsg;
	}
	
	public List<MethodItem> getMethodListBin() {
		return methodToBin;
	}

	public List<MethodItem> getMethodListOct() {
		return methodToOct;
	}

	public List<MethodItem> getMethodListHex() {
		return methodToHex;
	}

	private List<MethodItem> hitungKonversi(int basis) {
		MethodItem methodItem = new MethodItem(failedConvertMsg, failedConvertMsg);;
		List<MethodItem> methodList = new ArrayList<MethodItem>();
		if(isTrue(initialValue)) {
			long value = Long.parseLong(initialValue);
			long hasilBagi;
			long hasilKali;
			int sisa;
			String how;
			String num;
			while(value > 0) {
				hasilBagi = value / basis;
				hasilKali = hasilBagi * basis;
				sisa = (int) (value - hasilKali);
				how = value + " : " + basis + " = " + hasilBagi + ", " + value + " - "
						+ hasilKali + " = ";
				num = String.valueOf(Digit.getHexChar(sisa));
				methodItem = new MethodItem(how, num);
				methodList.add(methodItem);
				value /= basis;
			}
			return methodList;
		}
		methodList.add(methodItem);
		return methodList;
	}
	
	@Override
	public boolean isTrue(String str) {
		return isDigUnderLimit(str) && isNumber(str);
	}
	
	@Override
	public boolean isDigUnderLimit(String str) {
		if (str.length() == DigLimit) {
			try {
				return Long.parseLong(str) <= Long.parseLong("9223372036854775807");
			} catch (NumberFormatException e) {
				return false;
			}
		}else {
			return str.length() > 0 && str.length() <= DigLimit;
		}
	}

	@Override
	public boolean isNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!isDigit(str.charAt(i))) {
				System.out.println(str.charAt(i) + " is not a Decimal number");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isDigit(char ch) {
		return (ch >= '0' && ch <= '9');
	}
}
