package com.example.demo.form;

public class SearchForm {
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**検索キーワード*/
	private String keyword;
	/**住所*/
	private String address;
}
