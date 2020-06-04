package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * データ転送オブジェクト
 * */
@Entity
@Table(name = "phonebook")
public class PhoneBookEntity {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private int id;
	/**
	 * 名前
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 電話番号
	 */
	@Column(name = "phone_number")
	private String phoneNumber;
	/**
	 * 削除フラグ
	 */
	@Column(name = "is_delete")
	private boolean isDelete;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
}