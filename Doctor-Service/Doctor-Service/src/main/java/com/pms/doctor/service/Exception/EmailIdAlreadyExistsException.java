package com.pms.doctor.service.Exception;

public class EmailIdAlreadyExistsException extends RuntimeException {
	
	public EmailIdAlreadyExistsException(String msg)
	{
		super(msg);
	}

}
