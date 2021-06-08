package com.larissaevaldt.urlshortenerapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.larissaevaldt.urlshortenerapp.model.Url;

@DataJpaTest
public class UrlRepositoryTest {
	
	@Autowired
	private UrlRepository underTest;
	
	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}
	
	@Test
	void itShouldCheckWhenUrlShortUrlExists() {
		// given
		String shortUrl = "12ty678o";
		Url url =  new Url("https://spring.io/guides/gs/testing-web/", shortUrl);
		Url savedUrl = underTest.save(url);
		
		// when
		Optional<Url> optional = underTest.findUrlsByShortUrl(savedUrl.getShort_url());
		
		// then
		assertThat(optional).hasValue(url);
	}
	
	@Test
	void itShouldCheckWhenUrlShortUrlDoesNotExist() {
		// given
		String shortUrl = "12ty678o";
		
		// when
		Optional<Url> optional = underTest.findUrlsByShortUrl(shortUrl);
		
		// then
		assertThat(optional).isEmpty();
	}
	

}
