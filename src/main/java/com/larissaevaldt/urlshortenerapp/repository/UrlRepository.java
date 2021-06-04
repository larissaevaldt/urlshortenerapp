package com.larissaevaldt.urlshortenerapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.larissaevaldt.urlshortenerapp.model.Url;

/*
 * Interface responsible for Data Access
 * Provides the methods for the CRUD Operations
 */

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{
	
	/*
	 * Custom function to find Url by the shor_url
	 * SELECT * FROM urls WHERE short_url equals
	 */
	@Query("SELECT u FROM Url u WHERE u.short_url = ?1")
	Optional<Url> findUrlsByShortUrl(String short_url);
}
