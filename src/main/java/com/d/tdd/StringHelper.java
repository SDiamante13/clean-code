package com.d.tdd;

public class StringHelper {

	public String replaceAInFirst2Positions(String str) {
		String letterToBeReplaced = "A";
		if (str.length() < 2) return str.replace(letterToBeReplaced, "");
		String firstTwoChars = str.substring(0, 2);
		return firstTwoChars.replace(letterToBeReplaced, "") + str.substring(2);
	}

	public boolean areFirstTwoAndLastTwoCharsTheSame(String str) {
		if (str.length() < 2) return false;
		String firstTwo = str.substring(0, 2);
		String lastTwo = str.substring(str.length() - 2);
		return firstTwo.equals(lastTwo);
	}

}
