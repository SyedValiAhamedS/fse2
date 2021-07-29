package com.fse.usecase.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Company Stock Details", description = "Company Stock Details to be present in Data Base")
public class CompanyStockDTO {
	
	private CompanyDTO companyDTO;
	private List<StockDTO> stockDTO;
	
	public CompanyStockDTO(CompanyDTO companyDTO, List<StockDTO> stockDTO) {
		super();
		this.companyDTO = companyDTO;
		this.stockDTO = stockDTO;
	}
	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}
	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}
	public List<StockDTO> getStockDTO() {
		return stockDTO;
	}
	public void setStockDTO(List<StockDTO> stockDTO) {
		this.stockDTO = stockDTO;
	}
	
	

}
