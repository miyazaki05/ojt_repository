package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.form.InputedForm;
import com.example.demo.utility.ValidationUtility;

@Service
public class RegistLogic {
	@Autowired
	private PhoneBookRepository phoneBookRepository;

	/**登録処理を行う
	 * @param input
	 * @param mav
	 */
	public void execute(InputedForm input, ModelAndView mav) {
		String name = input.getName();
		String phoneNumber = input.getPhoneNumber();
		String address = input.getAddress();

		//住所を選択なしで選択した場合は「住所不明」で登録する
		if("選択なし".equals(address)) {
			address = "住所不明";
		}

		//登録画面初期表示の際はフィールドがnullのため、処理を終わらせる
		if (name == null || phoneNumber == null) {
			mav.setViewName("regist");
			return;
		}

		boolean isCorrectOfName = ValidationUtility.isCorrentName(name,mav);
		boolean isCorrectOfPhoneNumber = ValidationUtility.isCorrentPhoneNumber(phoneNumber,mav);
		//登録画面の入力チェック
		if (!isCorrectOfName || !isCorrectOfPhoneNumber) {
			mav.setViewName("regist");
			return;
		}


		phoneBookRepository.regist(name, phoneNumber,address);
		mav.addObject("nameMessage", Message.SUCCESS_REGIST);

		mav.setViewName("regist");

	}

}
