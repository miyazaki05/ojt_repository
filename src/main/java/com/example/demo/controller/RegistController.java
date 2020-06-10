package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.InputedForm;
import com.example.demo.service.RegistLogic;

@Controller
public class RegistController {
	@Autowired
	private RegistLogic regist;

	/**登録画面を表示*/
	@RequestMapping(value = "/regist", method = RequestMethod.GET)

	public ModelAndView index(ModelAndView mav) {
		return regist(new InputedForm(),mav);
	}

	@RequestMapping(value = "/regist/new", method = RequestMethod.POST)

	public ModelAndView regist(InputedForm input, ModelAndView mav) {
		regist.execute(input,mav);
		return mav;
	}
}