package com.fse.usecase.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Stock Details", description = "Stock Details returned to UI screen")
public class StockDTOAggregation {
	@NotNull
	@ApiModelProperty(name = "stockDtos", value = "List of Stock Details", dataType = "List", required = true)
	private List<StockDTO> stockDtos;
	
	@NotNull
	@ApiModelProperty(name = "minStockPrice", value = "Minimum Stock Price", dataType = "Double", required = true, example = "100.00")
	private Double minStockPrice;
	
	@NotNull
	@ApiModelProperty(name = "maxStockPrice", value = "Maximum Stock Price", dataType = "Double", required = true, example = "100.00")
	private Double maxStockPrice;
	
	@NotNull
	@ApiModelProperty(name = "avgStockPrice", value = "Average Stock Price", dataType = "Double", required = true, example = "100.00")
	private Double avgStockPrice;
	
	public StockDTOAggregation() {
		
	}
	
	public StockDTOAggregation(@NotNull List<StockDTO> stockDtos, @NotNull Double minStockPrice,
			@NotNull Double maxStockPrice, @NotNull Double avgStockPrice) {
		super();
		this.stockDtos = stockDtos;
		this.minStockPrice = minStockPrice;
		this.maxStockPrice = maxStockPrice;
		this.avgStockPrice = avgStockPrice;
	}

	public List<StockDTO> getStockDtos() {
		return stockDtos;
	}


	public void setStockDtos(List<StockDTO> stockDtos) {
		this.stockDtos = stockDtos;
	}


	public Double getMinStockPrice() {
		return minStockPrice;
	}

	public void setMinStockPrice(Double minStockPrice) {
		this.minStockPrice = minStockPrice;
	}

	public Double getMaxStockPrice() {
		return maxStockPrice;
	}

	public void setMaxStockPrice(Double maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}

	public Double getAvgStockPrice() {
		return avgStockPrice;
	}

	public void setAvgStockPrice(Double avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}
	
}
