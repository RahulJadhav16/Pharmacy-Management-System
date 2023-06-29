package com.pms.AdminMicroservice.Model;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="admin-profile-img")
public class AdminProfileImg {
	@Id
    private String id;
	private byte[] image;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public AdminProfileImg(String id, byte[] image) {
		super();
		this.id = id;
		this.image = image;
	}
	public AdminProfileImg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
