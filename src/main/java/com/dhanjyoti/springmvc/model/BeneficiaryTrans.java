package com.dhanjyoti.springmvc.model;

import java.io.Serializable;


public class BeneficiaryTrans implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer benId;	
	
	private Users user;	
	
	private String benType;
	
	private String benNickName;
	
	private String benName;
	
	private Integer benAccNumber;
	
	private String benBank;
	
	private String benBankIfsc;
	
	private double transamt;	
	

	public double getTransamt() {
		return transamt;
	}

	public void setTransamt(double transamt) {
		this.transamt = transamt;
	}

	public Integer getBenId() {
		return benId;
	}

	public void setBenId(Integer benId) {
		this.benId = benId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getBenType() {
		return benType;
	}

	public void setBenType(String benType) {
		this.benType = benType;
	}

	public String getBenNickName() {
		return benNickName;
	}

	public void setBenNickName(String benNickName) {
		this.benNickName = benNickName;
	}

	public String getBenName() {
		return benName;
	}

	public void setBenName(String benName) {
		this.benName = benName;
	}

	public Integer getBenAccNumber() {
		return benAccNumber;
	}

	public void setBenAccNumber(Integer benAccNumber) {
		this.benAccNumber = benAccNumber;
	}

	public String getBenBank() {
		return benBank;
	}

	public void setBenBank(String benBank) {
		this.benBank = benBank;
	}

	public String getBenBankIfsc() {
		return benBankIfsc;
	}

	public void setBenBankIfsc(String benBankIfsc) {
		this.benBankIfsc = benBankIfsc;
	}
	
	
	
}