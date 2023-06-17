package com.pms.doctor.service.Models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class Order {
	
	private String orderId;
	private String DoctorId;
	private String doctorName;
	private String email;
	private String address;
	private String drugName;
	private int quantity;
	private boolean status;
	private LocalDate orderDate;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDoctorId() {
		return DoctorId;
	}
	public void setDoctorId(String doctorId) {
		DoctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isStatus() {
		return status;
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	public Order(String orderId, String doctorId, String doctorName, String email, String address, String drugName,
			int quantity, boolean status, LocalDate orderDate) {
		super();
		this.orderId = orderId;
		DoctorId = doctorId;
		this.doctorName = doctorName;
		this.email = email;
		this.address = address;
		this.drugName = drugName;
		this.quantity = quantity;
		this.status = status;
		this.orderDate = orderDate;
	}
	public Order(String orderId, String doctorId, String doctorName, String drugName, int quantity, boolean status,
			LocalDate orderDate) {
		super();
		this.orderId = orderId;
		DoctorId = doctorId;
		this.doctorName = doctorName;
		this.drugName = drugName;
		this.quantity = quantity;
		
		this.orderDate = orderDate;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", DoctorId=" + DoctorId + ", doctorName=" + doctorName + ", drugName="
				+ drugName + ", quantity=" + quantity + ", status=" + status + ", orderDate=" + orderDate + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
