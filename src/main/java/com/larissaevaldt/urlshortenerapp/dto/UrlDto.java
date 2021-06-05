package com.larissaevaldt.urlshortenerapp.dto;


/**
 * Data Transfer Object for a URL.
 */
public class UrlDto {
	
	private String longUrl;
	
	/**
     * Constructor to fully initialize the UrlDTO
     * @param long URL to be shortened
     */
	public UrlDto(String longUrl) {
		this.longUrl = longUrl;
	}

	public UrlDto() {

	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}	

}
