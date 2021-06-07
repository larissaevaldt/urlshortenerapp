package com.larissaevaldt.urlshortenerapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.larissaevaldt.urlshortenerapp.model.Url;

/**
 * Url Repository Interface Responsible for Data Access. Provides the methods
 * for the CRUD Operations
 * 
 * Created by Larissa Evaldt
 */

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

	/**
	 * Custom function to find Url by the shor_url
	 * 
	 * @param short_url
	 * @return Optional container object with the URL or empty if it does not find
	 */
	@Query("SELECT u FROM Url u WHERE u.short_url = ?1") // SELECT * FROM urls WHERE short_url = the string passed to
															// the method
	Optional<Url> findUrlsByShortUrl(String short_url);
}
