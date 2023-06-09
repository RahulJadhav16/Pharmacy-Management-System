package com.pms.doctor.service.Models;

import org.springframework.data.annotation.Id;

public class Order {
	
	private String orderId;
	private String DoctorId;
	private String doctorName;
	private String drugName;
	private int quantity;
	private boolean status;
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
	public Order(String orderId, String doctorId, String doctorName, String drugName, int quantity, boolean status) {
		super();
		this.orderId = orderId;
		DoctorId = doctorId;
		this.doctorName = doctorName;
		this.drugName = drugName;
		this.quantity = quantity;
	}
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", DoctorId=" + DoctorId + ", doctorName=" + doctorName + ", drugName="
				+ drugName + ", quantity=" + quantity + ", status=" + status + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

}
