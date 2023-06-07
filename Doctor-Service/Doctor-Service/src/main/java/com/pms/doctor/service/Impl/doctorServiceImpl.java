package com.pms.doctor.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.pms.doctor.service.Exception.DrugNotFoundById;
import com.pms.doctor.service.Exception.DrugNotFoundByname;
import com.pms.doctor.service.Models.Drug;
import com.pms.doctor.service.Models.Order;
import com.pms.doctor.service.Repository.doctorRepository;
import com.pms.doctor.service.Service.doctorService;

@Service
public class doctorServiceImpl implements doctorService {
	
	@Autowired
	private doctorRepository repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private Logger logger= LoggerFactory.getLogger(doctorServiceImpl.class);

	@Override
	public List<Drug> viewAllDrugs() {
		// TODO Auto-generated method stub
		ArrayList<Drug>viewAllDrugs=restTemplate.getForObject("http://DRUG-INVENTORY/drugs/getalldrugs", ArrayList.class);
		logger.info("{}",viewAllDrugs);
		return viewAllDrugs;
	}

	@Override
	public List<Drug> drugByName(String name) {
		
		try {
            ArrayList<Drug> viewAllDrugsByName = restTemplate.getForObject("http://DRUG-INVENTORY/drugs/getdrugbyname/" + name, ArrayList.class);
            logger.info("{}", viewAllDrugsByName);
            return viewAllDrugsByName;
        } catch (HttpClientErrorException.NotFound exception) {
           
            logger.error("Drug not found: {}", exception.getMessage());
            throw new DrugNotFoundByname("Drug Not Found By Given Name !");
        }
	}

	@Override
	public Drug drugById(String id) {
		
	try {
		Drug getDrug=restTemplate.getForObject("http://DRUG-INVENTORY/drugs/getdrugbyid/"+id, Drug.class);
		logger.info("{}",getDrug);
		return getDrug;
	}
	catch (HttpClientErrorException.NotFound exception) {
        
        logger.error("Drug not found: {}", exception.getMessage());
        throw new DrugNotFoundById("Drug Not Found By Given Id !");
    }
	
		
		
	}

	@Override
	public List<Order> viewAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order addOrder(Order orderObj) {
		// TODO Auto-generated method stub
		return null;
	}

}
