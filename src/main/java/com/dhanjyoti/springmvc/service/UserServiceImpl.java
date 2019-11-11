package com.dhanjyoti.springmvc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhanjyoti.springmvc.dao.KycDocumentDao;
import com.dhanjyoti.springmvc.dao.UserDao;
import com.dhanjyoti.springmvc.dao.UserLoginDao;
import com.dhanjyoti.springmvc.dao.UserProfileDao;
import com.dhanjyoti.springmvc.model.KycDocument;
import com.dhanjyoti.springmvc.model.User;
import com.dhanjyoti.springmvc.model.UserProfile;
import com.dhanjyoti.springmvc.model.Users;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	@Autowired
	private UserLoginDao useLogin;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserProfileDao useProfileDao;
	
	@Autowired
	private KycDocumentDao kycdocumentdao;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = dao.findBySSO(sso);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	
	public void deleteUserBySSO(String sso) {
		useLogin.deleteById(Integer.parseInt(sso));
	}

	public List<Users> findAllUsers() {
		return useLogin.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public Users findUsersById(int id) {
		Users users = useLogin.getUsersById(id);
		
		if(users != null) {		
				users.setUserstatus("A"); // Active
				dao.save(users);
				setValuesForLogin(users);
		}	
		
		return users;
	}
	
	private void setValuesForLogin(Users user) {
		
		UserProfile userProfile = useProfileDao.findById(1);
		Set<UserProfile> userProfileSet = new HashSet<>();
		userProfileSet.add(userProfile);
		
		User userlogin = new User();
		
		userlogin.setEmail(user.getEmail());
		userlogin.setFirstName(user.getFirstName());
		userlogin.setLastName(user.getLastName());
		userlogin.setPassword(user.getPassword());
		userlogin.setSsoId(user.getUsername());		
		userlogin.setUserProfiles(userProfileSet);
		dao.save(userlogin);
	}

	@Override
	public KycDocument getDocument(Integer id) {
		// TODO Auto-generated method stub
		return kycdocumentdao.findById(id);
	}
	
	
	
}
