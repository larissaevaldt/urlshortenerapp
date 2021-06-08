package com.larissaevaldt.urlshortenerapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.larissaevaldt.urlshortenerapp.model.Url;
import com.larissaevaldt.urlshortenerapp.repository.UrlRepository;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
	
	@Mock
	private UrlRepository urlRepository;
	private UrlService underTest;
	
	@BeforeEach
	void setUp() {
			underTest = new UrlService(urlRepository);
	}
	
	@Test
	void canSaveUrl() {
		// given
		Url url =  new Url("12ty678o", "https://spring.io/guides/gs/testing-web/");
		
		//when
		underTest.save(url);
		
		//then
		ArgumentCaptor<Url> urlArgumentCaptor = ArgumentCaptor.forClass(Url.class);
		
		verify(urlRepository).save(urlArgumentCaptor.capture());
		Url capturedUrl = urlArgumentCaptor.getValue();
		
		assertThat(capturedUrl).isEqualTo(url);
	}
	
	@Test
	void whenShortUrlIsTaken() {
		// given
		Url url =  new Url("12ty678o", "https://spring.io/guides/gs/testing-web/");
		
		//when
		Url savedUrl = underTest.save(url);
		
		//then
		assertThat(savedUrl).isNull();
		
	}
	
	@Test
	void canGenerateShortUrl() {
		// given
		String longUrl = "https://spring.io/guides/gs/testing-web/";
		
		// when
		String shortUrl = underTest.generateShortUrl(longUrl);
		
		//then
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
				
		verify(urlRepository).findUrlsByShortUrl(argumentCaptor.capture());
		String capturedUrl = argumentCaptor.getValue();
				
		assertThat(capturedUrl).isEqualTo(shortUrl);
	}
	
	@Test
    void itShouldCheckWhenUrlIsValid() {
        // given
        String url = "https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/routines/UrlValidator.html";

        // when
        boolean expected = underTest.validateUrl(url);

        // then
        assertThat(expected).isTrue();
    }
	
	@Test
    void itShouldCheckWhenUrlIsNotValid() {
        // given
        String url = "jsndsigdu62791839";

        // when
        boolean expected = underTest.validateUrl(url);

        // then
        assertThat(expected).isFalse();
    }
	


}
