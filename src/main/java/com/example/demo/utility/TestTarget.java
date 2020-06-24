package com.example.demo.utility;

import java.util.regex.Pattern;

/**
 *入力された名前と電話番号にエラーがないかをチェックするクラス
 */
public class TestTarget {
	/**
	 * 入力された名前と電話番号をチェックするメソッド
	 * @param inputName 入力された名前
	 * @param inputPhoneNumber 入力された電話番号
	 * @param mav
	 * @param phoneBookList
	 * @return エラーありならtrue、なしならfalse
	 * */
	public static boolean isValid(String inputName, String inputPhoneNumber) {

		boolean isValidInputed = true;

		if (inputName.equals("") && inputPhoneNumber.equals("")) {

			return !isValidInputed;
		}

		if (inputName.length() > 20 && !(inputPhoneNumber.length() == 10 || inputPhoneNumber.length() == 11)) {

			return !isValidInputed;
		}

		if (inputName.equals("")) {

			return !isValidInputed;
		} else if (inputName.length() > 20) {

			return !isValidInputed;
		}

		if (inputPhoneNumber.equals("")) {

			return !isValidInputed;
		} else if (!(inputPhoneNumber.length() == 10 || inputPhoneNumber.length() == 11)) {

			return !isValidInputed;
		} else if (!Pattern.matches("^[0-9]*$", inputPhoneNumber)) {

			return !isValidInputed;
		}

		return isValidInputed;
	}

}