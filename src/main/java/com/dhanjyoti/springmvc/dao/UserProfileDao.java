package com.dhanjyoti.springmvc.dao;

import java.util.List;

import com.dhanjyoti.springmvc.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
