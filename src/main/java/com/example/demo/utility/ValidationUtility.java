package com.example.demo.utility;

import java.util.regex.Pattern;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.Message;

/**
 *入力された名前と電話番号にエラーがないかをチェックするクラス
 */
public class ValidationUtility {

	private ValidationUtility() {

	}
	/**
	 * 入力された名前と電話番号をチェックするメソッド
	 * @param inputName 入力された名前
	 * @param inputPhoneNumber 入力された電話番号
	 * @param mav
	 * @param phoneBookList
	 * @return エラーありならtrue、なしならfalse
	 * */
	public static boolean isCorrentName(String inputName,ModelAndView mav) {

		boolean isValidName = true;

		if (inputName.equals("") ) {
			mav.addObject("nameMessage", Message.NAME_EMPTY);
			isValidName = false;
		}

		if (inputName.length() > 20) {
			mav.addObject("nameMessage", Message.NAME_LIMIT);
			isValidName = false;
		}

		return isValidName;
	}

	public static boolean isCorrentPhoneNumber(String inputPhoneNumber, ModelAndView mav) {

		boolean isValidPhoneNumber = true;

		if (inputPhoneNumber.equals("")) {
			mav.addObject("numberMessage", Message.PHONENUMBER_EMPTY);
			isValidPhoneNumber = false;
		}else if (!(inputPhoneNumber.length() == 10 || inputPhoneNumber.length() == 11)) {
			mav.addObject("numberMessage", Message.PHONENUMBER_LIMIT);
			isValidPhoneNumber = false;
		}else if(!Pattern.matches("^[0-9]*$", inputPhoneNumber)){
			mav.addObject("numberMessage", Message.SIZE_CHECK);
			isValidPhoneNumber = false;
		}

		return isValidPhoneNumber;
	}

}