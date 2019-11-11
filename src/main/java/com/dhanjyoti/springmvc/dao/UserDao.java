package com.dhanjyoti.springmvc.dao;

import java.util.List;

import com.dhanjyoti.springmvc.model.User;
import com.dhanjyoti.springmvc.model.Users;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<Users> findAllUsers();
	
	void save(Users user);
	
	//Users getUsersById(int id);

}

