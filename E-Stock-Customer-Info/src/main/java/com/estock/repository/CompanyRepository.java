package com.estock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.*;

import com.estock.entity.CompanyEntity;

public interface CompanyRepository extends MongoRepository<CompanyEntity,Long> {

	Optional<CompanyEntity> findByCode(String code);

}
