package com.larissaevaldt.urlshortenerapp.model;

public class Url {
	
	private Long id;
	private String original_url;
	private String short_url;
	
	public Url(Long id, String original_url, String short_url) {
		this.id = id;
		this.original_url = original_url;
		this.short_url = short_url;
	}

	public Url(String original_url, String short_url) {
		this.original_url = original_url;
		this.short_url = short_url;
	}

	public Url() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginal_url() {
		return original_url;
	}

	public void setOriginal_url(String original_url) {
		this.original_url = original_url;
	}

	public String getShort_url() {
		return short_url;
	}

	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", original_url=" + original_url + ", short_url=" + short_url + "]";
	}
	
	
	
	
	
}
