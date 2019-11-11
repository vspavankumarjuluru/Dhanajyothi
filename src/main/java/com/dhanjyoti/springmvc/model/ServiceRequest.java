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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SERVICE_REQUEST")
public class ServiceRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REQ_ID", nullable=false)
	private Integer reqId;
	
	@ManyToOne(optional = false, cascade={ javax.persistence.CascadeType.ALL })
	@JoinColumn(name="CUST_ID")
	private Users user;
	
	@NotEmpty
	@Column(name="REQ_DESC", nullable=false)
	private String reqDesc;
	
	@Column(name="PAGE")
	private String page;
	
	@Column(name="CHARGE")
	private String charge;
	

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getReqDesc() {
		return reqDesc;
	}

	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}
	
	
	
}
