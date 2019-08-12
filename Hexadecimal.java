package io.alialaware.caliala.core;

import java.util.ArrayList;
import java.util.List;

public class Hexadecimal implements NumberKind {
	public static final int BASIS = 16; 
	public static final int DigLimit = 16;
    public static final String NOTNUMBER = " is not a hexadecimal number";
	private static final String failedConvertMsg = 
			"Can't convert, please make sure it's an hexadecimal number and no more than 7FFFFFFFFFFFFFFF"
			;
	private String initialValue;
	private Method method;
	private Binary bin;
	private List<MethodItem> methodToBin;
	private List<MethodItem> methodToOct;
	private List<MethodItem> methodToDec;

    public Hexadecimal() {
    }

	public Hexadecimal(String hexadecimal) {
		initialValue = hexadecimal;
		method = new Method(BASIS, hexadecimal, failedConvertMsg, new Hexadecimal());
		methodToBin = hitungKonversi();
		bin = new Binary(toBin());
		methodToOct = bin.getMethodListOct();
		methodToDec = method.getMethodToDec();
	}
	
	public String getSum() {
		return method.getSum();
	}
	
	public List<MethodItem> getMethodListBin() {
		return methodToBin;
	}

	public List<MethodItem> getMethodListOct() {
		return methodToOct;
	}
	
	public List<MethodItem> getMethodListDec() {
		return methodToDec;
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
					num = "000" + value;
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
							if (num.length() < 4) {
	                            int len = num.length();
	                            for (int j = 0; j < 4 - len; j++) {
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

	public String toOct() {
		if (isTrue(initialValue)) {
			return bin.toOct();
		}
		return failedConvertMsg;
	}
	
	public String toDec() {
		return method.toDec();
	}

	@Override
	public boolean isTrue(String str) {
		// TODO Auto-generated method stub
		return isDigUnderLimit(str) && isNumber(str);
	}

	@Override
	public boolean isDigUnderLimit(String str) {
		if (str.length() == DigLimit) {
			return  (str.charAt(0) >= '0' && str.charAt(0) <= '7');
		}else {
			return str.length() > 0 && str.length() <= DigLimit;
		}
	}

	@Override
	public boolean isNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!isDigit(str.charAt(i))) {
				System.out.println(str.charAt(i) + " is not a Hexadecimal number");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isDigit(char ch) {
		return (ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'f' || 
				ch >= 'A' && ch <= 'F');
	}
}
