package com.dhanjyoti.springmvc.service;

import java.util.List;
import java.util.Map;

import com.dhanjyoti.springmvc.model.Beneficiary;
import com.dhanjyoti.springmvc.model.BeneficiaryTrans;


public interface FundTransferService {
	
	void saveBeneficiaryAccount(Beneficiary beneficiary, String loggedinUser);
	
	Map<String, List<String>> getBeneficiaryAccounts(String loggedinUser, String internalOrExternal);
	
	boolean saveTransaction(BeneficiaryTrans beneficiarytrans, String loggedinUser);
	
	void transferownaccountfunds(BeneficiaryTrans beneficiarytrans);
}