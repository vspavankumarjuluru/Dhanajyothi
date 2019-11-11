package com.dhanjyoti.springmvc.dao;

import java.util.List;

import com.dhanjyoti.springmvc.model.Transaction;


public interface TransactionDao {	
	
	void save(Transaction transaction);
	
	List<Transaction> getTransactionDetails(int id);
}

