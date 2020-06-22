package com.example.demo.utility;

import java.util.regex.Pattern;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.Message;

/**
 *入力された名前と電話番号にエラーがないかをチェックするクラス
 */
public class InputCheck {
	/**
	 * 入力された名前と電話番号をチェックするメソッド
	 * @param inputName 入力された名前
	 * @param inputPhoneNumber 入力された電話番号
	 * @param mav
	 * @param phoneBookList
	 * @return エラーありならtrue、なしならfalse
	 * */
	public static boolean phoneBookCheck(String inputName, String inputPhoneNumber, ModelAndView mav) {

		boolean checkFlg = true;

		if (inputName.equals("") && inputPhoneNumber.equals("")) {
			mav.addObject("nameMessage", Message.NAME_EMPTY);
			mav.addObject("numberMessage", Message.PHONENUMBER_EMPTY);
			return !checkFlg;
		}

		if (inputName.equals("")) {
			mav.addObject("nameMessage", Message.NAME_EMPTY);
			return !checkFlg;
		}

		if (inputPhoneNumber.equals("")) {
			mav.addObject("numberMessage", Message.PHONENUMBER_EMPTY);
			return !checkFlg;
		}

		if (inputName.length() > 20 && !(inputPhoneNumber.length() == 10 || inputPhoneNumber.length() == 11)) {
			mav.addObject("nameMessage", Message.NAME_LIMIT);
			mav.addObject("numberMessage", Message.PHONENUMBER_LIMIT);
			return !checkFlg;
		}

		if (inputName.length() > 20) {
			mav.addObject("nameMessage", Message.NAME_LIMIT);
			return !checkFlg;
		}

		if (!(inputPhoneNumber.length() == 10 || inputPhoneNumber.length() == 11)) {
			mav.addObject("numberMessage", Message.PHONENUMBER_LIMIT);
			return !checkFlg;
		}

		if (!Pattern.matches("^[0-9]*$", inputPhoneNumber)) {
			mav.addObject("numberMessage", Message.SIZE_CHECK);
			return !checkFlg;
		}

		return checkFlg;
	}

}