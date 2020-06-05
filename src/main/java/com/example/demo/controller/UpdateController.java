package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.RegistLogic;

@Controller
public class UpdateController {
//	@Autowired
	private RegistLogic update;

	/**更新画面を表示*/
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		return  mav;
	}
}