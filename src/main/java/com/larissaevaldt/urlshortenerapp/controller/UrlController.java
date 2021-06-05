package com.larissaevaldt.urlshortenerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larissaevaldt.urlshortenerapp.model.Url;
import com.larissaevaldt.urlshortenerapp.service.UrlService;

@RestController
@RequestMapping(path = "api/v1")
public class UrlController {
	
	private final UrlService urlService;
	
	@Autowired //automatically instantiate urlService and inject in the constructor
	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}

	@GetMapping
	public List<Url> hello() {
		return urlService.getUrls();
	}
	
	@PostMapping()
	public void registerNewUrl(@RequestBody Url url) {
		urlService.save(url);
	}
	
	@PostMapping("/short")
	public ResponseEntity<Url> shortenUrl(@RequestBody String originalUrl) {
		String shortUrl = urlService.generateShortUrl(originalUrl);
		Url url = new Url(originalUrl, shortUrl);
		Url savedUrl = urlService.save(url);
		return ResponseEntity.ok(savedUrl);
		
	}
	
}
