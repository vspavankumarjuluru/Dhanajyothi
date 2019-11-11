package com.dhanjyoti.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dhanjyoti.springmvc.model.KycDocument;
import com.dhanjyoti.springmvc.model.User;
import com.dhanjyoti.springmvc.model.Users;



@Repository("usersDao")
public class UserLoginDaoImpl extends AbstractDao<Integer, Users> implements UserLoginDao {

	static final Logger logger = LoggerFactory.getLogger(UserLoginDaoImpl.class);


	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	
	@SuppressWarnings("unchecked")
	public List<Users> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.add(Restrictions.eq("userstatus", "P"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Users> users = (List<Users>) criteria.list(); 		
		return users;
	}


	@Override
	public void save(Users user) {
		getSession().save(user);
	}

	@Override
	public Users getUsersById(int id) {
		Users user = getByKey(id);
		return user;
	}
	
	@Override
	public void deleteById(int id) {
		Users user = getByKey(id);
		List<KycDocument> kycdocs = user.getKycDocList();
		for(KycDocument kyc: kycdocs) {
		getSession().delete(kyc);// cascade is not working. need to check later
		}
		getSession().delete(user);
	}
	
	
	public Users findByUsername(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", sso));
		Users user = (Users)crit.uniqueResult();	
		return user;
	}

	
}
