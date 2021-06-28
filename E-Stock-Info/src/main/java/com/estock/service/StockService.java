
  package com.estock.service;
  
  import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.estock.entity.StockPriceEntity;
import com.estock.model.Stock;
  
  
  public interface StockService {	
	String addStockPrice(String companyCode,float stockPrice);
	Stock fetchStockPrice(String companyCode, LocalDateTime startdate, LocalDateTime enddate);
	String deleteCompany(String companyCode);
	List<StockPriceEntity> fetchStock(String companyCode);
  
  }
 