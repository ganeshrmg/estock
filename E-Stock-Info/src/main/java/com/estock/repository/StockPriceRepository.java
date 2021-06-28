package com.estock.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.estock.entity.StockPriceEntity;

public interface StockPriceRepository extends CrudRepository<StockPriceEntity,Long> {

	@Query(value = "Select * from stockprice where code=?1",nativeQuery = true)
	List<StockPriceEntity> findAllByCode(String companyCode);
	
	@Query(value = "SELECT * FROM stockprice WHERE CODE= :code AND CREATEDDATE   >= :startDate AND CREATEDDATE <= :endDate", nativeQuery = true)
	List<StockPriceEntity> getAllBetweenDates(@Param("code") String code,@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
	                                       @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate);

}
