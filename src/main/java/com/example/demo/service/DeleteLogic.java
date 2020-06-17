package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PhoneBookRepository;

@Service
public class DeleteLogic {
	@Autowired
	PhoneBookRepository phonebookrepository;

	public void execute(int id) {
		phonebookrepository.delete(id);
	}
}
