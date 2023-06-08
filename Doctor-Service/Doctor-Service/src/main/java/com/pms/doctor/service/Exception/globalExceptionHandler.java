package com.pms.doctor.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pms.doctor.service.payload.ApiResponse;

@RestControllerAdvice
public class globalExceptionHandler {
	
	@ExceptionHandler(DrugNotFoundByname.class)
	public ResponseEntity<ApiResponse>handelResoceNotFoundByNameException(DrugNotFoundByname e){
		String msg=e.getMessage();
		ApiResponse response=ApiResponse.builder()
				.msg(msg)
				.success(true)
				.status(HttpStatus.OK)
				.build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DrugNotFoundById.class)
	public ResponseEntity<ApiResponse>handelResoceNotFoundByidException(DrugNotFoundById e){
		String msg=e.getMessage();
		ApiResponse response=ApiResponse.builder()
				.msg(msg)
				.success(true)
				.status(HttpStatus.OK)
				.build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UserNotFoundByIDException.class)
	public ResponseEntity<ApiResponse>handelUserNotFoundByIDException(UserNotFoundByIDException e){
		String msg=e.getMessage();
		ApiResponse response=ApiResponse.builder()
				.msg(msg)
				.success(true)
				.status(HttpStatus.OK)
				.build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
	
	
	

}
