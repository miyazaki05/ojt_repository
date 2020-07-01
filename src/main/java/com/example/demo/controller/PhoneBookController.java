package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.InputedForm;
import com.example.demo.form.SearchForm;
import com.example.demo.service.DeleteLogic;
import com.example.demo.service.RegistLogic;
import com.example.demo.service.SearchLogic;
import com.example.demo.service.UpdateLogic;

@Controller
public class PhoneBookController {
	@Autowired
	private SearchLogic search;
	//検索ボタンを押下したか否かのフラグ
	boolean isClicked = false;
//	更新処理を行ったかのフラグ
	boolean notUpdate = true;
//	更新画面を経由したかどうかのフラグ
	boolean throughUpdate = false;
	/**トップページを表示*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView searchInit(ModelAndView mav) {

		search(new SearchForm(), mav);


			isClicked = false;
			notUpdate = true;
			throughUpdate = false;
		mav.addObject("isClicked", isClicked);
		return mav;
	}

	/**検索ロジックを呼び出して検索ページへ遷移*/
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(SearchForm input, ModelAndView mav) {

		isClicked = true;
		search.execute(input, mav,isClicked);
		return mav;
	}

//	@RequestMapping(value = "/back",method = RequestMethod.POST)
//	public void updateSupport(@RequestParam(value = "pageNum", required = true) int pageNum,ModelAndView mav,@RequestParam(value = "updateName", required = true) String name) {
//		SearchForm input = new SearchForm();
//		input.setKeyword(name);
//		search.nextpaging(pageNum, mav,input,isClicked, passUpdate);
//	}
	/**次のページへ*/
	@RequestMapping(value = "/searchnext", method = RequestMethod.POST)
	public ModelAndView nextpaging(ModelAndView mav, @RequestParam(value = "pageNum", required = true) int pageNum,
			SearchForm input) {

		if(!notUpdate) {
			searchInit(mav);
		}


		search.nextpaging(pageNum, mav,input,isClicked,throughUpdate,notUpdate);
		return mav;
	}

	/**前のページへ*/
	@RequestMapping(value = "/searchprevious", method = RequestMethod.POST)
	public ModelAndView previouspaging(ModelAndView mav,
			@RequestParam(value = "pageNum", required = true) int pageNum,SearchForm input) {


		search.previouspaging(pageNum, mav,input,isClicked);

		return mav;
	}

	@Autowired
	private RegistLogic regist;

	/**登録画面を表示*/
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public ModelAndView registInit(ModelAndView mav) {
		return regist(new InputedForm(), mav);

	}

	/**登録処理を行う*/
	@RequestMapping(value = "/registnew", method = RequestMethod.POST)
	public ModelAndView regist(InputedForm input, ModelAndView mav) {
		regist.execute(input, mav);
		return mav;
	}

	@Autowired
	private UpdateLogic update;

	/**更新画面を表示*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateInit(ModelAndView mav, @RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "phoneNumber", required = true) String phoneNumber,
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "pageNum",required = true) int pageNum) {

		int adjustPageNum = pageNum-1;
		mav.addObject("pageNum",adjustPageNum);
		mav.addObject("id", id);
		mav.addObject("name", name);
		mav.addObject("phoneNumber", phoneNumber);
		throughUpdate = true;
		return update(new InputedForm(), mav,pageNum);

	}

	/**更新処理を行う
	 * @param pageNum */
	@RequestMapping(value = "/updatenew", method = RequestMethod.POST)
	public ModelAndView update(InputedForm input, ModelAndView mav, int pageNum) {
		update.execute(input, mav,pageNum,isClicked);
		notUpdate = update.judgeUpdate();

		return mav;
	}

	@Autowired
	private DeleteLogic delete;

	/**削除処理を行う*/
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletectl(ModelAndView mav, @RequestParam(value = "id", required = true) int id) {

		delete.execute(id);

		return searchInit(mav);

	}
}
