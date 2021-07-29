package com.fse.usecase.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stock")
public class Stock {
	@Id
    private String id;
	private String companyCode;
	private Double stockPrice;
	private LocalDateTime stockDateTime;
	
	public Stock(String companyCode, Double stockPrice, LocalDateTime stockDateTime) {
		this.companyCode = companyCode;
		this.stockPrice = stockPrice;
		this.stockDateTime = stockDateTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public Double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public LocalDateTime getStockDateTime() {
		return stockDateTime;
	}
	public void setStockDateTime(LocalDateTime stockDateTime) {
		this.stockDateTime = stockDateTime;
	}
	
}
