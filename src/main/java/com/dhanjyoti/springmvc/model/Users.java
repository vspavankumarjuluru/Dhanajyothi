package com.dhanjyoti.springmvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="USER")
public class Users implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID", nullable=false)
	private Integer id;
	
	@NotEmpty
	@Column(name="USERNAME", nullable=false)
	private String username;
	
	@NotEmpty
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	//@NotEmpty
	@Column(name="USER_ROLE", nullable=false)
	private String userrole;
	
	//@NotEmpty
	@Column(name="USER_STATUS", nullable=false)
	private String userstatus;
	
	//@NotNull
	@Column(name="DOB", nullable=false)
	private Date dob;
	
	@NotEmpty
	@Column(name="ADD_LINE1", nullable=false)
	private String addline1;
	
	//@NotEmpty
	@Column(name="ADD_LINE2", nullable=false)
	private String addline2;
	
	@NotEmpty
	@Column(name="CITY", nullable=false)
	private String city;
	
	@NotEmpty
	@Column(name="STATE", nullable=false)
	private String state;
	
	@NotEmpty	
	@Column(name="PIN", nullable=false)
	private String pin;
	
	@NotEmpty
	@Column(name="MOBILE_NUMBER", nullable=false)
	private String mobileNumber;
		
	@NotEmpty
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;

	@NotEmpty
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@NotEmpty
	@Column(name="EMAIL_ID", nullable=false)
	private String email;
		
	/*@NotEmpty*/
	@Column(name="PAN", nullable=true)
	private String pan;
	
	/*@NotEmpty*/
	@Column(name="AADHAR_ID", nullable=true)
	private String aadharId;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade={ javax.persistence.CascadeType.ALL })
	private List<KycDocument> kycDocList = new ArrayList<>();
	


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddline1() {
		return addline1;
	}

	public void setAddline1(String addline1) {
		this.addline1 = addline1;
	}

	public String getAddline2() {
		return addline2;
	}

	public void setAddline2(String addline2) {
		this.addline2 = addline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAadharId() {
		return aadharId;
	}

	public void setAadharId(String aadharId) {
		this.aadharId = aadharId;
	}
	
	

	public List<KycDocument> getKycDocList() {
		return kycDocList;
	}

	public void setKycDocList(List<KycDocument> kycDocList) {
		this.kycDocList = kycDocList;
	}
	
	public void addKycDocument(KycDocument kycdoc) {
		kycDocList.add(kycdoc);
		kycdoc.setUser(this);
	}

	/*
	 * DO-NOT-INCLUDE passwords in toString function.
	 * It is done here just for convenience purpose.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + "" +  ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}


	
}
