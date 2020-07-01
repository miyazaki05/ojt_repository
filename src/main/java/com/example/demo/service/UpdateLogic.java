package com.example.demo.service;

import javax.servlet.http.HttpSession;

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
	@Autowired
	private HttpSession session;

	boolean notUpdate = true;//更新処理がかかったか否か

	/**更新処理を行う
	 * @param input
	 * @param mav
	 * @param pageNum
	 * @param passUpdate
	 */
	public void execute(InputedForm input, ModelAndView mav, int pageNum, boolean isClicked) {

		String name = input.getName();
		String phoneNumber = input.getPhoneNumber();
		int id = input.getId();

		//初期表示の際はフィールドがnullになるため、処理を終わらせる
		if (name == null || phoneNumber == null) {
			mav.setViewName("update");
			return;
		}

		//更新画面での入力チェック
		boolean isCorrectOfName = ValidationUtility.isCorrentName(name,mav);
		boolean isCorrectOfPhoneNumber = ValidationUtility.isCorrentPhoneNumber(phoneNumber,mav);
		if (!isCorrectOfName || !isCorrectOfPhoneNumber) {

			mav.addObject("notUpdate",notUpdate);
			mav.addObject("pageNum",pageNum);
			mav.addObject("id", id);
			mav.addObject("name", name);
			mav.addObject("phoneNumber", phoneNumber);
//			SearchForm updateName = new SearchForm();
//			updateName.setKeyword(name);
//			mav.addObject("updateName",name);
			mav.setViewName("update");
			session.setAttribute("Keyword", name);
			return;
		}


		mav.addObject("nameMessage", Message.SUCCESS_UPDATE);
//		passUpdate = true;
		phoneBookRepository.update(name, phoneNumber, id);
		notUpdate = false;

//		SearchForm updateName = new SearchForm();
//		updateName.setKeyword(name);
		searchLogic.execute(new SearchForm(), mav, false);

//		mav.addObject("updateName",name);
		if(!isClicked) {
			mav.addObject("pageNum",pageNum);
		}else {
			mav.addObject("pageNum",0);
		}
		mav.addObject("notUpdate",notUpdate);
		mav.addObject("id", id);
		mav.addObject("name", name);
		mav.addObject("phoneNumber", phoneNumber);
		mav.setViewName("update");

	}

	public boolean judgeUpdate() {
		return notUpdate;
	}
}
