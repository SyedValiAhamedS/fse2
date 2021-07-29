package com.fse.usecase.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fse.usecase.model.Stock;

@Repository
public interface StockDetailsRepository extends MongoRepository<Stock, String> {

	List<Stock> findByCompanyCode(String companyCode);

	List<Stock> deleteByCompanyCode(String companyCode);
	
	@Query("{$and :[{'companyCode' : { $eq: ?0 } },{'stockDateTime' : { $gte: ?1, $lte: ?2 } } ]}")                 
	public List<Stock> getStockDetailsBasedOnCompanyCodeStartAndEndDate(String companyCode, Date fromDate, Date toDate);
	
}
