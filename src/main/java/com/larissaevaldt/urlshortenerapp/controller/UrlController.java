package com.larissaevaldt.urlshortenerapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
	
	@GetMapping("/{shortUrl}")
	public ResponseEntity<?> getLongUrl(@PathVariable String shortUrl) throws URISyntaxException {

        Optional<Url> optionalUrl = urlService.findByShortUrl(shortUrl);
//        return ResponseEntity.of(optionalUrl);
        
        if (optionalUrl.isPresent()) {
        	System.out.println("cheguei");
        	Url url = optionalUrl.get();
			
        	URI uri = new URI(url.getOriginal_url());
			
        	HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            
            return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
	}
	@PostMapping()
	public void registerNewUrl(@RequestBody Url url) {
		urlService.save(url);
	}
	
	@PostMapping("/short")
	public ResponseEntity<?> shortenUrl(@RequestBody String originalUrl) {
		
	 if(urlService.validateUrl(originalUrl)) {
		 
		 String shortUrl = urlService.generateShortUrl(originalUrl);
		 Url url = new Url(originalUrl, shortUrl);
		 Url savedUrl = urlService.save(url);
		 
		 return ResponseEntity.ok(savedUrl);
		 
	 } else {
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL invalid");
	 }
		
	}
	
	
}
