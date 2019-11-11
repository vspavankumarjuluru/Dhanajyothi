package com.dhanjyoti.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dhanjyoti.springmvc.model.Account;
import com.dhanjyoti.springmvc.model.Beneficiary;
import com.dhanjyoti.springmvc.model.BeneficiaryTrans;
import com.dhanjyoti.springmvc.service.AccountService;
import com.dhanjyoti.springmvc.service.FundTransferService;

@RestController
@RequestMapping("/")
@SessionAttributes
public class FundTransferController {

	@Autowired
	private FundTransferService fundTransferService;
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = { "/TransWithOwn-{loggedinuser}/bank/{intorext}" }, method = RequestMethod.GET)
	public ModelAndView newCustomer(@PathVariable String loggedinuser, @PathVariable String intorext,
			@ModelAttribute("beneficiarytrans") BeneficiaryTrans beneficiaryTrans, ModelMap model) {
		ModelAndView mav = new ModelAndView("fundtransfer");		
		Map<String, List<String>> dataMap = fundTransferService.getBeneficiaryAccounts(loggedinuser, intorext);
		model.addAttribute("bennicknames", dataMap.get("bennicknames"));
		dataMap.remove("bennicknames");
		Map<String, String> tempMap = new HashMap<>();
		if(dataMap != null ) {			
			dataMap.forEach((k,v) -> {
				String citiesCommaSeparated = String.join("|", v);
				tempMap.put(k, citiesCommaSeparated);
				});
		}
		
		model.addAttribute("dataMap" , tempMap);
		model.addAttribute("bank", intorext);		
		model.addAttribute("isext", intorext.equalsIgnoreCase("E") ? true : false);
		return mav;
	}
	
	@RequestMapping(value = { "/TransWithOwn-{loggedinuser}/bank/{intorext}" }, method = RequestMethod.POST)
	public ModelAndView transferAmt(@PathVariable String loggedinuser, @PathVariable String intorext,
			@ModelAttribute("beneficiarytrans") BeneficiaryTrans beneficiaryTrans, ModelMap model) {
		ModelAndView mav = new ModelAndView("fundtransfer");
		beneficiaryTrans.setBenType(intorext);
		boolean status = fundTransferService.saveTransaction(beneficiaryTrans, loggedinuser);
		System.out.println("status :" +status);
		model.addAttribute("bank", intorext);		
		model.addAttribute("isext", intorext.equalsIgnoreCase("E") ? true : false);
		if(!status) {
			model.addAttribute("error", true);
			model.addAttribute("errordesc", "Transaction failed due to insufficient balance.");
			return mav;
		}
		
		if(intorext.equalsIgnoreCase("I")) {
			fundTransferService.transferownaccountfunds(beneficiaryTrans);
		}
		model.addAttribute("trans", true);
		return mav;
	}

	@RequestMapping(value = { "/beneficiary-{loggedinuser}/bank/{intorext}" }, method = RequestMethod.GET)
	public ModelAndView addbeneficiary(@PathVariable String loggedinuser, @PathVariable String intorext,
			@ModelAttribute("beneficiary") Beneficiary account, ModelMap model) {
		ModelAndView mav = new ModelAndView("beneficiary");
		model.addAttribute("bank", intorext);
		model.addAttribute("isext", intorext.equalsIgnoreCase("E") ? true : false);
		return mav;
	}

	@RequestMapping(value = { "/beneficiary-{loggedinuser}/bank/{intorext}" }, method = RequestMethod.POST)
	public ModelAndView savebeneficiary(@PathVariable String loggedinuser, @PathVariable String intorext,
			@ModelAttribute("beneficiary") Beneficiary beneficiary, ModelMap model) {
		System.out.println("loggedinUser" + loggedinuser);		
		ModelAndView mav = new ModelAndView("beneficiary");
		model.addAttribute("bank", intorext);
		model.addAttribute("isext", intorext.equalsIgnoreCase("E") ? true : false);
		if(intorext.equalsIgnoreCase("I")) {
			Account account = accountService.getAccount(loggedinuser);
			if(account.getAccountId() == beneficiary.getBenAccNumber()) {
				model.addAttribute("error", true);
				model.addAttribute("errordesc", "You cant add the same account as beneficiary.");
				return mav;
			} else if(accountService.findAccountByAcctId(beneficiary.getBenAccNumber()) == null) {
				model.addAttribute("error", true);
				model.addAttribute("errordesc", "Invalid account number. ");
				return mav;
			}
			
		}		
		beneficiary.setBenType(intorext);		
		fundTransferService.saveBeneficiaryAccount(beneficiary, loggedinuser);
		model.addAttribute("benefAdd", true);		
		return mav;
	}	

	@ModelAttribute
	private void addAttributesReq(ModelMap model) {
		model.addAttribute("beneficiary", new Beneficiary());
	}

	@ModelAttribute("beneficiary")
	public Beneficiary getReqCheques() {
		return new Beneficiary();
	}

	@ModelAttribute
	private void addAttributestrans(ModelMap model) {
		model.addAttribute("beneficiarytrans", new BeneficiaryTrans());
	}

	@ModelAttribute("beneficiarytrans")
	public BeneficiaryTrans gettrans() {
		return new BeneficiaryTrans();
	}	
	

}
