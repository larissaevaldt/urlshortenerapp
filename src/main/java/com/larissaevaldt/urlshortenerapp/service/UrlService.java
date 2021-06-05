package com.larissaevaldt.urlshortenerapp.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import com.larissaevaldt.urlshortenerapp.model.Url;
import com.larissaevaldt.urlshortenerapp.repository.UrlRepository;

@Service //a bean that holds the logic 
public class UrlService {
	
	private final UrlRepository urlRepository;
	
	@Autowired
	public UrlService(UrlRepository urlRepository) {
		super();
		this.urlRepository = urlRepository;
	}


	public List<Url> getUrls() {
		return urlRepository.findAll();
	}
	
	public Optional<Url> findByShortUrl(String shortUrl) {
		Optional<Url> urlOptional = urlRepository
				.findUrlsByShortUrl(shortUrl);
		return urlOptional;
	}
	
	public Url save(Url url) {
		Optional<Url> urlOptional = findByShortUrl(url.getShort_url());
		
		if(urlOptional.isPresent()) {
			return null;
		}
		return urlRepository.save(url);	
		
	}
	
	public String generateShortUrl(String longUrl) {

		int counter = 0;
		
		//create a short URL
		String shortUrl = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
		
		//check if the generated URL is already in the database to prevent storing duplicates
		Optional<Url> url = findByShortUrl(shortUrl);
		
		//if shortUrl is already in the db, append a number to the end to create a different code
		while (url.isPresent() ){
			shortUrl = Hashing.murmur3_32().hashString(longUrl.concat(String.valueOf(counter)),
														StandardCharsets.UTF_8).toString();
			counter++;
			url = findByShortUrl(shortUrl);
        } 
		
		return shortUrl;
	}
	
	public boolean validateUrl(String longUrl) {
		
		UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );
		
		if (urlValidator.isValid(longUrl)) {
            return true;
        } else {
        	return false;
        }
	}
	
	
	
}
