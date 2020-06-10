package com.example.demo.utility;

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

		if(inputName.equals("") || inputPhoneNumber.equals("")) {
			mav.addObject("hikitsugiParam", Message.INPUT_EMPTY);
			checkFlg = false;
		}

		if(inputName.length() > 21 || inputPhoneNumber.length() > 11) {
			mav.addObject("hikitsugiParam", Message.INPUT_LIMIT);
			checkFlg = false;
		}
		return checkFlg;
	}
}