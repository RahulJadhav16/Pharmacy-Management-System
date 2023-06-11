package com.pms.AdminMicroservice.Exception;

public class DrugAlreadyAddedException extends RuntimeException{
	
	public DrugAlreadyAddedException()
	{
		System.out.println("Drug is already addded !!");
		
	}
	
	public DrugAlreadyAddedException(String msg)
	{
		super(msg);
	}

}
