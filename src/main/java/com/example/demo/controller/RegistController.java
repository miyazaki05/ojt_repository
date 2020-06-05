package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.RegistLogic;

@Controller
public class RegistController {
//	@Autowired
	private RegistLogic regist;

	/**登録画面を表示*/
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		return  mav;
	}

//	検索ロジックを呼び出して検索ページへ遷移
//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public ModelAndView search(SearchForm input, ModelAndView mav) {
//		search.execute(input, mav);
//		return mav;
//	}
}