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
@Table(name = "address")
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addr_id")
    private Long addrId;

    @NotNull(message="State id can't be null")
    @NotBlank(message="State id can't be empty")
    @Column(name = "state_id", nullable = false)
    private Integer stateId;

    @NotNull(message="City name can't be null")
    @NotBlank(message="City name can't be empty")
    @Size(max = 50)
    @Column(name = "city_name", nullable = false, length = 50)
    private String cityName;

    @NotNull(message="Address post code can't be null")
    @NotBlank(message="Address post code can't be empty")
    @Size(max = 10)
    @Column(name = "addr_postcode", nullable = false, length = 10)
    private String addrPostcode;

	public AddressModel() {
		super();
	
	}

	public AddressModel(Long addrId, @NotNull Integer stateId, @NotNull @Size(max = 50) String cityName,
			@NotNull @Size(max = 10) String addrPostcode) {
		super();
		this.addrId = addrId;
		this.stateId = stateId;
		this.cityName = cityName;
		this.addrPostcode = addrPostcode;
	}

	//getters y settrs
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAddrPostcode() {
		return addrPostcode;
	}

	public void setAddrPostcode(String cityPostcode) {
		this.addrPostcode = cityPostcode;
	}

	public Long getAddrId() {
		return addrId;
	}

	public Integer getStateId() {
		return stateId;
	}

    
}