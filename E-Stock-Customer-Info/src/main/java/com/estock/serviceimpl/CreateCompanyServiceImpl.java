package com.estock.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import
org.modelmapper.convention.MatchingStrategies;
import
org.springframework.beans.factory.annotation.Autowired;
import
org.springframework.stereotype.Service;

import com.estock.entity.CompanyEntity;
/*import com.estock.entity.StockPriceEntity; */
import com.estock.model.Company;
import com.estock.model.Stock;
import com.estock.repository.CompanyRepository;
/*import com.estock.repository.StockPriceRepository; */
import
com.estock.service.CreateCompanyService;

@Service
public class CreateCompanyServiceImpl implements CreateCompanyService {

  @Autowired
  CompanyRepository companyRepository;
  
  /*@Autowired
  StockPriceRepository stockPriceRepository; */

  public void createCompany(Company companyinfo) {
    ModelMapper modelmapper =  new ModelMapper();
    modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    CompanyEntity companyEntity = modelmapper.map(companyinfo,CompanyEntity.class);
    companyRepository.save(companyEntity);

  }

  public Optional<CompanyEntity> getComapnyinfo(String code) {
    Optional<CompanyEntity> getComapny = companyRepository.findByCode(code);    
    return getComapny;
  }
  
  public List<CompanyEntity> getAllCompanies(){
	  List<CompanyEntity> getComapnies = (List<CompanyEntity>) companyRepository.findAll();
	  return getComapnies;
  }
  
  public String deleteCompany(String companyCode) {
	  Optional<CompanyEntity> getCompany = companyRepository.findByCode(companyCode);
	  System.out.println(getCompany.get());
	  String result;
	  if(getCompany.isPresent()) {
		  companyRepository.delete(getCompany.get());		  
		  result = "Successfully Deleted Company & Stocks for "+getCompany.get().getName();
	  }else {
		  result = "Company Not Present in Database "+getCompany.get().getName();
		   
	  }
	  return result;
  } 
}