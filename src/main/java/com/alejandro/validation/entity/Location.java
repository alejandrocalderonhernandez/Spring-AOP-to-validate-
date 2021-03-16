package com.alejandro.validation.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Location implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLocation;
	private BigDecimal lat;
	private BigDecimal lon;
	@JsonIgnore
	private Transaction transaction;
	
	public Location() {}

	public Long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	private static final long serialVersionUID = 8934446774559943035L;

}
