package com.pms.drug.inventory.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.drug.inventory.Model.DrugImges;

@Repository
public interface drugImgesRepository extends MongoRepository<DrugImges, String>{

}
