package com.fse.usecase.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.usecase.dto.StockDTO;
import com.fse.usecase.dto.StockDTOAggregation;
import com.fse.usecase.mapper.StockDetailsMapper;
import com.fse.usecase.model.Stock;
import com.fse.usecase.repository.StockDetailsRepository;

@Service
public class StockDetailsService {

	@Autowired
	StockDetailsRepository stockDetailsRepository;

	@Autowired
	StockDetailsMapper stockDetailsMapper;

	public StockDTO saveStockDetails(String companyCode, String stockPrice) {
		JSONObject jsonObject = new JSONObject(stockPrice);
		Stock stock = new Stock(companyCode, Double.parseDouble(jsonObject.getString("stockPrice")),
				LocalDateTime.now());
		return stockDetailsMapper.convertEntityToDto(stockDetailsRepository.save(stock));
	}

	public List<StockDTO> getByCompanyCode(String companyCode) {
		return stockDetailsMapper.convertListOfEntitiesToDtos(stockDetailsRepository.findByCompanyCode(companyCode));
	}

	public List<StockDTO> getAllStockDetails() {
		return stockDetailsMapper.convertListOfEntitiesToDtos(stockDetailsRepository.findAll());
	}

	public List<StockDTO> deleteByCompanyCode(String companyCode) {
		return stockDetailsMapper.convertListOfEntitiesToDtos(stockDetailsRepository.deleteByCompanyCode(companyCode));
	}

	public StockDTOAggregation getStockDetailsBasedOnCompanyCodeStartAndEndDate(String companyCode, Date startDate,
			Date endDate) {
		List<StockDTO> stockDtos = stockDetailsMapper.convertListOfEntitiesToDtos(stockDetailsRepository
				.getStockDetailsBasedOnCompanyCodeStartAndEndDate(companyCode, startDate, endDate));

		StockDTOAggregation stockDTOAggregation = null;
		if (null != stockDtos && stockDtos.size() > 0) {
			stockDTOAggregation = new StockDTOAggregation();
			stockDTOAggregation.setStockDtos(stockDtos);
			DoubleSummaryStatistics summaryStats = stockDtos.stream().mapToDouble(StockDTO::getStockPrice)
					.summaryStatistics();
			stockDTOAggregation.setMinStockPrice(summaryStats.getMin());
			stockDTOAggregation.setMaxStockPrice(summaryStats.getMax());
			stockDTOAggregation.setAvgStockPrice(summaryStats.getAverage());
		}
		return stockDTOAggregation;
	}

}
