package com.dhanjyoti.springmvc.service;

import java.util.List;

import com.dhanjyoti.springmvc.model.KycDocument;
import com.dhanjyoti.springmvc.model.User;
import com.dhanjyoti.springmvc.model.Users;


public interface UserService {
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<Users> findAllUsers();
	
	Users findUsersById(int id);
	
	boolean isUserSSOUnique(Integer id, String sso);
	
	KycDocument getDocument(Integer id);

}