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

	/**トップページを表示*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView searchInit(ModelAndView mav) {
		return search(new SearchForm(), mav);
	}

	/**検索ロジックを呼び出して検索ページへ遷移*/
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(SearchForm input, ModelAndView mav) {

		search.execute(input, mav);

		return mav;
	}

	@Autowired
	private RegistLogic regist;

	/**登録画面を表示*/
	@RequestMapping(value = "/regist", method = RequestMethod.GET)

	public ModelAndView registInit(ModelAndView mav) {
		return regist(new InputedForm(),mav);

	}

	@RequestMapping(value = "/registnew", method = RequestMethod.POST)

	public ModelAndView regist(InputedForm input, ModelAndView mav) {
		regist.execute(input,mav);
		return mav;
	}

	@Autowired
	private UpdateLogic update;

	/**更新画面を表示*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateInit(ModelAndView mav, @RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "phoneNumber", required = true) String phoneNumber,
			@RequestParam(value = "id", required = true) int id) {

		mav.addObject("id", id);
		mav.addObject("name", name);
		mav.addObject("phoneNumber", phoneNumber);

		return update(new InputedForm(), mav);

	}

	@RequestMapping(value = "/updatenew", method = RequestMethod.POST)

	public ModelAndView update(InputedForm input, ModelAndView mav) {

		update.execute(input, mav);
		return mav;
	}

	@Autowired
	private DeleteLogic delete;

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletectl(ModelAndView mav, @RequestParam(value = "id", required = true) int id) {

		delete.execute(id);

		return search(new SearchForm(), mav);

	}
}
