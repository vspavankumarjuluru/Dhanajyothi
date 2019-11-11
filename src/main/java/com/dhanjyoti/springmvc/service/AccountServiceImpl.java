package com.dhanjyoti.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhanjyoti.springmvc.dao.AccountDao;
import com.dhanjyoti.springmvc.dao.ServiceRequestDao;
import com.dhanjyoti.springmvc.dao.TransactionDao;
import com.dhanjyoti.springmvc.dao.UserLoginDao;
import com.dhanjyoti.springmvc.model.Account;
import com.dhanjyoti.springmvc.model.ServiceRequest;
import com.dhanjyoti.springmvc.model.Transaction;
import com.dhanjyoti.springmvc.model.Users;


@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private UserLoginDao dao;
	
	@Autowired
	private AccountDao accountdao;
	
	@Autowired
	private ServiceRequestDao serviceRequestdao;
	
	@Autowired
	private TransactionDao transactiondao;

	@Override
	public void saveAccount(Account account, String loggedinUser) {
		// TODO Auto-generated method stub
		//get users with username
		Users user = dao.findByUsername(loggedinUser);
		account.setUser(user);
		account.setAccountCreated(new java.sql.Date(new java.util.Date().getTime()));
		account.setAccountUpdated(new java.sql.Date(new java.util.Date().getTime()));
		account.setIntRate(0.0);
		account.setAccType("S");
		account.setAccountStatus("A");
		accountdao.saveOrUpdate(account);
	}
	
	@Override
	public void saveTermAccount(Account account, String loggedinUser) {
		// TODO Auto-generated method stub
		//get users with username
		Users user = dao.findByUsername(loggedinUser);
		account.setUser(user);
		account.setAccountCreated(new java.sql.Date(new java.util.Date().getTime()));
		account.setAccountUpdated(new java.sql.Date(new java.util.Date().getTime()));
		account.setAccountStatus("A");
		accountdao.save(account);
	}
	
	@Override
	public Account getAccount(String loggedinUser) {
		return accountdao.getAccountByUserName(loggedinUser);
	}

	@Override
	public void saveServiceRequest(ServiceRequest serviceRequest, String loggedinUser) {
		// TODO Auto-generated method stub
		Users user = dao.findByUsername(loggedinUser);
		serviceRequest.setUser(user);
		serviceRequest.setReqDesc("Request has been processed for" +serviceRequest.getPage()+" pages"
				 +"at " +serviceRequest.getCharge()+"cost .");
		serviceRequestdao.save(serviceRequest);
	}

	@Override
	public List<Transaction> getTransactionDetails(Account account) {
		return transactiondao.getTransactionDetails(account.getAccountId());
	}
	
	@Override
	public Account findAccountByAcctId(Integer accId) {
		return accountdao.getAccountByAccountId(accId);
	}
	
	
}
