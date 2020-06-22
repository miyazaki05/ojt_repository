package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PhoneBookRepository;

@Service
public class DeleteLogic {
	@Autowired
	PhoneBookRepository phonebookrepository;

	/**渡されたidに従ってデータを削除する
	 * @param id
	 */
	public void execute(int id) {
		phonebookrepository.delete(id);
	}
}
