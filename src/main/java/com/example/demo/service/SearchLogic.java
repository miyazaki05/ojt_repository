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

	static int IDCOUNTER = 0;

	/**入力された名前と電話帳リストにある名前を比較して合致するものをListに格納するメソッド
	 * @param input
	 * @param mav
	 * */
	public void execute(SearchForm input, ModelAndView mav) {

		List<PhoneBookEntity> phoneBookList = null;
		//入力された名前を取得
		String keyword = input.getKeyword();

		//検索結果表示画面に検索キーワードを表示する
		if (keyword != null && keyword != "" && !keywordCheck(keyword)) {
			mav.addObject("searchkeyword", keyword + Message.SEARCH_KEYWORD);
		}
		List<SearchResultForm> searchList = new ArrayList<>();
		if (keyword == null) {
			phoneBookList = phoneBookRepository.findAll();
		} else if (("").equals(keyword)) {
			phoneBookList = phoneBookRepository.findAll();
		} else {
			phoneBookList = phoneBookRepository.findResult(keyword);
		}

		session.setAttribute("phoneBookList", phoneBookList);

		int idCounter = 0;//画面に表示するデータNoのための変数
		int pageNum = 0;
		if (phoneBookList != null && !phoneBookList.isEmpty()) {
			for (int i = 0; i < 15; i++) {
				if (phoneBookList.size() < 15) {
					break;
				}
				idCounter++;
				PhoneBookEntity entity = phoneBookList.get(i);
				SearchResultForm sf = new SearchResultForm();
				sf.setResultId(idCounter);
				sf.setId(entity.getId());
				sf.setName(entity.getName());
				sf.setPhoneNumber(entity.getPhoneNumber());
				searchList.add(sf);
			}
		}

		mav.addObject("searchList", searchList);
		pageNum++;
		mav.addObject("pageNum", pageNum);
		session.setAttribute("list_page" + pageNum, searchList);
		SearchLogic.searchMsg(phoneBookList, keyword, mav);
		mav.setViewName("search");

	}

	/**「次へ」ボタン押下時の処理
	 * @param pageNum
	 * @param mav
	 */
	public void nextpaging(int pageNum, ModelAndView mav) {

		List<PhoneBookEntity> phoneBookList = (List<PhoneBookEntity>) session.getAttribute("phoneBookList");

		//最終ページで「次へ」ボタンを押下した際は常に最終ページを表示するようにする

		if (phoneBookList.size() - (15 * pageNum) > 0 && phoneBookList != null) {
			pageNum++;
		}

		List<SearchResultForm> searchList = new ArrayList<>();
		int firstDataIndex = 15 * (pageNum - 1);//各ページの最初のデータのインデックスを格納する変数
		int tryCnt = firstDataIndex + 15;//試行回数、1ページ当たりのデータ件数が15件なので+15
		int idCounter = pageNum * 15 - 15;//ページ番号に応じてそのページの最初のデータのNoに対応させる
		if (phoneBookList != null) {

			for (int i = firstDataIndex; i < tryCnt; i++) {
				if (i == phoneBookList.size()) {
					break;
				}
				idCounter++;
				PhoneBookEntity entity = phoneBookList.get(i);
				SearchResultForm sf = new SearchResultForm();
				sf.setResultId(idCounter);
				sf.setId(entity.getId());
				sf.setName(entity.getName());
				sf.setPhoneNumber(entity.getPhoneNumber());
				searchList.add(sf);
			}
		}

		mav.addObject("searchList", searchList);

		mav.addObject("pageNum", pageNum);
		session.setAttribute("list_page" + pageNum, searchList);
		mav.setViewName("search");
	}

	/**「前へ」ボタン押下時の処理
	 * @param pageNum
	 * @param mav
	 */
	public void previouspaging(int pageNum, ModelAndView mav) {

		int previousPage = pageNum - 1;

		//最初のページでの「前へ」ボタン押下時は常に最初のページを表示させる
		if (previousPage < 1) {
			previousPage = 1;
		}

		//セッションから指定されたページ番号のリストを取得
		mav.addObject("searchList", (List<SearchResultForm>) session.getAttribute("list_page" + previousPage));

		mav.addObject("pageNum", previousPage);

		mav.setViewName("search");

	}

	/**画面に表示するメッセージをセットする
	 * @param phoneBookList
	 * @param inputName
	 * @param mav
	 */
	private static void searchMsg(List<PhoneBookEntity> phoneBookList, String inputName, ModelAndView mav) {
		if (inputName == null) {
			return;
		}

		if (inputName.equals("")) {
			mav.addObject("msg", Message.SEARCH_EMPTY);
		} else if (keywordCheck(inputName)) {//入力チェック
			mav.addObject("msg", Message.NAME_LIMIT);
		}else if(phoneBookList.size() == 0) {
			mav.addObject("msg", Message.SEARCH_NOT_HIT);
		} else {
			mav.addObject("msg", phoneBookList.size() + Message.SEARCH_HIT_COUNT);
		}
	}

	/**検索画面における入力チェックを行う
	 * @param keyword
	 */
	private static boolean keywordCheck(String keyword) {

		return keyword.length() > 20;

	}
}