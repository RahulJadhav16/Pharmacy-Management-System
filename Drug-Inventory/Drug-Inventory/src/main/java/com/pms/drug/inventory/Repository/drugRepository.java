package com.pms.drug.inventory.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.drug.inventory.Model.Drug;

@Repository
public interface drugRepository extends MongoRepository<Drug, String> {
	
	List<Drug> findByNameIgnoreCase(String name);

}
