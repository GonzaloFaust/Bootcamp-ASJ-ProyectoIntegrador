package com.bootcamp.gestor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "countries")
public class CountryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coun_id")
	private Integer counId;

	@NotNull(message = "Country name can't be null")
    @NotBlank(message="Country name can't be empty")
	@Size(min = 4, max = 40)
	@Column(name = "coun_name", nullable = false, length = 40)
	private String counName;

	public CountryModel() {
		super();
	}

	public CountryModel( @NotNull @Size(min = 4, max = 40) String counName) {
		this.counName = counName;
	}

	// -------getters y setters

	public String getCounName() {
		return counName;
	}

	public void setCounName(String counName) {
		this.counName = counName;
	}

	public Integer getCounId() {
		return counId;
	}

}