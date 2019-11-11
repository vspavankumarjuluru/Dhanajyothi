package com.dhanjyoti.springmvc.dao;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dhanjyoti.springmvc.model.Account;



@Repository("accountDao")
public class AccountDaoImpl extends AbstractDao<Integer, Account> implements AccountDao {

	static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
		
	@Override
	public void save(Account account) {
		getSession().save(account);
	}
	
	@Override
	public Account getAccountByUserName(String username) {
		Query query = getSession().createQuery("select acc from Account acc, Users user "
				+ "where acc.user.id=user.id and acc.accType='S' and user.username = :username ");
		query.setParameter("username", username);
		return (Account) query.uniqueResult();				
	}
	
	
	@Override
	public Account getAccountByAccountId(int accountId) {
		Account account = getByKey(accountId);
		return account;
	}
	
	@Override
	public void saveOrUpdate(Account account) {
		getSession().saveOrUpdate(account);
	}

}
