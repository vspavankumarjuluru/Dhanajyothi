package com.dhanjyoti.springmvc.dao;

import java.util.List;

import com.dhanjyoti.springmvc.model.Beneficiary;

public interface BenficiaryDao {	
	
	void saveBeneficiary(Beneficiary beneficiary);
	
	List<Object[]> getBeneficiaryAccounts(String loggedinUser, String bentype);
}

