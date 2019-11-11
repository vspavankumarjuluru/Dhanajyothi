package com.dhanjyoti.springmvc.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="TRANSACTION")
public class Transaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TRN_ID", nullable=false)
	private Integer trnId;
	
	@ManyToOne(optional = false, cascade={ javax.persistence.CascadeType.ALL })
	@JoinColumn(name="DEBIT_ACCT")
	private Account debitaccount;
	
	@ManyToOne(optional = false, cascade={ javax.persistence.CascadeType.ALL })
	@JoinColumn(name="CREDIT_ACCT")
	private Account creditAccount;
	
	@NotEmpty
	@Column(name="TRN_DESC", nullable=false)
	private String transdesc;
	
	@Column(name="TRN_AMT", nullable=false)
	private double transamt;
	
	@Column(name="TRN_DT_TIME", nullable=false)
	private Timestamp transdt;

	public Integer getTrnId() {
		return trnId;
	}

	public void setTrnId(Integer trnId) {
		this.trnId = trnId;
	}

	public Account getDebitaccount() {
		return debitaccount;
	}

	public void setDebitaccount(Account debitaccount) {
		this.debitaccount = debitaccount;
	}

	public Account getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(Account creditAccount) {
		this.creditAccount = creditAccount;
	}

	public String getTransdesc() {
		return transdesc;
	}

	public void setTransdesc(String transdesc) {
		this.transdesc = transdesc;
	}

	public double getTransamt() {
		return transamt;
	}

	public void setTransamt(double transamt) {
		this.transamt = transamt;
	}

	public Timestamp getTransdt() {
		return transdt;
	}

	public void setTransdt(Timestamp transdt) {
		this.transdt = transdt;
	}
	
	
	
}