package com.estock.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.bson.types.ObjectId;
import org.hibernate.annotations.Where;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Entity
@Document(collection ="company")
@Data
public class CompanyEntity implements Serializable {	

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

	public String getTurnover() {
		return turnover;
	}

	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}

	public String getStockexchange() {
		return stockexchange;
	}

	public void setStockexchange(String stockexchange) {
		this.stockexchange = stockexchange;
	}

	private static final long serialVersionUID = 4518290119722159053L;
	
	@Id
    private ObjectId id;

	@Column(name="NAME")
    private String name;

	@Column(name="CODE")
	private String code;
	
	@Column(name="CEO")
	private String ceo;
	
	@Column(name = "TURNOVER")
	private String turnover;
	
	@Column(name = "STOCKEXCHANGE")
	private String stockexchange;
	
	@Column(name = "WEBSITE")
	private String website;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
/*	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="CODE",referencedColumnName = "CODE",insertable = false,updatable = false)
    @OrderBy(value = " CREATEDDATE DESC")
	private List<StockPriceEntity> stockPrice;

	public List<StockPriceEntity> getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(List<StockPriceEntity> stockPrice) {
		this.stockPrice = stockPrice;
	} */

}
