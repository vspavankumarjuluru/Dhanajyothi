package com.dhanjyoti.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dhanjyoti.springmvc.model.Beneficiary;



@Repository("beneficiaryDao")
public class BenficiaryDaoImpl extends AbstractDao<Integer, Beneficiary> implements BenficiaryDao {

	static final Logger logger = LoggerFactory.getLogger(BenficiaryDaoImpl.class);
		
	@Override
	public void saveBeneficiary(Beneficiary beneficiary) {
		getSession().save(beneficiary);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getBeneficiaryAccounts(String username, String benType) {
		Query query = getSession().createQuery(" select ben.benNickName, ben.benName, ben.benAccNumber, ben.benBank, ben.benBankIfsc from Beneficiary ben, Users user "
				+ "where ben.user.id=user.id and user.username = :username and ben.benType= :bentype ");
		query.setParameter("username", username);
		query.setParameter("bentype", benType);
		return (List<Object[]>) query.list();				
	}
	
	


}
