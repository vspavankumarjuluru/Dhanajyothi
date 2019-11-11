package com.dhanjyoti.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACCT_ID", nullable=false)
	private Integer accountId;
	
	@OneToOne(optional = false, cascade={ javax.persistence.CascadeType.ALL })
	@JoinColumn(name="ACCT_HOLDER_ID")
	private Users user;
	
	@NotEmpty
	@Column(name="ACCT_TYPE", nullable=false)
	private String accType;
	
	@Column(name="INT_RATE", nullable=false)
	private double intRate;
	
	@Column(name="ACC_BALANCE", nullable=false)
	private double accountBalance;	

	@Column(name="DEPOSIT_TENURE")
	private Integer depoTenure;
	
	@Column(name="MATURITY_AMT")
	private double maturityAmt;
	
	@Column(name="ACCOUNT_CREATED_DATE")
	private Date accountCreated;
	
	@Column(name="ACCOUNT_UPDATED_DATE")
	private Date accountUpdated;
	
	@Column(name="ACC_STATUS",nullable=false)
	private String accountStatus;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getIntRate() {
		return intRate;
	}

	public void setIntRate(double intRate) {
		this.intRate = intRate;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Integer getDepoTenure() {
		return depoTenure;
	}

	public void setDepoTenure(Integer depoTenure) {
		this.depoTenure = depoTenure;
	}

	public double getMaturityAmt() {
		return maturityAmt;
	}

	public void setMaturityAmt(double maturityAmt) {
		this.maturityAmt = maturityAmt;
	}

	public Date getAccountCreated() {
		return accountCreated;
	}

	public void setAccountCreated(Date accountCreated) {
		this.accountCreated = accountCreated;
	}

	public Date getAccountUpdated() {
		return accountUpdated;
	}

	public void setAccountUpdated(Date accountUpdated) {
		this.accountUpdated = accountUpdated;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
		
}
