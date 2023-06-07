package com.pms.drug.inventory.Exception;

public class InvalidexpireDate extends RuntimeException{
	
	public InvalidexpireDate()
	{
		System.out.println("Expire date should be greater than todays date !");
		
	}
	
	public InvalidexpireDate(String msg)
	{
		super(msg);
	}

}
