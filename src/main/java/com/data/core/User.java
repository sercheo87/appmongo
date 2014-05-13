package com.data.core;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Field("name")
	private String Name;
	@Field("lName")
	private String LastName;
	@Field("dBirth")
	private Date DateBirth;

	public User() {
	}

	@PersistenceConstructor
	public User(String name, String lastName) {
		Name = name;
		LastName = lastName;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Date getDateBirth() {
		return DateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		DateBirth = dateBirth;
	}
}
