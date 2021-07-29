package com.fse.usecase.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.fse.usecase.dto.StockDTO;
import com.fse.usecase.model.Stock;

@Mapper(componentModel = "spring")
public interface StockDetailsMapper {
	
	StockDTO convertEntityToDto(Stock stock);
	
	Stock convertDtoToEntity(StockDTO stockDto);
	
	List<StockDTO> convertListOfEntitiesToDtos(List<Stock> stocks);
	
}
