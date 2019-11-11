package com.dhanjyoti.springmvc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dhanjyoti.springmvc.model.Account;
import com.dhanjyoti.springmvc.model.ServiceRequest;
import com.dhanjyoti.springmvc.model.Transaction;
import com.dhanjyoti.springmvc.service.AccountService;

@Controller
@RequestMapping("/")
@SessionAttributes
public class AccountController {
	
	@Autowired
	AccountService accountService;

	@RequestMapping(value = { "/openSBAcc-{loggedinuser}" }, method = RequestMethod.GET)
	public ModelAndView newCustomer(@PathVariable String loggedinuser, @ModelAttribute("account") Account account) {
		ModelAndView mav = new ModelAndView("newSBAccount");	
		return mav;
	}
	
	@RequestMapping(value = { "/openTermAcc-{loggedinuser}" }, method = RequestMethod.GET)
	public ModelAndView newTermDeposit(@PathVariable String loggedinuser, @ModelAttribute("account") Account account, 
			 ModelMap model) {
		ModelAndView mav = new ModelAndView("newSBAccount");
		model.addAttribute("termdeposit", true);
		//depoTenure
		Integer[] tenture = {1, 2, 3, 4, 5};
		List<Integer> tentureList = Arrays.asList(tenture);
		model.addAttribute("depoTenure", tentureList);
		return mav;
	}
	
	@RequestMapping(value = { "/openSBAcc-{loggedinuser}" }, method = RequestMethod.POST)
	public ModelAndView openSBAccount(@PathVariable String loggedinuser, @ModelAttribute("account") Account account, ModelMap model) {
		System.out.println("loggedinuser" +loggedinuser);
		System.out.println("account" +account.getAccountBalance());
		accountService.saveAccount(account, loggedinuser);
		Account acc = accountService.getAccount(loggedinuser);
		model.addAttribute("sbacc", true);
		model.addAttribute("accId", acc.getAccountId());
		ModelAndView mav = new ModelAndView("newSBAccount");	
		return mav;
	}
	
	@RequestMapping(value = { "/openTermAcc-{loggedinuser}" }, method = RequestMethod.POST)
	public ModelAndView openTermDepositAccount(@PathVariable String loggedinuser, @ModelAttribute("account") Account account, ModelMap model) {
		/*System.out.println("loggedinuser" +loggedinuser);
		System.out.println("account" +account.getAccountBalance());*/
		ModelAndView mav = new ModelAndView("newSBAccount");
		model.addAttribute("termdeposit", true);
		Integer[] tenture = {1, 2, 3, 4, 5};
		List<Integer> tentureList = Arrays.asList(tenture);
		model.addAttribute("depoTenure", tentureList);
		if(account.getMaturityAmt() <1000) {
			model.addAttribute("error", true);
			return mav;
		}		
		//accountService.saveAccount(account, loggedinuser);
		Account acc = accountService.getAccount(loggedinuser);
		
		if(acc.getAccountBalance()<account.getMaturityAmt()) {
			model.addAttribute("errorfunds", true);
			return mav;
		}
		acc.setAccountBalance(acc.getAccountBalance() - account.getMaturityAmt());
		accountService.saveAccount(acc, loggedinuser);
		
		account.setAccountBalance(0);
		account.setAccType("T");
		account.setIntRate(getIntRate(account.getDepoTenure(), account.getMaturityAmt()));
		accountService.saveTermAccount(account, loggedinuser);
		
		model.addAttribute("termacc", true);
		model.addAttribute("accId", acc.getAccountId());
			
		return mav;
	}
	
	private double getIntRate(Integer depoTenture, double maturityamt) {
		double intrate = 0.0;
		
		if(maturityamt<100000 && depoTenture <= 3) {
			intrate=5.75;
		} else if(maturityamt<1000000 && depoTenture <= 2) {
			intrate=6.00;
		}else if(maturityamt<1000000 && depoTenture == 3){
			intrate=6.25;
		} else if(maturityamt>=10000000 && depoTenture==1) {
			intrate=6.25;
		}else if(maturityamt>=10000000 && depoTenture==2) {
			intrate=6.50;
		} else {
			intrate=6.70;
		}
		return intrate;
	}
	
	@RequestMapping(value = { "/accBalance-{loggedinuser}" }, method = RequestMethod.GET)
	public ModelAndView checkAcctBalance(@PathVariable String loggedinuser, ModelMap model) {
		System.out.println("loggedinUser" +loggedinuser);
		Account account = accountService.getAccount(loggedinuser);
		model.addAttribute("accbal", true);
		model.addAttribute("account", account);
		ModelAndView mav = new ModelAndView("userslist");	
		return mav;
	}
	
	@RequestMapping(value = { "/req-{loggedinuser}" }, method = RequestMethod.GET)
	public ModelAndView loadReqChequebookPage(@PathVariable String loggedinuser,ModelMap model) {
		String[] pages = {"20", "50", "100"};
		List<String> noofpages = Arrays.asList(pages);
		String[] charges = {"100", "200", "300"};
		List<String> charegsList = Arrays.asList(charges);
		model.addAttribute("pages", noofpages);
		model.addAttribute("charges", charegsList);
		ModelAndView mav = new ModelAndView("chequebook");	
		return mav;
	}
	
	@RequestMapping(value = { "/req-{loggedinuser}" }, method = RequestMethod.POST)
	public ModelAndView submitReqChequebookPage(@PathVariable String loggedinuser,
			@ModelAttribute("servicerequest") ServiceRequest serviceRequest, ModelMap model) {
		accountService.saveServiceRequest(serviceRequest, loggedinuser);
		model.addAttribute("req", true);
		ModelAndView mav = new ModelAndView("chequebook");	
		return mav;
	}
	
	@RequestMapping(value = { "/viewstatements-{loggedinuser}" }, method = RequestMethod.GET)
	public ModelAndView viewMonthlyStatements(@PathVariable String loggedinuser, ModelMap model) {
		System.out.println("loggedinUser" +loggedinuser);
		Account account = accountService.getAccount(loggedinuser);
		List<Transaction> transactionlist = accountService.getTransactionDetails(account);
		model.addAttribute("transactions", transactionlist);
		model.addAttribute("trans", true);
		model.addAttribute("statement", true);
		ModelAndView mav = new ModelAndView("userslist");	
		return mav;
	}
	
	
	@ModelAttribute
	private void addAttributes(ModelMap model) {
	    model.addAttribute("account", new Account());
	}
	
	@ModelAttribute("account")
	public Account getUsers(){
	    return  new Account();
	}
	
	@ModelAttribute
	private void addAttributesReq(ModelMap model) {
	    model.addAttribute("servicerequest", new ServiceRequest());
	}
	
	@ModelAttribute("servicerequest")
	public ServiceRequest getReqCheques(){
	    return  new ServiceRequest();
	}
	
}
