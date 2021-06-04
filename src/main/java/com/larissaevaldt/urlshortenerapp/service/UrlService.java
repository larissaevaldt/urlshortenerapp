package com.larissaevaldt.urlshortenerapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larissaevaldt.urlshortenerapp.model.Url;
import com.larissaevaldt.urlshortenerapp.repository.UrlRepository;

@Service //this is a bean that holds logic 
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
	
	public void addNewUrl(Url url) {
		Optional<Url> urlOptional = urlRepository
				.findUrlsByShortUrl(url.getShort_url());
		if(urlOptional.isPresent()) {
			throw new IllegalStateException("short url taken");
		}
		urlRepository.save(url);
		
	}
	
	
}
