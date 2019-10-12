package com.lomasity.mastermind;

public class Checker {

	private static final char USED = ' ';
	private static final int CODE_LENGTH = 5;
	private static final String RED_PEG = "R";
	private static final String BLACK_PEG = "B";

	public String check(final String actualCode, final String guess) {

		StringBuilder feedback = new StringBuilder();

		validateInput(actualCode);
		validateInput(guess);

		char[] workingElements = split(actualCode);
		char[] guessElements = split(guess);

		// Check for exact matches
		for (int i = 0; i < CODE_LENGTH; i++) {
			if (guessElements[i] != USED && guessElements[i] == workingElements[i]) {
				feedback.append(RED_PEG);
				guessElements[i] = USED;
				workingElements[i] = USED;
			}
		}

		// Check for inexact matches
		for (int i = 0; i < CODE_LENGTH; i++) {
			for (int j = 0; j < CODE_LENGTH; j++) {
				if (guessElements[i] != USED && guessElements[i] == workingElements[j]) {
					feedback.append(BLACK_PEG);
					guessElements[i] = USED;
					workingElements[j] = USED;
				}
			}
		}

		return feedback.toString();
	}

	private void validateInput(final String code) {
		if (code == null || code.length() != CODE_LENGTH) {
			throw new IllegalArgumentException("Each input must be a string of length 5");
		}
		if (code.contains(" ")) {
			throw new IllegalArgumentException("Inputs must not contain spaces");
		}
	}

	private char[] split(final String code) {

		char[] elements = new char[CODE_LENGTH];
		for (int i = 0; i < CODE_LENGTH; i++) {
			elements[i] = code.charAt(i);
		}
		return elements;
	}
}
