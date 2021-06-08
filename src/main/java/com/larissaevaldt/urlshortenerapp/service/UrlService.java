package com.larissaevaldt.urlshortenerapp.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import com.larissaevaldt.urlshortenerapp.model.Url;
import com.larissaevaldt.urlshortenerapp.repository.UrlRepository;

/**
 * Url Service
 *
 * Created by Larissa Evaldt
 */

@Service // a bean that holds the logic
public class UrlService {

	private final UrlRepository urlRepository;

	@Autowired
	public UrlService(UrlRepository urlRepository) {
		super();
		this.urlRepository = urlRepository;
	}

	/**
	 * Save a URL object in the database.
	 *
	 * @param url is a complete Url object with long and short URL, the id is
	 *            automatically generated.
	 * @return the saved object or null.
	 */
	public Url save(Url url) {
		// check if there is already an item with the short code passed to avoid storing
		// duplicates
		Optional<Url> urlOptional = findByShortUrl(url.getShort_url());
		if (urlOptional.isPresent()) {
			return null;
		}

		return urlRepository.save(url);

	}

	/**
	 * Generate a short url code.
	 *
	 * @param longUrl is the URL from which to generate the short code.
	 * @return a String containing the generated short code.
	 */
	public String generateShortUrl(String longUrl) {

		int counter = 0;

		// create a short URL
		String shortUrl = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();

		// check if the generated URL is already in the database to prevent storing
		// duplicates
		Optional<Url> url = findByShortUrl(shortUrl);

		// if shortUrl is already in the db, append a number to the end to create a
		// different code
		while (url.isPresent()) {
			shortUrl = Hashing.murmur3_32().hashString(longUrl.concat(String.valueOf(counter)), StandardCharsets.UTF_8)
					.toString();
			counter++;
			url = findByShortUrl(shortUrl);
		}

		return shortUrl;
	}

	/**
	 * Validates if URL is valid using the UrlValidator class of Apache commons
	 * validator.
	 *
	 * @param longUrl is the URL to validate
	 * @return true if URL is valid and false if it is not.
	 */
	public boolean validateUrl(String longUrl) {

		UrlValidator urlValidator = new UrlValidator(new String[] { "http", "https" });

		if (urlValidator.isValid(longUrl)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if there is an object with the given short URL code in the database
	 *
	 * @param shortUrl is the short URL code to search
	 * @return Optional object container containing the URL object or empty if the
	 *         short code is not in the database.
	 */
	public Optional<Url> findByShortUrl(String shortUrl) {
		Optional<Url> urlOptional = urlRepository.findUrlsByShortUrl(shortUrl);
		return urlOptional;
	}

}