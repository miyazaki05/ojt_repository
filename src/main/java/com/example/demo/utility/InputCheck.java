package com.example.demo.utility;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.PhoneBookEntity;

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
	public static boolean phoneBookCheck(String inputName, String inputPhoneNumber, ModelAndView mav,
			List<PhoneBookEntity> phoneBookList) {
		return true;
	}
}