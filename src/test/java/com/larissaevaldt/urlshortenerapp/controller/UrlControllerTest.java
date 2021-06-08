package com.larissaevaldt.urlshortenerapp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import com.larissaevaldt.urlshortenerapp.model.Url;
import com.larissaevaldt.urlshortenerapp.service.UrlService;

@WebMvcTest
public class UrlControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UrlService urlService;
	
	@Test
	public void whenShortUrlValidShouldRedirectToOriginalUrl() throws Exception {
	
		String shortUrl = "12ty678o";

		Url url = new Url(shortUrl, "https://spring.io/guides/gs/testing-web/");
		urlService.save(url);

		this.mockMvc.perform(get("/" + shortUrl)).andExpect(status().is3xxRedirection())
				.andExpect(header().string(HttpHeaders.LOCATION, equalTo(url.getOriginal_url())));

	}
	
}
