package com.pms.AdminMicroservice.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.pms.AdminMicroservice.Exception.DrugAlreadyAddedException;
import com.pms.AdminMicroservice.Exception.DrugNotFoundById;
import com.pms.AdminMicroservice.Model.Drug;
import com.pms.AdminMicroservice.Model.DrugsStock;
import com.pms.AdminMicroservice.Service.DrugStockService;

@Service
public class DrugStockServiceImpl implements DrugStockService{
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<DrugsStock> getAllStock() {
		
		String url="http://DRUG-INVENTORY/stock/getAllStock";
		return restTemplate.getForObject(url, ArrayList.class);
	}

	@Override
	public List<DrugsStock> getByDrugName(String name) {
		
		String url="http://DRUG-INVENTORY/stock/getByDrugName/"+name;
		return restTemplate.getForObject(url, ArrayList.class);
	}

	@Override
	public List<DrugsStock> getByExpireDate(LocalDate date) {
		String url="http://DRUG-INVENTORY/stock/getByExpireDate/"+date;
		
		return restTemplate.getForObject(url, ArrayList.class);
	}

	@Override
	public List<DrugsStock> getByBatchNo(String batchNo) {
		// TODO Auto-generated method stub
		String url="http://DRUG-INVENTORY/stock/getByBatchNo/"+batchNo;
		return restTemplate.getForObject(url, ArrayList.class);
	}

	@Override
	public DrugsStock createStock(DrugsStock obj) {
		// TODO Auto-generated method stub
		String url="http://DRUG-INVENTORY/stock/create";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<DrugsStock> requestEntity = new HttpEntity<>(obj, headers);
		try {
			DrugsStock response= restTemplate.exchange(url, HttpMethod.POST, requestEntity, DrugsStock.class).getBody();
			return response;
		}
		catch(HttpClientErrorException e){
			throw new DrugAlreadyAddedException("Drug Is Already added with the same name!");
			
		}
		
	}

	@Override
	public DrugsStock updateStock(DrugsStock obj) {
		// TODO Auto-generated method stub
		String url="http://DRUG-INVENTORY/stock/update";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<DrugsStock> requestEntity = new HttpEntity<>(obj, headers);
		try {
			DrugsStock response= restTemplate.exchange(url, HttpMethod.PUT, requestEntity, DrugsStock.class).getBody();
			return response;
		}
		catch(HttpClientErrorException e){
			throw new DrugNotFoundById("Drug Not Found By Given Id");
			
		}
	}

	@Override
	public String deleteStock(String id) {
		// TODO Auto-generated method stub
		String url="http://DRUG-INVENTORY/stock/delete/"+id;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(id, headers);
		try {
			String response= restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class).getBody();
			return response;
		}
		catch(HttpClientErrorException e){
			throw new DrugNotFoundById("Drug Not Found By Given Id");
			
		}
		
		
		
	}

}
