package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.PhoneBookRepository;
import com.example.demo.form.InputedForm;
import com.example.demo.utility.InputCheck;
@Service
public class RegistLogic {
	@Autowired
	private PhoneBookRepository phoneBookRepository;

	public void execute(InputedForm input, ModelAndView mav) {
		String name = input.getName();
		String phoneNumber = input.getPhoneNumber();

		if(name == null || phoneNumber == null){
			return;
		}

		if(InputCheck.phoneBookCheck(name,phoneNumber,mav)) {
			mav.addObject("msg", Message.SUCCESS_REGIST);
			phoneBookRepository.regist(name, phoneNumber);
		}

		mav.setViewName("regist");

	}

}
