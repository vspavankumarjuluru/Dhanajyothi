package com.dhanjyoti.springmvc.dao;

import com.dhanjyoti.springmvc.model.Account;


public interface AccountDao {	
	
	void save(Account account);
	
	Account getAccountByUserName(String username);
	
	Account getAccountByAccountId(int accountId);
	
	void saveOrUpdate(Account account);
}

