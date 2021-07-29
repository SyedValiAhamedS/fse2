package com.fse.usecase.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fse.usecase.model.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
	
	Company findByCompanyCode(String companyCode);
	
	Company deleteByCompanyCode(String companyCode);
	
}