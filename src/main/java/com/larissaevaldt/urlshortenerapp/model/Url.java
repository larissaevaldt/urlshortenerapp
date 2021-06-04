package com.larissaevaldt.urlshortenerapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * JPA Entity
 * This class represents a table in the database
 * With the annotations @Entity and @Table this class will be automatically mapped to the urls table
 */
@Entity // This tells Hibernate to make a table out of this class
@Table(name="urls")
public class Url {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
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
