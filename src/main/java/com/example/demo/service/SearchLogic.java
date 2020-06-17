package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.entity.PhoneBookEntity;
import com.example.demo.form.SearchForm;
import com.example.demo.form.SearchResultForm;

/**
 * 検索クラス
 */
@Service
public class SearchLogic {
	@Autowired
	private HttpSession session;
	@Autowired
	private PhoneBookRepository phoneBookRepository;

	/**入力された名前と電話帳リストにある名前を比較して合致するものをListに格納するメソッド*/
	public void execute(SearchForm input, ModelAndView mav) {
		List<PhoneBookEntity> phoneBookList = null;
		String keyword = input.getKeyword(); //入力された名前を取得
		if (!SearchLogic.keywordCheck(keyword, mav)) {
			return;
		}
		List<SearchResultForm> searchList = new ArrayList<>();
		if (keyword == null) {
			phoneBookList = phoneBookRepository.findAll();
		} else if (keyword.equals("")) {
			phoneBookList = phoneBookRepository.findAll();
		} else if (!keyword.equals("")) {
			phoneBookList = phoneBookRepository.findResult(keyword);
		}
		session.setAttribute("phoneBookList", phoneBookList);
		if (phoneBookList != null) {
			for (int i = 0; i < phoneBookList.size(); i++) {
				PhoneBookEntity entity = phoneBookList.get(i);
				SearchResultForm sf = new SearchResultForm();
				sf.setId(entity.getId());
				sf.setName(entity.getName());
				sf.setPhoneNumber(entity.getPhoneNumber());
				searchList.add(sf);
			}
		}
		mav.addObject("searchList", searchList);
		mav.setViewName("search");
		SearchLogic.searchMsg(searchList, keyword, mav);
	}

	private static void searchMsg(List<SearchResultForm> searchList, String inputName, ModelAndView mav) {
		if (inputName == null) {
			return;
		}

		if (inputName.equals("")) {
			mav.addObject("msg", Message.SEARCH_EMPTY);
		} else if (searchList.size() == 0) {
			mav.addObject("msg", Message.SEARCH_NOT_HIT);
		} else {
			mav.addObject("msg", searchList.size() + Message.SEARCH_HIT_COUNT);
		}
	}

	private static boolean keywordCheck(String keyword, ModelAndView mav) {
		if (keyword != null) {
			boolean flg = true;

			if (keyword.length() > 20) {
				mav.addObject("msg", Message.NAME_LIMIT);
				flg = false;
			}
			return flg;
		} else {
			return true;
		}
	}
}