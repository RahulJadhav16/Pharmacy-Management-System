package com.pms.doctor.service.Exception;

public class DrugNotFoundByname extends RuntimeException{
	
	public DrugNotFoundByname()
	{
		System.out.println("Drug Not Found By Given Name");
		
	}
	
	public DrugNotFoundByname(String msg)
	{
		super(msg);
	}

}
