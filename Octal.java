package io.alialaware.caliala.core;

import java.util.ArrayList;
import java.util.List;

public class Octal implements NumberKind {
	public static final int BASIS = 8; 
	public static final int DigLimit = 21;
    public static final String NOTNUMBER = " is not a octal number";
	private static final String failedConvertMsg = 
			"Can't convert, please make sure it's an octal number and has digits less than 22"
			;
	private String initialValue;
	private Method method;
	private Binary bin;
	private List<MethodItem> methodToBin;
	private List<MethodItem> methodToDec;
	private List<MethodItem> methodToHex;
	
	public Octal() {
	}
	
	public Octal(String octal) {
		initialValue = octal;
		method = new Method(BASIS, octal, failedConvertMsg, new Octal());
		methodToDec = method.getMethodToDec();
		methodToBin = hitungKonversi();
		bin = new Binary(toBin());
		methodToHex = bin.getMethodListHex();
	}
	
	public String getSum() {
		return method.getSum();
	}
	
	public List<MethodItem> getMethodListBin() {
		return methodToBin;
	}
	
	public List<MethodItem> getMethodListDec() {
		return methodToDec;
	}
	
	public List<MethodItem> getMethodListHex() {
		return methodToHex;
	}
	
	private List<MethodItem> hitungKonversi() {
		int basis = 2;
		MethodItem methodItem = new MethodItem(failedConvertMsg, failedConvertMsg);;
		List<MethodItem> methodList = new ArrayList<MethodItem>();
		if(isTrue(initialValue)) {
			for (int i = initialValue.length() - 1; i >= 0; i--) {
				int value = Digit.toInt(initialValue.charAt(i));
				int hasilBagi;
				int hasilKali;
				int sisa;
				String how;
				String num;
				how = initialValue.charAt(i) + " = ";
				num = "";
				if (value == 0 || value == 1) {
					num = "00" + value;
				}else {
					while(value > 0) {
						hasilBagi = value / basis;
						hasilKali = hasilBagi * basis;
						sisa = value - hasilKali;
						if(value != Digit.toInt(initialValue.charAt(i))) {
							how += "\n" + "   = ";
						}
						num = String.valueOf(sisa) + num;
						if(value < basis) {
							if (num.length() < 3) {
								int len = num.length();
								for (int j = 0; j < 3 - len; j++) {
									num = 0 + num;
								}
							}
							how += String.valueOf(sisa + "\n" + "   = ");
						}else {
							how += value + " : " + basis + " = " + hasilBagi + ", " + value + " - "
									+ hasilKali + " = " + sisa;
						}
						value /= basis;
					}
				}
				methodItem = new MethodItem(how, num);
				methodList.add(methodItem);
			}
			return methodList;
		}
		methodList.add(methodItem);
		return methodList;
	}
	
	public String toBin() {
		if (isTrue(initialValue)) {
			String bin = "";
			for (int i = 0; i < methodToBin.size(); i++) {
				bin = methodToBin.get(i).getNum() + bin;
			}
			return Digit.removeZeroFront(bin);
		}
		return failedConvertMsg;
	}

	public String toDec() {
		return method.toDec();
	}
	
	public String toHex() {
		if (isTrue(initialValue)) {
			return bin.toHex();
		}
		return failedConvertMsg;
	}
	
	@Override
	public boolean isTrue(String str) {
		return isDigUnderLimit(str) && isNumber(str);
	}

	@Override
	public boolean isDigUnderLimit(String str) {
		return str.length() > 0 && str.length() <= DigLimit;
	}

	@Override
	public boolean isNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!isDigit(str.charAt(i))) {
				System.out.println(str.charAt(i) + " is not an Octal number");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isDigit(char ch) {
		return (ch >= '0' && ch <= '7');
	}
}
