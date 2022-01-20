package com.d.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class StringHelperTest {
	//"", "A", "AA", "B", "BC"
	StringHelper helper = new StringHelper();

	@ParameterizedTest
	@CsvSource(value = {
			"ABCD,BCD",
			"AACD,CD",
			"BACD,BCD",
			"AAAA,AA",
			"MNAA,MNAA",
			"'',''",
			"A,''",
			"AA,''",
			"B,B",
			"BC,BC",
	})
	void testReplaceAInFirst2Positions(String input, String expected) {
		assertEquals(expected, helper.replaceAInFirst2Positions(input));
	}
	
	//""=>false, "A"=>false, "AB"=>true, "ABC"=>false, "AAA"=>true, "ABCAB"=>true, "ABCDEBA"=>false
	//Red
	//Green
	//Refactor

	@ParameterizedTest
	@MethodSource("provideArguments")
	void testAreFirstTwoAndLastTwoCharsTheSame(boolean expected, String input) {
		assertEquals(expected, helper.areFirstTwoAndLastTwoCharsTheSame(input));
	}

	private static Stream<Arguments> provideArguments() {
		return Stream.of(
				Arguments.of(false, ""),
				Arguments.of(false, "A"),
				Arguments.of(true, "AB"),
				Arguments.of(false, "ABC"),
				Arguments.of(true, "AAA"),
				Arguments.of(true, "ABCAB"),
				Arguments.of(false, "ABCDEBA")
		);
	}

}
