package com.vishal.PostManagement.entities;

import javax.persistence.Entity;

import com.vishal.PostManagement.entities.AbstractEntity;


@Entity
public class User extends AbstractEntity {

	private String fname;
	private String lname;
	private String email;
	private int age;
	private String phone;
	private String gender;
	private int usertypeid = 2;
	private String password;

	

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getUsertypeid() {
		return usertypeid;
	}

	public void setUsertypeid(int usertypeid) {
		this.usertypeid = usertypeid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
