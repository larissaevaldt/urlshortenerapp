package com.larissaevaldt.urlshortenerapp.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.larissaevaldt.urlshortenerapp.model.Url;
import com.larissaevaldt.urlshortenerapp.repository.UrlRepository;

@Service //this is a bean that holds the logic 
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
	
	public void addNewUrl(Url url) {
		Optional<Url> urlOptional = findByShortUrl(url.getShort_url());
		
		if(urlOptional.isPresent()) {
			throw new IllegalStateException("short url taken");
		}
		urlRepository.save(url);	
	}
	
	public String generateShortUrl(String longUrl) {

		int counter = 0;
		System.out.println("longUrl = " + longUrl + ", counter = " + counter);
		//get the short URL
		String shortUrl = Hashing.murmur3_32().hashString(longUrl, StandardCharsets.UTF_8).toString();
		System.out.println("shortUrl = " + shortUrl); 
		//check if the generated URL is already in the database to prevent storing duplicates
		Optional<Url> url = findByShortUrl(shortUrl);
		System.out.println(url);
		while (url.isPresent() ){
			shortUrl = Hashing.murmur3_32().hashString(longUrl.concat(String.valueOf(counter)),
														StandardCharsets.UTF_8).toString();
			counter++;
			url = findByShortUrl(shortUrl);
        } 
		
		Url urlToSave = new Url(longUrl, shortUrl);
		addNewUrl(urlToSave);
		return shortUrl;
	}
	
	
	
}
