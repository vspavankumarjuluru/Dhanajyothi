package com.dhanjyoti.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dhanjyoti.springmvc.model.Transaction;



@Repository("transactionDao")
public class TransactionDaoImpl extends AbstractDao<Integer, Transaction> implements TransactionDao {

	static final Logger logger = LoggerFactory.getLogger(TransactionDaoImpl.class);
		
	@Override
	public void save(Transaction transaction) {
		getSession().save(transaction);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getTransactionDetails(int id) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("select transaction from Transaction transaction "
				+ "where transaction.debitaccount.accountId in (:id) or transaction.creditAccount.accountId in (:id) "
				+ " order by transaction.transdt desc");
		query.setParameter("id", id);
		return query.list();
	}
	
	

}
