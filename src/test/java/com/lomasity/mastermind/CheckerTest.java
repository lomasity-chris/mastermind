package com.lomasity.mastermind;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckerTest {

	private static final String CODE = "00211";
	private Checker checker;
	
	@Before
	public void setup() {
		checker = new Checker();
	}

	@Test(expected = IllegalArgumentException.class)
	public void actualCodeNull() {
		String feedback = checker.check(null,"99999");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void actualCodeTooShort() {
		String feedback = checker.check("1234","99999");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void actualCodeTooLong() {
		String feedback = checker.check("123456","99999");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void guessNull() {
		String feedback = checker.check(CODE, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void guessTooShort() {
		String feedback = checker.check(CODE,"1234");
	}

	@Test(expected = IllegalArgumentException.class)
	public void guessTooLong() {
		String feedback = checker.check(CODE, "123456");
	}
	

	
	@Test
	public void allWrongReturnsNoPegs() {
		String feedback = checker.check(CODE,"99999");
		assertThat(feedback, equalTo(""));
	}
	
	@Test
	public void twoExactReturnsRR() {
		String feedback = checker.check(CODE,"99911");
		assertThat(feedback, equalTo("RR"));
	}
	
	@Test
	public void twoInWrongPlaceReturnsBB() {
		String feedback = checker.check(CODE,"99900");
		assertThat(feedback, equalTo("BB"));
	}
	
	@Test
	public void wrongPlacesIgnoredWhenDigitsAlreadyMatchedByExact() {
		String feedback = checker.check(CODE,"11911");
		assertThat(feedback, equalTo("RR"));
	}
	
	@Test
	public void allCorrectReturnsRRRRR() {
		String feedback = checker.check(CODE,"00211");
		assertThat(feedback, equalTo("RRRRR"));
	}

}
