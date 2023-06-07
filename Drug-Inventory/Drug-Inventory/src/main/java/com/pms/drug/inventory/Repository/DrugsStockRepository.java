package com.pms.drug.inventory.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.drug.inventory.Model.DrugsStock;

@Repository
public interface DrugsStockRepository extends MongoRepository<DrugsStock, String> {
	
	List<DrugsStock> findByDrugNameIgnoreCase(String drugName);
	List<DrugsStock> findByExpireDate(LocalDate expireDate);
	List<DrugsStock> findByBatchId(String batchId);

}
