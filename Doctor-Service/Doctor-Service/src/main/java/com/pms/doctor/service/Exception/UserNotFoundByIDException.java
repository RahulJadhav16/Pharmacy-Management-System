package com.pms.doctor.service.Exception;

public class UserNotFoundByIDException extends RuntimeException{
	
	public UserNotFoundByIDException(String msg)
	{
		super(msg);
	}

}
