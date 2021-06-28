package com.estock.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public class Company {
	
	@NotNull(message="Name must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String name;
	
	
	@NotNull(message="Code must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String code;	
	
	@NotNull(message="CEO Name must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String ceo;
	
	@NotNull(message="Turnover must be Required")
	@Min(value=100000000, message="must be equal or greater than 10 crore") 	
	private int turnover;
	
	@NotNull(message="Website must be Required")
	@Size(min=3,message="Minimum 3 characters Required")	
	private String website;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public int getTurnover() {
		return turnover;
	}

	public void setTurnover(int turnover) {
		this.turnover = turnover;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getStockexchange() {
		return stockexchange;
	}

	public void setStockexchange(String stockexchange) {
		this.stockexchange = stockexchange;
	}

	@NotNull(message="Stockexchange must be Required")
	@Size(min=3,message="Minimum 3 characters Required")
	private String stockexchange;	
	
}
