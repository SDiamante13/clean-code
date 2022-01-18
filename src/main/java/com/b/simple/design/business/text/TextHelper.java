package com.b.simple.design.business.text;

public class TextHelper {

	public String swapLastTwoCharacters(String str) {
		int length = str.length();
		if (length < 2) return str;
		char lastChar = str.charAt(length - 1);
		char secondToLastChar = str.charAt(length - 2);

		return str.substring(0, length - 2) + lastChar + secondToLastChar;
	}

	public String truncateAInFirst2Positions(String str) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			if (i >= 2 || str.charAt(i) != 'A') {
				stringBuilder.append(str.charAt(i));
			}
		}
		return stringBuilder.toString();
	}
}
