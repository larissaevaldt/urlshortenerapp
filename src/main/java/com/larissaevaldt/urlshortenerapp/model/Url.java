package com.larissaevaldt.urlshortenerapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * JPA Entity This class represents a table in the database With the
 * annotations @Entity and @Table this class will be automatically mapped to the
 * urls table
 * 
 * Created by Larissa Evaldt
 */
@Entity // Tells Hibernate to make a table out of this class
@Table(name = "urls")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((original_url == null) ? 0 : original_url.hashCode());
		result = prime * result + ((short_url == null) ? 0 : short_url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Url other = (Url) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (original_url == null) {
			if (other.original_url != null)
				return false;
		} else if (!original_url.equals(other.original_url))
			return false;
		if (short_url == null) {
			if (other.short_url != null)
				return false;
		} else if (!short_url.equals(other.short_url))
			return false;
		return true;
	}

}
