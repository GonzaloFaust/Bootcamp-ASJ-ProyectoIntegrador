package com.bootcamp.gestor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "states")
public class StateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Integer stateId;


    @NotNull(message="Country id can't be null")
 // @NotBlank(message="Country id can't be empty") //da problemas al crear un nuevo state
    @ManyToOne
	@JoinColumn(name="country_id")
    private CountryModel country;


    @NotNull(message="State/Province name can't be null")
    @NotBlank(message="State/Province name can't be empty")
    @Size(max = 30)
    @Column(name = "state_name", nullable = false, length = 30)
    private String stateName;
	
	public StateModel() {
		super();
	}

	public StateModel( @NotNull CountryModel country, @NotNull @Size(max = 30) String stateName) {
		this.country = country;
		this.stateName = stateName;
	}
	
	//getters y seters

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public CountryModel getCountry() {
		return country;
	}

	public void setCountry(CountryModel country) {
		this.country = country;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getStateId() {
		return stateId;
	}

    
	
}	
