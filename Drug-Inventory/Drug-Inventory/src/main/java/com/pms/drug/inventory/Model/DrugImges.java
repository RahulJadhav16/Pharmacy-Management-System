package com.pms.drug.inventory.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("drugsImges")
public class DrugImges {
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
	public DrugImges(String id, byte[] image) {
		super();
		this.id = id;
		this.image = image;
	}
	public DrugImges() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
