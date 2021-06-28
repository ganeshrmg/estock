
  package com.estock.service;
  
  import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.estock.entity.CompanyEntity;
  import com.estock.model.Company;
import com.estock.model.Stock;
  
  
  public interface CreateCompanyService { 
	  void createCompany(Company  companyinfo);  
	  Optional<CompanyEntity> getComapnyinfo(String code);
	List<CompanyEntity> getAllCompanies();
	String deleteCompany(String companyCode);
  
  }
 