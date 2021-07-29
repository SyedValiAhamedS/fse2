package com.fse.usecase.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.usecase.dto.CompanyDTO;
import com.fse.usecase.dto.CompanyStockDTO;
import com.fse.usecase.service.CompanyRegistrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/company")
@Api(value = "Company Service to save/retrieve company related details. Also to get list of companies. ")
@CrossOrigin
public class CompanyRegistrationController {
	
	private static Logger log = LoggerFactory.getLogger(CompanyRegistrationController.class);

	@Autowired
	CompanyRegistrationService companyRegistrationService;

	@PostMapping(value = "/register")
	@ApiOperation(value = "Company Service to save company related detail.", response = CompanyDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved company details"),
			@ApiResponse(code = 400, message = "Invalid request. Please check the request details"),
			@ApiResponse(code = 401, message = "You are not authorized to save the company information"),
			@ApiResponse(code = 403, message = "Accessing the servie you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Requested Service Not Found"),
			@ApiResponse(code = 500, message = "Data was not saved into DB..One reason might be companyCode column must be unique.") })
	public ResponseEntity<CompanyDTO> saveCompanyRegistrationDetails(@Valid @RequestBody CompanyDTO companyDto) {
		log.info("Received POST request to save company details");
		return Optional.ofNullable(companyRegistrationService.saveCompanyRegistrationDetails(companyDto))
				.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
				.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@GetMapping(value = "/getall")
	@ApiOperation(value = "Company Service to retrieve company related details.", response = CompanyStockDTO.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all company details"),
			@ApiResponse(code = 204, message = "No company detail records are found..", response = List.class),
			@ApiResponse(code = 401, message = "You are not authorized to save the company information"),
			@ApiResponse(code = 403, message = "Accessing the servie you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Requested Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error.. Please try after some time..") })
	public ResponseEntity<List<CompanyStockDTO>> getAllCompanyDetails() {
		log.info("Received GET request to retrieve all company details");
		List<CompanyStockDTO> companyStockDtos = companyRegistrationService.getAllCompanyDetails();
		if (companyStockDtos.size() > 0) {
			return new ResponseEntity<>(companyStockDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/info/{companyCode}")
	@ApiOperation(value = "Company Service to retrieve company related detail based on company code", response = CompanyStockDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved company details by company code"),
			@ApiResponse(code = 204, message = "Company Code doesn't exist..", response = Optional.class),
			@ApiResponse(code = 401, message = "You are not authorized to retrieve the company information"),
			@ApiResponse(code = 403, message = "Accessing the servie you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Requested Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error.. Please try after some time..") })
	public ResponseEntity<CompanyStockDTO> getByCompanyCode(@PathVariable("companyCode") String companyCode) {
		log.info("Received GET request to find company details by company code");
		return Optional.ofNullable(companyRegistrationService.getByCompanyCode(companyCode))
				.map(companyDto -> new ResponseEntity<>(companyDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>( HttpStatus.NO_CONTENT));
	}
	
	@DeleteMapping(value = "/delete/{companyCode}")
	@ApiOperation(value = "Company Service to delete company related detail based on company code", response = CompanyStockDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted company details by company code"),
			@ApiResponse(code = 204, message = "Company Code doesn't exist..", response = Optional.class),
			@ApiResponse(code = 401, message = "You are not authorized to retrieve the company information"),
			@ApiResponse(code = 403, message = "Accessing the servie you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Requested Service Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error.. Please try after some time..") })
	public ResponseEntity<CompanyStockDTO> deleteByCompanyCode(@PathVariable("companyCode") String companyCode) {
		log.info("Received DELETE request to delete company details by company code");
		return Optional.ofNullable(companyRegistrationService.deleteByCompanyCode(companyCode))
				.map(companyDto -> new ResponseEntity<>(companyDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>( HttpStatus.NO_CONTENT));
	}

}
