package com.pms.AdminMicroservice.Exception;

public class UserEmailIdAlreadyPresentException extends RuntimeException {
	
	public UserEmailIdAlreadyPresentException(String msg)
	{
		super(msg);
	}

}
