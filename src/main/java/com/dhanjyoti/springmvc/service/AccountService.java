package com.dhanjyoti.springmvc.service;

import java.util.List;

import com.dhanjyoti.springmvc.model.Account;
import com.dhanjyoti.springmvc.model.ServiceRequest;
import com.dhanjyoti.springmvc.model.Transaction;


public interface AccountService {
	
	void saveAccount(Account account, String loggedinUser);
	
	Account getAccount(String loggedinUser);
	
	void saveServiceRequest(ServiceRequest serviceRequest, String loggedinUser);
	
	List<Transaction> getTransactionDetails(Account account);
	
	void saveTermAccount(Account account, String loggedinUser);
	
	Account findAccountByAcctId(Integer accId);
	
}