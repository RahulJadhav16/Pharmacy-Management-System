package com.pms.doctor.service.Models;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("DoctorInfo")
public class Doctor {
    @Id
    private String doctorId;
    private String name;
    private String contact;
    private String email;
    private String password;
    private String address;
    private String role = "DOCTOR";
    
    

   
	

	public String getRole() {
        return role;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    

   
    

	public Doctor(String doctorId, String name, String contact, String email, String password, String address) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.password = password;
		this.address = address;
	}

	public Doctor() {
        // Default constructor
    }
}
