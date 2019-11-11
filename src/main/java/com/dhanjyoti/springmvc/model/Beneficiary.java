package com.dhanjyoti.springmvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BENEFICIARY")
public class Beneficiary implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BEN_ID", nullable=false)
	private Integer benId;
	
	@ManyToOne(optional = false, cascade={ javax.persistence.CascadeType.ALL })
	@JoinColumn(name="OWNER_ID")
	private Users user;	
	
	@Column(name="BEN_TYPE", nullable=false)
	private String benType;
	
	@Column(name="BEN_NICK_NAME",nullable=false)
	private String benNickName;
	
	@Column(name="BEN_NAME",nullable=false)
	private String benName;
	
	@Column(name="BEN_ACCT_NUM",nullable=false)
	private Integer benAccNumber;
	
	@Column(name="BEN_BANK")
	private String benBank;
	
	@Column(name="BEN_BANK_IFSC")
	private String benBankIfsc;

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