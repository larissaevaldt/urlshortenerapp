package com.larissaevaldt.urlshortenerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.larissaevaldt.urlshortenerapp.model.Url;

/*
 * Interface responsible for Data Access
 * Provides the methods for the CRUD Operations
 */

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{
	

}
