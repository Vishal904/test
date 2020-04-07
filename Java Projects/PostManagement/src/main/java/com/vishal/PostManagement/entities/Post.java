package com.vishal.PostManagement.entities;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class Post extends AbstractEntity {

	private long userid;
	private String posttitle;
	private String description;
	private Date date;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getPosttitle() {
		return posttitle;
	}

	public void setPosttitle(String posttitle) {
		this.posttitle = posttitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
