package com.pms.drug.inventory.Exception;

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
