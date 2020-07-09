package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.form.InputedForm;
import com.example.demo.form.SearchForm;
import com.example.demo.utility.ValidationUtility;

@Service
public class UpdateLogic {

	@Autowired
	private PhoneBookRepository phoneBookRepository;
	@Autowired
	private SearchLogic searchLogic;

	/**更新処理を行う
	 * @param input
	 * @param mav
	 * @param pageNum
	 */
	public void execute(InputedForm input, ModelAndView mav, int pageNum) {

		String name = input.getName();
		String phoneNumber = input.getPhoneNumber();
		String address = input.getAddress();
		int id = input.getId();

		//住所不明での更新は「選択なし」として更新する
		if("住所不明".equals(address)) {
			address = "選択なし";
		}

		//初期表示の際はフィールドがnullになるため、処理を終わらせる
		if (name == null || phoneNumber == null) {
			mav.setViewName("update");
			return;
		}

		//更新画面での入力チェック
		boolean isCorrectOfName = ValidationUtility.isCorrentName(name,mav);
		boolean isCorrectOfPhoneNumber = ValidationUtility.isCorrentPhoneNumber(phoneNumber,mav);
		if (!isCorrectOfName || !isCorrectOfPhoneNumber) {
			mav.addObject("pageNum",pageNum);
			mav.addObject("id", id);
			mav.addObject("name", name);
			mav.addObject("phoneNumber", phoneNumber);
			SearchForm updateName = new SearchForm();
			updateName.setKeyword(name);
			mav.addObject("updateName",updateName);
			mav.setViewName("update");
			mav.addObject("address",address);
			return;
		}


		mav.addObject("nameMessage", Message.SUCCESS_UPDATE);
		phoneBookRepository.update(name, phoneNumber, id,address);

		searchLogic.execute(new SearchForm(), mav, false);

		mav.addObject("pageNum",pageNum);
		mav.addObject("id", id);
		mav.addObject("name", name);
		mav.addObject("phoneNumber", phoneNumber);
		mav.setViewName("update");
		mav.addObject("address",address);

	}
}
