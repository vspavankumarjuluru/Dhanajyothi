package com.dhanjyoti.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhanjyoti.springmvc.dao.UserDao;
import com.dhanjyoti.springmvc.model.Users;


@Service
@Transactional
public class RegisterServiceImpl implements RegisterService{

	@Autowired
	private UserDao dao; 
	
	@Autowired
    private PasswordEncoder passwordEncoder;	

	public void saveUser(Users user) {		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserstatus("P"); // user is not approved by manager - P
		user.setDob(new java.sql.Date(user.getDob().getTime()));
		user.setUserrole("C"); // will be changed manually if user wants admin access
		dao.save(user);
	}
	
	
}
