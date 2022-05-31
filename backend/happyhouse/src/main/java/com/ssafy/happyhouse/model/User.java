package com.ssafy.happyhouse.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
	private String ID;
	private String password;
	private String username;
	private String phone;
	private Timestamp create_time;
	private String email;
	private String address;
	private String city;
	private String country;
	private String postalCode;
	private String aboutMe;
	private int age;

	public User(String ID, String password, String username, String phone, Timestamp create_time, String email, String address, String city, String country, String postalCode, String aboutMe, int age) {
		this.ID = ID;
		this.password = password;
		this.username = username;
		this.phone = phone;
		this.create_time = create_time;
		this.email = email;
		this.address = address;
		this.city = city;
		this.country = country;
		this.postalCode = postalCode;
		this.aboutMe = aboutMe;
		this.age = age;
	}

	public User() {
	}


}
