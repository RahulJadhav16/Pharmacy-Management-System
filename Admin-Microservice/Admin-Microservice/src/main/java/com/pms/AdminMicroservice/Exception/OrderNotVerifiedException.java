package com.pms.AdminMicroservice.Exception;

public class OrderNotVerifiedException extends RuntimeException {
	
	public OrderNotVerifiedException(String msg)
	{
		super(msg);
	}

}
