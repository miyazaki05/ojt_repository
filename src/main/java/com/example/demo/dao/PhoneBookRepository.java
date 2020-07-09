package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PhoneBookEntity;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBookEntity, Long> {

	/**検索SQL*/
	@Query(value = "SELECT p.account_id, p.name, p.phone_number, p.address FROM phonebook p ORDER BY p.account_id", nativeQuery = true)
	public List<PhoneBookEntity> findAll();

	@Query(value = "SELECT p.account_id, p.name, p.phone_number , p.address FROM phonebook p where p.name = :keyword ORDER BY p.account_id", nativeQuery = true)
	public List<PhoneBookEntity> findByKeyword(@Param("keyword") String keyword);

	@Query(value = "SELECT p.account_id, p.name, p.phone_number , p.address FROM phonebook p where p.name = :keyword AND p.address = :address ORDER BY p.account_id", nativeQuery = true)
	public List<PhoneBookEntity> findResult(@Param("keyword") String keyword,@Param("address")  String address);

	@Query(value = "SELECT p.account_id, p.name, p.phone_number , p.address FROM phonebook p where p.address = :address ORDER BY p.account_id", nativeQuery = true)
	public List<PhoneBookEntity> findByAddress(@Param("address") String address);

	/**削除SQL*/
	@Modifying
	@Transactional
	@Query(value = "DELETE from phonebook WHERE account_id = :id", nativeQuery = true)
	public void delete(@Param("id") int id);

	/**登録SQL*/
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO phonebook (name,phone_number,address) VALUES (:name,:phoneNumber,:address)", nativeQuery = true)
	public void regist(@Param("name") String name, @Param("phoneNumber") String phoneNumber,@Param("address") String address);

	/**更新SQL*/
	@Modifying
	@Transactional
	@Query(value = "UPDATE phonebook SET name = :name, phone_number = :phoneNumber WHERE account_id = :id", nativeQuery = true)
	public void update(@Param("name") String name, @Param("phoneNumber") String phoneNumber, @Param("id") int id);




}