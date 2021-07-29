package com.fse.usecase.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.fse.usecase.dto.CompanyDTO;
import com.fse.usecase.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyRegistrationMapper {
	Company convertDtoToEntity(CompanyDTO companyDto);
	
	CompanyDTO convertEntityToDto(Company company);
	
	List<CompanyDTO> convertListOfEntitiesToDtos(List<Company> company);
}
