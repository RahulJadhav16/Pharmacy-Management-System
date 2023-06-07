package com.pms.doctor.service.Models;

import org.springframework.data.annotation.Id;

public class Order {
	@Id
	private String id;
	private String doctor;
	private String name;
	private int price;
	private String type;
	private String category;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Order(String id, String doctor, String name, int price, String type, String category) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.name = name;
		this.price = price;
		this.type = type;
		this.category = category;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
