package com.mindtree.easybucks.dto;

public class EmployeeDto {

	private String userName;
	private String password;
	private String fullName;    
	private String email;
	private String dob;
	private String security_que;
	private String security_ans;
	
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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
