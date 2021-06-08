package com.larissaevaldt.urlshortenerapp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larissaevaldt.urlshortenerapp.dto.UrlDto;
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
	 private ObjectMapper objectMapper;

    @Autowired
    private UrlService urlService;
	
	
	@Test
	void whenShortUrlValidShouldRedirectToOriginalUrl() throws Exception {
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
	void whenShortUrlInvalidShouldReturnNotFound() throws Exception {
		// given
		String shortUrlNotInDb = "hays78293";

		// When and Then
		this.mockMvc.perform(get("/api/v1/" + shortUrlNotInDb)).andExpect(status().isNotFound());

	}
	
	@Test
	void canRegisterNewUrl() throws JsonProcessingException, Exception {
		
		UrlDto url = new UrlDto("https://stackoverflow.com/questions/16232833/how-to-respond-with-http-400-error-in-a-spring-mvc-responsebody-method-returnin");
		
		// when
        ResultActions resultActions = mockMvc
                .perform(post("/api/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(url)));
        // then
        resultActions.andExpect(status().isCreated());
        
	}
	
	@Test
	void doesNotRegisterNewUrlIfUrlIsInvalid() throws JsonProcessingException, Exception {
		UrlDto url = new UrlDto("1263783489");
		
		// when
        ResultActions resultActions = mockMvc
                .perform(post("/api/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(url)));
        // then
        resultActions.andExpect(status().isBadRequest()).andExpect(content().string("URL invalid"));
        
	}
	
	
}
