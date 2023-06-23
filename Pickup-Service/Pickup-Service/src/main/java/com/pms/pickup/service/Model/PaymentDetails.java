package com.pms.pickup.service.Model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PaymentDetails")
public class PaymentDetails {
	@Id
	private String paymentId;
	private String orderId;
	private double amountPaid;
	private LocalDate paymentDate;
	private String doctorMail;
	
	public PaymentDetails(String paymentId, String orderId, double amountPaid, LocalDate paymentDate,
			String doctorMail) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
		this.doctorMail = doctorMail;
	}
	public String getDoctorMail() {
		return doctorMail;
	}
	public void setDoctorMail(String doctorMail) {
		this.doctorMail = doctorMail;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public PaymentDetails(String paymentId, String orderId, double amountPaid, LocalDate paymentDate) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
	}
	public PaymentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	

}
