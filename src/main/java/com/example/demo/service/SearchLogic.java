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
	 * @param isClicked
	 * */
	public void execute(SearchForm input, ModelAndView mav, boolean isClicked) {

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
			//初期表示で表示するデータがない場合はメッセージをセット
			if(phoneBookList.isEmpty()) {
				mav.addObject("searchkeyword",Message.NODATE);
			}
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
				if (phoneBookList.size() == i) {
					break;
				}
				idCounter++;
				PhoneBookEntity entity = phoneBookList.get(i);
				SearchResultForm sf = new SearchResultForm();
				sf.setPageNum(pageNum);
				sf.setResultId(idCounter);
				sf.setId(entity.getId());
				sf.setName(entity.getName());
				sf.setPhoneNumber(entity.getPhoneNumber());
				searchList.add(sf);
			}
		}

		if(keyword != null) {
			mav.addObject("isClicked", isClicked);
		}
		mav.addObject("searchList", searchList);
		//表示するデータがない場合はページ切り替えボタンは非表示にする
		if(!phoneBookList.isEmpty()) {
			pageNum++;
		}
		mav.addObject("isLast", false);
		mav.addObject("pageNum", pageNum);
		mav.addObject("keyword",keyword);
		session.setAttribute("list_page" + pageNum, searchList);
		SearchLogic.searchMsg(phoneBookList, keyword, mav);
		mav.setViewName("search");

	}

	/**「次へ」ボタン押下時の処理
	 * @param pageNum
	 * @param mav
	 * @param input
	 * @param isClicked
	 */
	public void nextpaging(int pageNum, ModelAndView mav, SearchForm input, boolean isClicked) {

		List<PhoneBookEntity> phoneBookList = (List<PhoneBookEntity>) session.getAttribute("phoneBookList");
		String keyword = input.getKeyword();
//		//最終ページで「次へ」ボタンを押下した際は常に最終ページを表示するようにする
//
//		if (phoneBookList.size() - (15 * pageNum) > 0 && phoneBookList != null) {
//			pageNum++;
//		}
		pageNum++;

		boolean isLast = false;
		int lastPage = 0;
		if(phoneBookList.size()%15 == 0) {
			lastPage = phoneBookList.size()/15;
		}else {
			lastPage = phoneBookList.size()/15 + 1;
		}

		if (keyword != null && keyword != "" && !keywordCheck(keyword)) {
			mav.addObject("searchkeyword", keyword + Message.SEARCH_KEYWORD);
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
				sf.setPageNum(pageNum);
				sf.setResultId(idCounter);
				sf.setId(entity.getId());
				sf.setName(entity.getName());
				sf.setPhoneNumber(entity.getPhoneNumber());
				searchList.add(sf);
			}
		}

		mav.addObject("searchList", searchList);
		mav.addObject("keyword",keyword);
		if(lastPage == pageNum) {
			isLast = true;
		}
		mav.addObject("isLast", isLast);
		mav.addObject("pageNum", pageNum);
		session.setAttribute("list_page" + pageNum, searchList);
		if(isClicked) {
			SearchLogic.searchMsg(phoneBookList, keyword, mav);
		}
		mav.addObject("isClicked", isClicked);
		mav.setViewName("search");
	}

	/**「前へ」ボタン押下時の処理
	 * @param pageNum
	 * @param mav
	 * @param isClicked
	 * @param keyword
	 */
	public void previouspaging(int pageNum, ModelAndView mav, SearchForm input, boolean isClicked) {

		int previousPage = pageNum - 1;

		String keyword = input.getKeyword();
		if (keyword != null && keyword != "" && !keywordCheck(keyword)) {
			mav.addObject("searchkeyword", keyword + Message.SEARCH_KEYWORD);
		}

//		//最初のページでの「前へ」ボタン押下時は常に最初のページを表示させる
//		if (previousPage < 1) {
//			previousPage = 1;
//		}

		//セッションから指定されたページ番号のリストを取得
		mav.addObject("searchList", (List<SearchResultForm>) session.getAttribute("list_page" + previousPage));
		List<PhoneBookEntity> phoneBookList = (List<PhoneBookEntity>) session.getAttribute("phoneBookList");
		mav.addObject("pageNum", previousPage);
		mav.addObject("keyword",keyword);
		if(isClicked) {
			SearchLogic.searchMsg(phoneBookList, keyword, mav);
		}
		mav.addObject("isLast", false);
		mav.addObject("isClicked", isClicked);
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