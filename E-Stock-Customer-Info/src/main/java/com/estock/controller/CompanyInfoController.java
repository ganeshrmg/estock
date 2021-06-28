package com.estock.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import
java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.client.RestTemplate;

import com.estock.entity.CompanyEntity;
import com.estock.entity.StockPriceEntity;
//import com.estock.entity.StockPriceEntity;
import com.estock.model.Company;
import com.estock.model.Result;
import com.estock.model.Stock;
import com.estock.model.Stockservice;
import com.estock.repository.CompanyRepository;
import com.estock.service.CreateCompanyService;
import com.estock.service.RestTemplateClass;

@RestController
@RequestMapping(path = "/api/v1.0/market")
public class CompanyInfoController {

  private static Logger logger =  LoggerFactory.getLogger(CompanyInfoController.class);
  
  final String baseUrl = "http://localhost:8084/api/v1.0/market/stock/";

  @Autowired
  private CreateCompanyService createCompanyService;

  @Autowired
  private CompanyRepository companyRepository;
  
  @Autowired
  private Stockservice stockservice; 
  
  @Autowired
  private RestTemplateClass restTemplate; 

  @PostMapping(value = "/company/register")
  public ResponseEntity <Result>createCompany(@Valid @RequestBody Company companyinfo, BindingResult bindingResult) {
	  Result geterror = new Result();
	  if (bindingResult.getErrorCount() > 0) {
    	  List<FieldError> errors = bindingResult.getFieldErrors();
    	  List<String> message = new ArrayList<>();    	      	 
    	  geterror.setCode(-2);
          for (FieldError e : errors){
              message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
          }
          geterror.setMessage("Create Failed");
          geterror.setCause(message.toString());          
        return  ResponseEntity.ok(geterror);
      } else {        
        ModelMapper modelmapper = new ModelMapper();
        modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);        
        String companyCode = companyinfo.getCode();       
        Optional<CompanyEntity> checkCode = createCompanyService.getComapnyinfo(companyCode);
        if (!checkCode.isPresent()) {
          Company customerdata = modelmapper.map(companyinfo, Company.class);         
          createCompanyService.createCompany(customerdata);
          geterror.setMessage("New Company is Registered");         
        } else {
        	geterror.setMessage("Comapny Code Already Exists");          
        }
        return ResponseEntity.ok(geterror);
      }

    }

  @GetMapping(value = "/company/info/{companyCode}")  
  public Map < String, Object > getCompany(@PathVariable(name = "companyCode", required = true) String companyCode) throws URISyntaxException {
    HashMap <String, Object> getData = new HashMap <> ();
    Optional<CompanyEntity> companyinfo =  createCompanyService.getComapnyinfo(companyCode); 
    URI uri = new URI(baseUrl+"getStock/"+companyCode);
   // ResponseEntity<StockPriceEntity[]> result = restTemplate.getForEntity(uri, StockPriceEntity[].class);
  //  StockPriceEntity[] userArray = result.getBody();    
   // List<StockPriceEntity> getStock = Arrays.stream(userArray).collect(Collectors.toList()); 
   
    if (companyinfo.isPresent()) {
      getData.put("companyData", companyinfo.get());
   //   getData.put("stockData", getStock);
    }else {
      getData.put("data", "No Records Found");
    }
    return getData;
  }
  
  @GetMapping(value = "/company/getall")
  public Map<String,Object> getAllCompanies() throws URISyntaxException{
	  HashMap <String, Object> getData = new HashMap <> ();
	  List<CompanyEntity> getCompanies = createCompanyService.getAllCompanies();
	  
	  List<Object> companyData = new ArrayList<Object>();
	  
	  getCompanies.forEach(data ->{	
		  HashMap<String, Object> getAlldata = new HashMap<String, Object>();
		try {
			URI uri = new URI(baseUrl+"getStock/"+data.getCode());
			ResponseEntity<StockPriceEntity[]> result = restTemplate.getForEntity(uri, StockPriceEntity[].class);
		    StockPriceEntity[] userArray = result.getBody();    
		    List<StockPriceEntity> getStock = Arrays.stream(userArray).collect(Collectors.toList());
		    
		    getAlldata.put("companyData", data);
		    getAlldata.put("stockData", getStock);
		    companyData.add(getAlldata);		    
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	  });   
	  
		
	if(!getCompanies.isEmpty()) { 
	    getData.put("data", companyData); 
	}else {
	    getData.put("data", "No Records Found");
	 }
		 
	  return getData;
  }
  
  @GetMapping(value = "/company/delete/{companyCode}")
  public String delete(@PathVariable(name = "companyCode", required = true) String companyCode) throws URISyntaxException {
	  String getStatus = createCompanyService.deleteCompany(companyCode);
	  URI uri = new URI(baseUrl+"delete/"+companyCode);	   
	  ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);	  
	  return getStatus;
  }  

 
}