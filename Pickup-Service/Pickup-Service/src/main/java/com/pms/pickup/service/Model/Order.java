package com.pms.pickup.service.Model;

import java.time.LocalDate;

public class Order {
	private String orderId;
	private String doctorId;
	private String doctorName;
	private String drugName;
	private int quantity;
	private boolean status;
	private LocalDate orderDate;
	
	

	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
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
	public void setStatus(boolean status) {
		this.status = status;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public Order(String orderId, String doctorId, String doctorName, String drugName, int quantity, boolean status,
			LocalDate orderDate) {
		super();
		this.orderId = orderId;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.drugName = drugName;
		this.quantity = quantity;
		this.status = status;
		this.orderDate = orderDate;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", doctorId=" + doctorId + ", doctorName=" + doctorName + ", drugName="
				+ drugName + ", quantity=" + quantity + ", status=" + status + ", orderDate=" + orderDate + "]";
	}
	
	

}
