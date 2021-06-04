package com.larissaevaldt.urlshortenerapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.larissaevaldt.urlshortenerapp.model.Url;

@Service //this is a bean that holds logic 
public class UrlService {
	
	public List<Url> getUrls() {
		return List.of(
				new Url(1L,"https://dzone.com/articles/spring-boot-and-postgresql","1234573")
		);
	}
}
