package com.hackathon.command_line.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "developers")
public class Developer implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private Category category;
	
	private String phone;
	
	//Días a asistir al mobile ( 28 Feb, 1 -3 Marzo)
	@Enumerated(EnumType.STRING)
	@Column(name = "date")
	private MwcDays date;
	
	
	public Developer() {}	
	
	
	public Developer(String name, String email, Category category, String phone, MwcDays date) {
		this.name = name;
		this.email = email;
		this.category = category;
		this.phone = phone;
		this.date = date;
	}
	
	public Developer(int id, String name, String email, Category category, String phone, MwcDays date) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.category = category;
		this.phone = phone;
		this.date = date;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public MwcDays getDate() {
		return date;
	}

	public void setDate(MwcDays date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Developer [id=" + id + ", name=" + name + ", email=" + email + ", category=" + category + ", phone="
				+ phone + ", date=" + date + "]";
	}

}
