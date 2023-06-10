package com.pms.pickup.service.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
@Document("DrugsStock")
public class DrugsStock {
	
	@Id
	private String id;
	private String supplierEmailId;
	private String drugName;
	private int quantity;
	private String batchId;
	private Double price;
	private LocalDate expireDate;
	private String status;
	
	
	public DrugsStock(String id, String supplierEmailId, String drugName, int quantity, String batchId, Double price,
			LocalDate expireDate, String status) {
		super();
		this.id = id;
		this.supplierEmailId = supplierEmailId;
		this.drugName = drugName;
		this.quantity = quantity;
		this.batchId = batchId;
		this.price = price;
		this.expireDate = expireDate;
		this.status = status;
	}
	
	public DrugsStock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSupplierEmailId() {
		return supplierEmailId;
	}
	public void setSupplierEmailId(String supplierEmailId) {
		this.supplierEmailId = supplierEmailId;
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
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDate getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
