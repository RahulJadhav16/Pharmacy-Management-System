package com.pms.drug.inventory.Exception;

public class DrugNotFoundByBatchId extends RuntimeException{
	
	public DrugNotFoundByBatchId()
	{
		System.out.println("Drugs are  not found by given Batch id !");
		
	}
	
	public DrugNotFoundByBatchId(String msg)
	{
		super(msg);
	}

}
