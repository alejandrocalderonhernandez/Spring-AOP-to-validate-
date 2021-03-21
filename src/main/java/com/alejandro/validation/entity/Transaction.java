package com.alejandro.validation.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.alejandro.validation.annotation.Iva;
import com.google.gson.Gson;

@Entity
public class Transaction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Iva(value = 0.15)
	private Double ammount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "idLocation")
	private Location location;
	public Transaction() {}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this).toString();
	}
	
	private static final long serialVersionUID = -1526128332791201248L;
	
}
