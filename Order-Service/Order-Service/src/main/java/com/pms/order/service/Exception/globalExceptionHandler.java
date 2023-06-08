package com.pms.order.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pms.order.service.payload.ApiResponse;





@RestControllerAdvice
public class globalExceptionHandler {
	
	@ExceptionHandler(ordersNotFoundException.class)
	public ResponseEntity<ApiResponse>handelOrdersNotFoundException(ordersNotFoundException e){
		String msg=e.getMessage();
		ApiResponse response=ApiResponse.builder()
				.msg(msg)
				.success(true)
				.status(HttpStatus.OK)
				.build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
	
	
	
	
	

}
