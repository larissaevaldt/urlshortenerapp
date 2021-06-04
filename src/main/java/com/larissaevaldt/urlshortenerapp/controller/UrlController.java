package com.larissaevaldt.urlshortenerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
