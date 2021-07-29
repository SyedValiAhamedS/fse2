package com.fse.usecase.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fse.usecase.dto.StockDTO;
import com.fse.usecase.dto.StockDTOAggregation;
import com.fse.usecase.service.StockDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/stock")
@Api(value = "Stock Service to save/retrieve stock related details. Also to get list of stocks. ")
@CrossOrigin
public class StockDetailsController {

	private static Logger log = LoggerFactory.getLogger(StockDetailsController.class);

	@Autowired
	StockDetailsService stockDetailsService;

	@PostMapping(value = "/add/{companyCode}")
	@ApiOperation(value = "Company Service to save company related detail.", response = StockDTO.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved stock details"),
			@ApiResponse(code = 400, message = "Invalid request. Please check the request details"),
			@ApiResponse(code = 401, message = "You are not authorized to save the stock information"),
			@ApiResponse(code = 403, message = "Accessing the service you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Requested Service Not Found"),
			@ApiResponse(code = 500, message = "Data was not saved into DB..Internal Error..") })
	public ResponseEntity<StockDTO> saveCompanyRegistrationDetails(@PathVariable String companyCode,
			@RequestBody String stockPrice) {
		log.info("Received POST request to save company details");
		return Optional.ofNullable(stockDetailsService.saveStockDetails(companyCode, stockPrice))
				.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
				.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@GetMapping(value = "/info/{companyCode}")
	@ApiOperation(value = "Stock Service to retrieve stock related detail based on company code", response = StockDTO.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved stock details by company code"),
			@ApiResponse(code = 204, message = "Company Code doesn't exist..", response = Optional.class),
			@ApiResponse(code = 401, message = "You are not authorized to retrieve the information"),
			@ApiResponse(code = 403, message = "Accessing the servie you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Requested Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error.. Please try after some time..") })
	public ResponseEntity<List<StockDTO>> getByCompanyCode(@PathVariable("companyCode") String companyCode) {
		log.info("Received GET request to find stock details by company code");
		return Optional.ofNullable(stockDetailsService.getByCompanyCode(companyCode))
				.map(stockDto -> new ResponseEntity<>(stockDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

	@GetMapping(value = "/getall")
	@ApiOperation(value = "Stock Service to retrieve stock related detail based on company code", response = StockDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved stock details by company code"),
			@ApiResponse(code = 204, message = "Company Code doesn't exist..", response = Optional.class),
			@ApiResponse(code = 401, message = "You are not authorized to retrieve the information"),
			@ApiResponse(code = 403, message = "Accessing the servie you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Requested Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error.. Please try after some time..") })
	public ResponseEntity<List<StockDTO>> getAllStockDetails() {
		log.info("Received GET request to find all stock details");
		List<StockDTO> stockDtos = stockDetailsService.getAllStockDetails();
		if (stockDtos.size() > 0) {
			return new ResponseEntity<>(stockDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping(value = "/delete/{companyCode}")
	@ApiOperation(value = "Stock Service to retrieve stock related detail based on company code", response = StockDTO.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted stock details by company code"),
			@ApiResponse(code = 204, message = "Company Code doesn't exist..", response = Optional.class),
			@ApiResponse(code = 401, message = "You are not authorized to retrieve the information"),
			@ApiResponse(code = 403, message = "Accessing the servie you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Requested Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error.. Please try after some time..") })
	public ResponseEntity<List<StockDTO>> deleteByCompanyCode(@PathVariable("companyCode") String companyCode) {
		log.info("Received DELETE request to delete stock details by company code");
		return Optional.ofNullable(stockDetailsService.deleteByCompanyCode(companyCode))
				.map(companyDto -> new ResponseEntity<>(companyDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

	@GetMapping(value = "/get")
	@ApiOperation(value = "Stock Service to retrieve stock related detail based on company code/start date/end date", response = StockDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved stock details by company code/start date/end date"),
			@ApiResponse(code = 204, message = "Company Code doesn't exist..", response = Optional.class),
			@ApiResponse(code = 401, message = "You are not authorized to retrieve the information"),
			@ApiResponse(code = 403, message = "Accessing the servie you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Requested Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error.. Please try after some time..") })
	public ResponseEntity<StockDTOAggregation> getStockDetailsBasedOnCompanyCodeStartAndEndDate(
			@RequestParam("companyCode") String companyCode,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		log.info("Received GET request to find stock details based on companyCode/start date/end date");
		return Optional
				.ofNullable(stockDetailsService.getStockDetailsBasedOnCompanyCodeStartAndEndDate(companyCode, startDate,
						endDate))
				.map(stockDTOAggregation -> new ResponseEntity<>(stockDTOAggregation, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

}
