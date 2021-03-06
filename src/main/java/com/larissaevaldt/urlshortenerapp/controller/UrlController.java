package com.larissaevaldt.urlshortenerapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

import com.larissaevaldt.urlshortenerapp.dto.UrlDto;
import com.larissaevaldt.urlshortenerapp.model.Url;
import com.larissaevaldt.urlshortenerapp.service.UrlService;

/**
 * URL Controller
 *
 * Created by Larissa Evaldt
 */
@RestController
@RequestMapping(path = "api/v1")
public class UrlController {

	private final UrlService urlService;

	@Autowired // automatically instantiate urlService and inject in the constructor
	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}

	/**
	 * Find the long URL of a given short URL and redirect the user to the web site.
	 *
	 * @param shortUrl A short URL code
	 * @return ResponseEntity with HTTP status code 301 (Moved Permanently) with the
	 *         long URI given by the Location field in the response or a
	 *         ResponseEntity with HTTP status code 400 (Not Found) if user sends a
	 *         shortUrl that is not in the database
	 */
	@GetMapping("/{shortUrl}")
	public ResponseEntity<?> getLongUrl(@PathVariable String shortUrl) throws URISyntaxException {

		// look for the Url object in the database
		Optional<Url> optionalUrl = urlService.findByShortUrl(shortUrl);

		if (optionalUrl.isPresent()) {

			// get the object
			Url url = optionalUrl.get();

			// create a URI object using the long URL
			URI uri = new URI(url.getOriginal_url());

			// pass it to the Location field of the response
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uri);

			// return response entity with the location in the headers and moved permanently
			// code to automatically redirect the user
			return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Receives a long URL to shorten, validates if URL is valid, generate short
	 * URL, save in the database
	 * 
	 * @param urlDto A long URL to shorten
	 * @return ResponseEntity with status 201 created and the short URL or 400 with
	 *         a URL invalid message
	 */
	@PostMapping("/")
	public ResponseEntity<?> shortenUrl(@RequestBody UrlDto urlDto) {
		// get URL from the body of the request
		String originalUrl = urlDto.getLongUrl();

		// make sure URL is valid
		if (urlService.validateUrl(originalUrl)) {

			// generate the short code
			String shortUrl = urlService.generateShortUrl(originalUrl);

			// create an object and save in the database
			Url url = new Url(originalUrl, shortUrl);
			Url savedUrl = urlService.save(url);
			
			// return response entity
			return ResponseEntity.created(URI.create(savedUrl.getShort_url()))
					.body("http://localhost:8080/api/v1/" + savedUrl.getShort_url());
		
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL invalid");
		}

	}

}
