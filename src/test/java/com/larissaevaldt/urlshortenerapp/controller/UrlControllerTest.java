package com.larissaevaldt.urlshortenerapp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.larissaevaldt.urlshortenerapp.model.Url;
import com.larissaevaldt.urlshortenerapp.service.UrlService;


@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class UrlControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

    @Autowired
    private UrlService urlService;
	
	
	@Test
	public void whenShortUrlValidShouldRedirectToOriginalUrl() throws Exception {
		// given
		String longUrl = "https://spring.io/guides/gs/testing-web/";
		String shortUrl = urlService.generateShortUrl(longUrl);

		Url url = new Url(longUrl, shortUrl);
		urlService.save(url);
		// when and then
		this.mockMvc.perform(get("/api/v1/" + shortUrl)).andExpect(status().is3xxRedirection())
				.andExpect(header().string(HttpHeaders.LOCATION, equalTo(longUrl)));

	}
	
	@Test
	public void whenShortUrlInvalidShouldReturnNotFound() throws Exception {
		// given
		String shortUrlNotInDb = "hays78293";

		// When and Then
		this.mockMvc.perform(get("/api/v1/" + shortUrlNotInDb)).andExpect(status().isNotFound());

	}
	
	
	
}
