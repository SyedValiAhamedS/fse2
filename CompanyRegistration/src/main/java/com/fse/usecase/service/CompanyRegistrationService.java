package com.fse.usecase.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.usecase.client.StockClient;
import com.fse.usecase.dto.CompanyDTO;
import com.fse.usecase.dto.CompanyStockDTO;
import com.fse.usecase.dto.StockDTO;
import com.fse.usecase.mapper.CompanyRegistrationMapper;
import com.fse.usecase.repository.CompanyRepository;

@Service
public class CompanyRegistrationService {
	
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	CompanyRegistrationMapper companyRegistrationMapper;
	
	@Autowired
	StockClient stockClient;

	public CompanyDTO saveCompanyRegistrationDetails(CompanyDTO companyDto) {
		return companyRegistrationMapper
				.convertEntityToDto(companyRepository.save(companyRegistrationMapper.convertDtoToEntity(companyDto)));
	}

	public List<CompanyStockDTO> getAllCompanyDetails() {
		List<CompanyDTO> companyDtos = companyRegistrationMapper.convertListOfEntitiesToDtos(companyRepository.findAll());
		List<StockDTO> stockDtos = stockClient.getAllStockDetails();
		System.out.println(stockDtos.size());
		return companyDtos.stream().filter(Objects::nonNull).map(companyDto -> mapStockInformation(companyDto, stockDtos)).collect(Collectors.toList());
	}

	private CompanyStockDTO mapStockInformation(CompanyDTO companyDto, List<StockDTO> stockDtos) {
		if(stockDtos != null && stockDtos.size() > 0) {
			List<StockDTO> stocks = stockDtos.stream().filter(Objects::nonNull)
					.filter(stockDto -> stockDto.getCompanyCode().equals(companyDto.getCompanyCode()))
					.collect(Collectors.toList());
			return new CompanyStockDTO(companyDto, stocks);
		}
		return new CompanyStockDTO(companyDto, Collections.emptyList());
	}

	public CompanyStockDTO getByCompanyCode(String companyCode) {
		CompanyDTO companyDTO = companyRegistrationMapper.convertEntityToDto(companyRepository.findByCompanyCode(companyCode));
		List<StockDTO> stockDTO = stockClient.getStockDetailsByCompanyCode(companyCode);
		return new CompanyStockDTO(companyDTO, stockDTO);
	}

	public CompanyStockDTO deleteByCompanyCode(String companyCode) {
		CompanyDTO companyDTO = companyRegistrationMapper.convertEntityToDto(companyRepository.deleteByCompanyCode(companyCode));
		List<StockDTO> stockDTO = stockClient.deleteStockDetailsByCompanyCode(companyCode);
		return new CompanyStockDTO(companyDTO, stockDTO);
	}

}
