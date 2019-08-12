package io.alialaware.caliala.core;

import java.util.ArrayList;
import java.util.List;

public class Binary implements NumberKind {
	public static final int BASIS = 2; 
	public static final int DigLimit = 63;
    public static final String NOTNUMBER = " is not a binary number";
	private static final String failedConvertMsg = 
			"Can't convert, please make sure it's a binary number and has digits less than 64"
			;
	private String initialValue;
	private Method method;
	private List<MethodItem> methodToOct;
	private List<MethodItem> methodToDec;
	private List<MethodItem> methodToHex;
	
	public Binary() {
	}
	
	public Binary(String binary) {
		initialValue = binary;
		method = new Method(BASIS, binary, failedConvertMsg, new Binary());
		methodToDec = method.getMethodToDec();
		methodToOct = hitungKonversi(3);
		methodToHex = hitungKonversi(4);
	}
	
	public String getSum() {
		return method.getSum();
	}

	public List<MethodItem> getMethodListOct() {
		return methodToOct;
	}
	
	public List<MethodItem> getMethodListDec() {
		return methodToDec;
	}

	public List<MethodItem> getMethodListHex() {
		return methodToHex;
	}
		
	private int getNilaiIntPos(char digit, int pos) {
		return (int) Digit.getNilaiInPos(digit, Binary.BASIS, pos);
	}
	
	private String getKali(char digit, int pos, int j) {
		String kali = "";
		if(j > 0)
			kali += " + ";
		kali += "(" + digit + " x " + Binary.BASIS + Digit.getPangkat(pos) + ")" ;
		return kali;
	}
	
	private String getJumlah(char digit, int pos, int j) {
		String jumlah = "";
		if(j > 0)
			jumlah += " + ";
		jumlah += getNilaiIntPos(digit, pos);
		return jumlah;
	}
		
	private List<MethodItem> hitungKonversi(int divider) {
		MethodItem methodItem = new MethodItem(failedConvertMsg, failedConvertMsg);
		List<MethodItem> methodList = new ArrayList<MethodItem>();
		if(isTrue(initialValue)) {
			int length = initialValue.length() / divider;
			if(!isRemainZero(initialValue, divider)){
				length +=1;
			}
			String groupBit;
			for(int i = 0; i < length; i++) {
				groupBit = Number.getGroupCharBck(initialValue, divider, i);
				int pos = 0;
				int nilaiInPos;
				int total = 0;
				String kali = "";
				String jumlah = "";
				String how = "";
				String num = "";
				for(int j = 0; j < groupBit.length(); j++) {
					pos = groupBit.length() - 1 - j;
					nilaiInPos = getNilaiIntPos(groupBit.charAt(j), pos);
					total += nilaiInPos;
					kali += getKali(groupBit.charAt(j), pos, j);
					jumlah += getJumlah(groupBit.charAt(j), pos, j);
				}
				how = groupBit + " = " + kali + " = " + jumlah + " = ";
				num = String.valueOf(Digit.getHexChar(total));
				methodItem = new MethodItem(how, num);
				methodList.add(methodItem);
			}
			return methodList;
		}else {
			methodList.add(methodItem);
		}
		return methodList;
	}
	
	private boolean isRemainZero(String str, int divider) {
		return str.length() % divider == 0;
	}
	
	public String toOct() {
		if (isTrue(initialValue)) {
			String oct = "";
			for (int i = 0; i < methodToOct.size(); i++) {
				oct = methodToOct.get(i).getNum() + oct;
			}
			return oct;
		}
		return failedConvertMsg;
	}

	public String toDec() {
		return method.toDec();
	}
	
	public String toHex() {
		if (isTrue(initialValue)) {
			String hex = "";
			for (int i = 0; i < methodToHex.size(); i++) {
				hex = methodToHex.get(i).getNum() + hex;
			}
			return hex.toUpperCase();
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
				System.out.println(str.charAt(i) + " is not a Binary number");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isDigit(char ch) {
		return (ch >= '0' && ch <= '1');
	}
}
