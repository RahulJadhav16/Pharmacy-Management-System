package com.pms.AdminMicroservice.Model;

import java.time.LocalDate;
import java.util.ArrayList;




public class Pickup {
    
    private String pickupId;
    private double totalBill;
    private LocalDate pickupdate;
    private boolean paymentStatus;
    private double moneyPaid;
    private ArrayList<Order> Orders=new ArrayList<>();
	public String getPickupId() {
		return pickupId;
	}
	public void setPickupId(String pickupId) {
		this.pickupId = pickupId;
	}
	public double getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}
	public LocalDate getPickupdate() {
		return pickupdate;
	}
	public void setPickupdate(LocalDate pickupdate) {
		this.pickupdate = pickupdate;
	}
	public boolean isPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public double getMoneyPaid() {
		return moneyPaid;
	}
	public void setMoneyPaid(double moneyPaid) {
		this.moneyPaid = moneyPaid;
	}
	public ArrayList<Order> getOrders() {
		return Orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		Orders = orders;
	}
	public Pickup(String pickupId, double totalBill, LocalDate pickupdate, boolean paymentStatus, double moneyPaid,
			ArrayList<Order> orders) {
		super();
		this.pickupId = pickupId;
		this.totalBill = totalBill;
		this.pickupdate = pickupdate;
		this.paymentStatus = paymentStatus;
		this.moneyPaid = moneyPaid;
		Orders = orders;
	}
	public Pickup() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Pickup [pickupId=" + pickupId + ", totalBill=" + totalBill + ", pickupdate=" + pickupdate
				+ ", paymentStatus=" + paymentStatus + ", moneyPaid=" + moneyPaid + ", Orders=" + Orders + "]";
	}
    
	
    
	
	
	
    
	
    
	
}
