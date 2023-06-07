package com.pms.drug.inventory.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pms.drug.inventory.Payload.ApiResponse;



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
	
	@ExceptionHandler(DrugAlreadyAddedException.class)
	public ResponseEntity<ApiResponse>handelResoceAlreaadyAddedException(DrugAlreadyAddedException e){
		String msg=e.getMessage();
		ApiResponse response=ApiResponse.builder()
				.msg(msg)
				.success(true)
				.status(HttpStatus.OK)
				.build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.CONFLICT);
		
	}
	
	
	

	@ExceptionHandler(DrugNotFoundByExpiryDate.class)
	public ResponseEntity<ApiResponse>handelDrugNotFoundByExpiryDateException(DrugNotFoundByExpiryDate e){
		String msg=e.getMessage();
		ApiResponse response=ApiResponse.builder()
				.msg(msg)
				.success(true)
				.status(HttpStatus.OK)
				.build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DrugNotFoundByBatchId.class)
	public ResponseEntity<ApiResponse>handelDrugNotFoundByBatchIdException(DrugNotFoundByBatchId e){
		String msg=e.getMessage();
		ApiResponse response=ApiResponse.builder()
				.msg(msg)
				.success(true)
				.status(HttpStatus.OK)
				.build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(InvalidexpireDate.class)
	public ResponseEntity<ApiResponse>handelInvalidexpireDateException(InvalidexpireDate e){
		String msg=e.getMessage();
		ApiResponse response=ApiResponse.builder()
				.msg(msg)
				.success(true)
				.status(HttpStatus.OK)
				.build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.CONFLICT);
		
	}
	

}
