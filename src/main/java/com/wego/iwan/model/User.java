package com.wego.iwan.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@NamedQuery(name="User.findAll", query="SELECT v FROM User v")
@Table(name="users")
@Data
public class User {

	@Id
	private String id;
	private String name;

	@Column(name = "user_name")
	private String username;
	private String password;
	private String phone;

	@Column(name = "created_date")
	private Date createddate;
	
	public User() {
		this.id = generateId();
	}
	
	private String generateId() {
		long now = Calendar.getInstance().getTimeInMillis();
		return String.valueOf(now);
	}
	
	public void setGeneratedId() {
		this.id = generateId();
	}

}
