package com.bootcamp.gestor.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

	@NotNull(message = "State id can't be null")
//	@NotBlank(message = "State id can't be empty")
	@ManyToOne
	@JoinColumn(name = "address_state")
	private StateModel state;

	@NotNull(message = "City name can't be null")
   @NotBlank(message="City name can't be empty")
	@Size(max = 50)
	@Column(name = "city_name", nullable = false, length = 50)
	private String cityName;

	@NotNull(message = "Address post code can't be null")
	@NotBlank(message = "Address post code can't be empty")
	@Size(max = 10)
	@Column(name = "addr_postcode", nullable = false, length = 10)
	private String addrPostcode;

	@NotNull(message = "Address street name can't be null")
	@NotBlank(message = "Address street name can't be empty")
	@Column(name = "addr_street", nullable = false)
	private String addrStreet;

	@NotNull(message = "Address street number can't be null")

	@Column(name = "addr_number", nullable = false)
	private int addrNumber;

	@Column(name = "addr_floor")
	private int addrFloor;

	@Size(max = 2)
	@Column(name = "addr_apart", length = 2)
	private String addrApartment;
	
	 //   @NotNull(message="Creation date can't be null")
//  @NotBlank(message="Creation date can't be empty")
  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

//  @NotNull(message="Updating date can't be null")
//  @NotBlank(message="Updating date can't be empty")
  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

	public AddressModel(StateModel state, String cityName, String addrPostcode, String addrStreet, int addrNumber,
			int addrFloor, String addrApartment) {
		super();
		this.state = state;
		this.cityName = cityName;
		this.addrPostcode = addrPostcode;
		this.addrStreet = addrStreet;
		this.addrNumber = addrNumber;
		this.addrFloor = addrFloor;
		this.addrApartment = addrApartment;
	}

	public StateModel getState() {
		return state;
	}

	public void setState(StateModel state) {
		this.state = state;
	}

	public String getAddrStreet() {
		return addrStreet;
	}

	public void setAddrStreet(String addrStreet) {
		this.addrStreet = addrStreet;
	}

	public int getAddrNumber() {
		return addrNumber;
	}

	public void setAddrNumber(int addrNumber) {
		this.addrNumber = addrNumber;
	}

	public int getAddrFloor() {
		return addrFloor;
	}

	public void setAddrFloor(int addrFloor) {
		this.addrFloor = addrFloor;
	}

	public String getAddrApartment() {
		return addrApartment;
	}

	public void setAddrApartment(String addrApartment) {
		this.addrApartment = addrApartment;
	}

	public AddressModel() {
		super();

	}

	// getters y settrs
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
	
	@PrePersist
	protected void onCreate() {
	  createdAt = new Date();
	  updatedAt= new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
	  updatedAt = new Date();
	}


}