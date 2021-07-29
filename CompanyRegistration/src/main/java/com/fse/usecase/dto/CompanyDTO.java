package com.fse.usecase.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Company Details", description = "Company Details to be saved into Data Base")
public class CompanyDTO {
	@NotNull
	@ApiModelProperty(name = "companyCode", value = "Unique Company Code", dataType = "String", required = true, example = "COMP001")
	private String companyCode;
	@NotNull
	@ApiModelProperty(name = "companyName", value = "Company Name", dataType = "String", required = true, example = "Tech Giant")
	private String companyName;
	@NotNull
	@ApiModelProperty(name = "companyCeo", value = "Company CEO", dataType = "String", required = true, example = "Milne")
	private String companyCeo;
	@NotNull
	@Min(100000000)
	@ApiModelProperty(name = "companyTurnover", value = "Company Turnover. Minimum turnover should be 10 cr.", dataType = "long", required = true, example = "100000000")
	private Long companyTurnover;
	@NotNull
	@ApiModelProperty(name = "companyWebsite", value = "Company Website", dataType = "String", required = true, example = "http://mock:123/TechGiant")
	private String companyWebsite;
	@NotNull
	@ApiModelProperty(name = "stockExchange", value = "Stock Exchange", dataType = "String", required = true, example = "BSE")
	private String stockExchange;
	
	public CompanyDTO(@NotNull String companyCode, @NotNull String companyName,
			@NotNull String companyCeo, @NotNull @Min(100000000) Long companyTurnover, @NotNull String companyWebsite,
			@NotNull String stockExchange) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCeo = companyCeo;
		this.companyTurnover = companyTurnover;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCeo() {
		return companyCeo;
	}
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}
	public Long getCompanyTurnover() {
		return companyTurnover;
	}
	public void setCompanyTurnover(Long companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	
	@Override
	public String toString() {
		return "CompanyRegistrationModel [companyCode=" + companyCode + ", companyName=" + companyName + ", companyCeo="
				+ companyCeo + ", companyTurnover=" + companyTurnover + ", companyWebsite=" + companyWebsite
				+ ", stockExchange=" + stockExchange + "]";
	}
	
}
