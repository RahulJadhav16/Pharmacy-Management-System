package com.pms.AdminMicroservice.Exception;

public class DrugNotFoundByExpiryDate extends RuntimeException {
	

	public DrugNotFoundByExpiryDate()
	{
		System.out.println("Drug Not Found By Given Date !");
		
	}
	
	public DrugNotFoundByExpiryDate(String msg)
	{
		super(msg);
	}


}
