package com.example.demo;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.utility.ValidationUtility;

@SpringBootTest
class DemoApplicationTests {

	ModelAndView mav = new ModelAndView();

	@Test
	void validationUtility() {
	}


	@Test
	public void isNameCorrectTest() {
		assertThat(ValidationUtility.isCorrentName("",mav),is(false));
		assertThat(ValidationUtility.isCorrentName("あいうえおかきくけこさしすせそたちつてと",mav),is(true));
		assertThat(ValidationUtility.isCorrentName("あいうえおかきくけこさしすせそたちつてとさ",mav),is(false));
	}

	@Test
	public void isPhoneNumberCorrectTest() {
		assertThat(ValidationUtility.isCorrentPhoneNumber("", mav),is(false));
		assertThat(ValidationUtility.isCorrentPhoneNumber("0000000000", mav),is(true));
		assertThat(ValidationUtility.isCorrentPhoneNumber("00000000000", mav),is(true));
		assertThat(ValidationUtility.isCorrentPhoneNumber("000000000000", mav),is(false));
		assertThat(ValidationUtility.isCorrentPhoneNumber("０００００００００", mav),is(false));
		assertThat(ValidationUtility.isCorrentPhoneNumber("００００００００００", mav),is(false));

	}
}
