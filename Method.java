package io.alialaware.caliala.core;

import java.util.ArrayList;
import java.util.List;

public class Method {
	private int basis;
	private String initialValue;
	private String failedConvertMsg;
	private NumberKind nK;
	private List<MethodItem> methodToDec;
	
	public Method(int basis, String initialValue, String failedConvertMsg, NumberKind nK) {
		this.basis = basis;
		this.initialValue = initialValue;
		this.failedConvertMsg = failedConvertMsg;
		this.nK = nK;
		
		methodToDec = hitungToDec();
	}
	
	public List<MethodItem> getMethodToDec() {
		return methodToDec;
	}
	
	public String getSum() {
		String sum = "";
		for (int i = 0; i < methodToDec.size(); i++) {
			sum += methodToDec.get(i).getNum();
			if(i != methodToDec.size() -1)
				sum += " + ";
		}
		sum += " = " + toDec();
		return sum;
	}
	
	private String getHasilPangkat(int basis, int pos) {
		return Digit.getDecPoint(String.valueOf(Digit.getPangkatLong(
				basis, pos)));
	}
	
	private String getHasilKali(char digit, int basis, int pos) {
		return Digit.getDecPoint(String.valueOf(Digit.getNilaiInPos(
				digit, basis, pos)));
	}
	
	private String getHowToDec(char digit, int basis, int pos) {
		String str = digit + " = " + Digit.toInt(digit) + " x "
				+ basis + Digit.getPangkat(pos) + " = ";
		if (digit == '0') {
			str += 0;
		}else {
			str +=  + Digit.toInt(digit)
			+ " x " + getHasilPangkat(basis, pos) + " = ";
		}
		return str;
	}
	
	private List<MethodItem> hitungToDec() {
		MethodItem methodItem = new MethodItem(failedConvertMsg, failedConvertMsg);;
		List<MethodItem> methodList = new ArrayList<MethodItem>();
		if(nK.isTrue(initialValue)) {
			int pos = 0;
			String how;
			String multi;
			for (int i = initialValue.length() - 1; i >= 0; i--) {
				how = getHowToDec(initialValue.charAt(i), basis, pos);
				multi = getHasilKali(initialValue.charAt(i), basis, pos);
				methodItem = new MethodItem(how, multi);
				methodList.add(methodItem);
				pos++;
			}
		}else {
			methodList.add(methodItem);
		}
		return methodList;
	}
	
	public String toDec() {
		if (nK.isTrue(initialValue)) {
			long dec = 0;
			for (int i = 0; i < methodToDec.size(); i++) {
				dec += Long.parseLong(methodToDec.get(i).getNum().replaceAll(",", ""));
			}
			return String.valueOf(dec);
		}
		return failedConvertMsg;
	}
}
