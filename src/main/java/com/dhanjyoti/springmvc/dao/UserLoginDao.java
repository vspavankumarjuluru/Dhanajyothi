package com.dhanjyoti.springmvc.dao;

import java.util.List;

import com.dhanjyoti.springmvc.model.Users;


public interface UserLoginDao {
	
	List<Users> findAllUsers();
	
	void save(Users user);
	
	Users getUsersById(int id);
	
	void deleteById(int id);
	
	Users findByUsername(String sso);	
	

}

