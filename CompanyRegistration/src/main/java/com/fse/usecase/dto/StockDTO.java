package com.fse.usecase.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Stock Details", description = "Stock Details to be saved into Data Base")
public class StockDTO {
	@NotNull
	@ApiModelProperty(name = "companyCode", value = "Company Code", dataType = "String", required = true, example = "COMP001")
	private String companyCode;
	
	@NotNull
	@ApiModelProperty(name = "stockPrice", value = "Stock Price", dataType = "Double", required = true, example = "100.50")
	private Double stockPrice;
	
	@NotNull
	@ApiModelProperty(name = "stockDateTime", value = "Stock Date Time", dataType = "Date", required = true, example = "2021-06-29T17:55:46.949+00:00")
	private LocalDateTime stockDateTime;
	
	public StockDTO() {
		
	}
	
	public StockDTO(@NotNull String companyCode, @NotNull Double stockPrice, @NotNull LocalDateTime stockDateTime) {
		super();
		this.companyCode = companyCode;
		this.stockPrice = stockPrice;
		this.stockDateTime = stockDateTime;
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
