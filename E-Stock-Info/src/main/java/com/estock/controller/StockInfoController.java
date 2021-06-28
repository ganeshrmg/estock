package com.estock.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import
java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estock.entity.StockPriceEntity;
import com.estock.model.Stock;
import com.estock.service.StockService;

@RestController
@RequestMapping(path = "/api/v1.0/market")
public class StockInfoController {

  private static Logger logger =  LoggerFactory.getLogger(StockInfoController.class);

  @Autowired
  private StockService stockService; 
 
  
  @GetMapping(value = "/stock/delete/{companyCode}")
  public String deleteStock(@PathVariable(name = "companyCode", required = true) String companyCode) {
	  String getStatus = stockService.deleteCompany(companyCode);
	  return getStatus;
  } 
  
  @PostMapping(value = "/stock/add/{companyCode}")
  public String addStock(@PathVariable(name = "companyCode", required = true) String companyCode,
		  			@RequestParam(name = "stockPrice", required = true) float stockPrice) {
	  String getStatus = stockService.addStockPrice(companyCode,stockPrice);
	  return getStatus;
	  
  }
  
  @GetMapping(value = "/stock/get/{companyCode}")
  public Map<String,Object> fetchStockPriceList(@PathVariable(name = "companyCode", required = true) String companyCode,
		  @RequestParam(value = "startdate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startdate,
		  @RequestParam(value = "enddate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime enddate){
	  Stock getStock =  stockService.fetchStockPrice(companyCode,startdate,enddate);
	  HashMap <String, Object> getData = new HashMap <> ();
	  if(!getStock.getStockDetails().isEmpty()) {
		  getData.put("data", getStock);
	  }else {
		  getData.put("data", "No Records Found");
	  }	  
	  return getData;
  } 
  
  @GetMapping(value = "/stock/getStock/{companyCode}")
  public List<StockPriceEntity> fetchStockPrice(@PathVariable(name = "companyCode", required = true) String companyCode){
	  List<StockPriceEntity> getStock =  stockService.fetchStock(companyCode);	  
	  return getStock;
  } 
}