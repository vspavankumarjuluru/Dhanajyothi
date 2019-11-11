package com.dhanjyoti.springmvc.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhanjyoti.springmvc.dao.AccountDao;
import com.dhanjyoti.springmvc.dao.BenficiaryDao;
import com.dhanjyoti.springmvc.dao.TransactionDao;
import com.dhanjyoti.springmvc.dao.UserLoginDao;
import com.dhanjyoti.springmvc.model.Account;
import com.dhanjyoti.springmvc.model.Beneficiary;
import com.dhanjyoti.springmvc.model.BeneficiaryTrans;
import com.dhanjyoti.springmvc.model.Transaction;
import com.dhanjyoti.springmvc.model.Users;

@Service
@Transactional
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	private UserLoginDao dao;

	@Autowired
	private BenficiaryDao benficiaryDao;

	@Autowired
	private AccountDao accountdao;
	
	@Autowired
	private TransactionDao transactiondao;

	@Override
	public void saveBeneficiaryAccount(Beneficiary beneficiary, String loggedinUser) {
		Users user = dao.findByUsername(loggedinUser);
		beneficiary.setUser(user);
		benficiaryDao.saveBeneficiary(beneficiary);

	}

	@Override
	public Map<String, List<String>> getBeneficiaryAccounts(String loggedinUser, String bentype) {
		List<Object[]> data = benficiaryDao.getBeneficiaryAccounts(loggedinUser, bentype);
		Map<String, List<String>> dataMap = new HashMap<>();
		List<String> nickList = new ArrayList<>();
		if (data != null && !data.isEmpty()) {
			data.stream().forEach(aa -> {
				List<String> dataList = new ArrayList<>();
				String nickName = (String) aa[0];
				nickList.add(nickName);
				System.out.println("nickName" + nickName);
				String benName = (String) aa[1];
				System.out.println("benName" + benName);
				dataList.add(benName);
				Integer benAcctNumber = (Integer) aa[2];
				System.out.println("benAcctNumber" + benAcctNumber);
				dataList.add(String.valueOf(benAcctNumber));
				String benBank = (String) aa[3];
				System.out.println("benBank" + benBank);
				dataList.add(benBank);

				String benBankIfsc = (String) aa[4];
				System.out.println("benBankIfsc" + benBankIfsc);
				dataList.add(benBankIfsc);

				dataMap.put(nickName, dataList);

			});

			dataMap.put("bennicknames", nickList);
		}
		System.out.println("dataMap ::" + dataMap);

		return dataMap;
	}

	@Override
	public boolean saveTransaction(BeneficiaryTrans beneficiarytrans, String loggedinUser) {
		Account ownaccount = accountdao.getAccountByUserName(loggedinUser);
		if(ownaccount != null && ownaccount.getAccountBalance() >=beneficiarytrans.getTransamt()) {			
			ownaccount.setAccountBalance(ownaccount.getAccountBalance()-beneficiarytrans.getTransamt());
			accountdao.saveOrUpdate(ownaccount);
		} else {
			return false;
		}
		Account account = accountdao.getAccountByAccountId(beneficiarytrans.getBenAccNumber());		
		Transaction transaction = new Transaction();
		transaction.setDebitaccount(ownaccount);
		transaction.setCreditAccount(account);
		transaction.setTransamt(beneficiarytrans.getTransamt());
		transaction.setTransdesc("Bank :" + beneficiarytrans.getBenName() + " : Account Number: " + account.getAccountId());
		Timestamp ts=new Timestamp(new java.util.Date().getTime());
		transaction.setTransdt(ts);
		transactiondao.save(transaction);
		return true;
	}

	@Override
	public void transferownaccountfunds(BeneficiaryTrans beneficiarytrans) {
		// TODO Auto-generated method stub
		Account account = accountdao.getAccountByAccountId(beneficiarytrans.getBenAccNumber());
		if(account!= null) {
			account.setAccountBalance(account.getAccountBalance()+beneficiarytrans.getTransamt());
			accountdao.saveOrUpdate(account);
		}
	}
	
	

}
