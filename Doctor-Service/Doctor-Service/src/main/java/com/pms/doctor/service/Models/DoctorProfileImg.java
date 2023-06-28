package com.pms.doctor.service.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("DoctorProfileImg")
public class DoctorProfileImg {
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
	public DoctorProfileImg(String id, byte[] image) {
		super();
		this.id = id;
		this.image = image;
	}
	public DoctorProfileImg() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
