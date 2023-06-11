package com.pms.AdminMicroservice.Impl;

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
import com.pms.AdminMicroservice.Service.DrugCatalogueService;

@Service
public class DrugCatalogueServiceImpl implements DrugCatalogueService{
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Drug> getalldrugs() {
		// TODO Auto-generated method stub
		String url="http://DRUG-INVENTORY/drugs/getalldrugs";
		return restTemplate.getForObject(url, ArrayList.class);
	}

	@Override
	public Drug getdrugbyid(String id) {
		
		String url="http://DRUG-INVENTORY/drugs/getdrugbyid/"+id;
		return restTemplate.getForObject(url, Drug.class);
	}

	@Override
	public List<Drug> getdrugbyname(String name) {
		
		String url="http://DRUG-INVENTORY/drugs/getdrugbyname/"+name;
		return restTemplate.getForObject(url, ArrayList.class);
	}

	@Override
	public Drug createdrug(Drug obj) {
		String url="http://DRUG-INVENTORY/drugs/createdrug";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Drug> requestEntity = new HttpEntity<>(obj, headers);
		try {
		Drug response=restTemplate.exchange(url, HttpMethod.POST, requestEntity, Drug.class).getBody();
		return response;
		}
		catch (HttpClientErrorException e) {
			throw new DrugAlreadyAddedException("Drug Is Already added with the same name!");
			
		}
		
	}

	@Override
	public Drug updatedrug(Drug obj) {
		String url="http://DRUG-INVENTORY/drugs/updatedrug";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Drug> requestEntity = new HttpEntity<>(obj, headers);
		
		try {
			Drug response=restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Drug.class).getBody();
			return response;
		}
		catch (HttpClientErrorException e) {
			
			throw new DrugNotFoundById("Drug Not Found By Given Id !");
		}
	}

	@Override
	public String deletedrug(String id) {
		String url="http://DRUG-INVENTORY/drugs/deletedrug/"+id;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(id, headers);
		try {
			String response=restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class).getBody();
			return response;
		}
		catch (HttpClientErrorException e) {
			
			throw new DrugNotFoundById("Drug Not Found By Given Id !");
		}
		
		
	}

}
