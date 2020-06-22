package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.form.InputedForm;
import com.example.demo.utility.InputCheck;

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

		//登録画面初期表示の際はフィールドがnullのため、処理を終わらせる
		if (name == null || phoneNumber == null) {
			return;
		}

		//登録画面の入力チェック
		if (InputCheck.isValid(name, phoneNumber, mav)) {
			mav.addObject("nameMessage", Message.SUCCESS_REGIST);
			phoneBookRepository.regist(name, phoneNumber);
		}

		mav.setViewName("regist");

	}

}
