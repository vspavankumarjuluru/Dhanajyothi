package com.dhanjyoti.springmvc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dhanjyoti.springmvc.model.Account;
import com.dhanjyoti.springmvc.model.ServiceRequest;



@Repository("servicerequestdao")
public class ServiceRequestDaoImpl extends AbstractDao<Integer, Account> implements ServiceRequestDao {

	static final Logger logger = LoggerFactory.getLogger(ServiceRequestDaoImpl.class);
		
	@Override
	public void save(ServiceRequest servicerequest) {
		getSession().save(servicerequest);
	}
	
	/*@Override
	public Account getAccountByUserName(String username) {
		Query query = getSession().createQuery("select acc from Account acc, Users user "
				+ "where acc.user.id=user.id and user.username = :username ");
		query.setParameter("username", username);
		return (Account) query.uniqueResult();				
	}*/


}
