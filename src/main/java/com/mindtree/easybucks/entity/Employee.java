package com.mindtree.easybucks.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "employee")

public class Employee implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "userName" , unique=true)
    @NotEmpty(message = "userName must not be empty")
	private String userName;
	
	private String password;
	private String fullName;
    @NotEmpty(message = "Email must not be empty")
	private String email;
	private String dob;
	private String security_que;
	private String security_ans;
	
	public Employee() {
		
	}
	
	public Employee(String userName ,String email, String fullName ) {
		
		this.userName = userName;
		this.email = email;
		this.fullName = fullName;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSecurity_que() {
		return security_que;
	}
	public void setSecurity_que(String security_que) {
		this.security_que = security_que;
	}
	public String getSecurity_ans() {
		return security_ans;
	}
	public void setSecurity_ans(String security_ans) {
		this.security_ans = security_ans;
	}
	

}
