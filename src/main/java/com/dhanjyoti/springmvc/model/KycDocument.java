package com.dhanjyoti.springmvc.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="KYC_DOCUMENT")
public class KycDocument implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="KYC_ID", nullable=false)
	private Integer id;
	
	@ManyToOne(optional = false, cascade={ javax.persistence.CascadeType.ALL })
	@JoinColumn(name="USER_ID")
	private Users user;
	
	@NotEmpty
	@Column(name="KYC_TYPE", nullable=false)
	private String kycType;
	
	//@NotEmpty
	@Column(name="DOCUMENT_DESC", nullable=false)
	private String docDesc;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name="DOC")
	private byte[] doc;	
	

	public byte[] getDoc() {
		return doc;
	}

	public void setDoc(byte[] doc) {
		this.doc = doc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getKycType() {
		return kycType;
	}

	public void setKycType(String kycType) {
		this.kycType = kycType;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}
	
	
}
