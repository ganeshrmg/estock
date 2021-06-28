package com.estock.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "zuul",contextId = "stockinfo")
public interface Stockservice {
		
	@RequestMapping(method=RequestMethod.GET,value = "/stockinfo/api/v1.0/market/stock/delete/{companyCode}")
	public String deleteStock(@PathVariable String companyCode);
}
