package com.vishal.PostManagement.entities;

import javax.persistence.Entity;

import com.vishal.PostManagement.entities.AbstractEntity;

@Entity
public class Address extends AbstractEntity {

	private long userid;
	private String address;
	private String district;
	private String city;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
