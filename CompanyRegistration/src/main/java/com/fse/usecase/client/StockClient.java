package com.fse.usecase.client;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fse.usecase.dto.StockDTO;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class StockClient {

	private static Logger log = LoggerFactory.getLogger(StockClient.class);

	// @HystrixCommand(fallbackMethod = "fallbackMethod")
	public List<StockDTO> getStockDetailsByCompanyCode(String companyCode) {
		String url = "http://localhost:8889/api/v1.0/market/stock/info/" + companyCode;
		ResponseEntity<List<StockDTO>> response = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<StockDTO>>() {
			});
		} catch (Exception e) {
			log.error("There are no stock details present for companyCode"+e.getMessage());
		}
		return Optional.ofNullable(response).map(ResponseEntity::getBody).orElse(null);
	}
	
	public List<StockDTO> getAllStockDetails() {
		String url = "http://localhost:8889/api/v1.0/market/stock/getall";
		ResponseEntity<List<StockDTO>> response = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<StockDTO>>() {
			});
		} catch (Exception e) {
			log.error("There are no stock details present"+e.getMessage());
		}
		return Optional.ofNullable(response).map(ResponseEntity::getBody).orElse(null);
	}

	public List<StockDTO> deleteStockDetailsByCompanyCode(String companyCode) {
		String url = "http://localhost:8889/api/v1.0/market/stock/delete/" + companyCode;
		ResponseEntity<List<StockDTO>> response = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			response = restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<List<StockDTO>>() {
			});
		} catch (Exception e) {
			log.error("There are no stock details present for companyCode to be deleted"+e.getMessage());
		}
		return Optional.ofNullable(response).map(ResponseEntity::getBody).orElse(null);
	}

	/*
	 * @SuppressWarnings("unused") private StockDTO fallbackMethod(String
	 * companyCode) {
	 * log.info("There are no stock details present for companyCode"); return null;
	 * }
	 */

}
