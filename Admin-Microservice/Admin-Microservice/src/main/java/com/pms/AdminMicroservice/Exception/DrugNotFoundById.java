package com.pms.AdminMicroservice.Exception;

public class DrugNotFoundById extends RuntimeException {
	
	public DrugNotFoundById()
	{
		System.out.println("Drug Not Found By Given Id");
		
	}
	
	public DrugNotFoundById(String msg)
	{
		super(msg);
	}

}
