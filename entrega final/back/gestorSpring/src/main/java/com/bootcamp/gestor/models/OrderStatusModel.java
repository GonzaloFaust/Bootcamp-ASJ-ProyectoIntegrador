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
@Table(name = "order_status")
public class OrderStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordst_id")
    private Integer ordstId;

    @NotNull(message="Order status name can't be null")
    @NotBlank(message="Order status name can't be empty")
    @Size(max = 30)
    @Column(name = "ordst_name", nullable = false, length = 30)
    private String ordstName;

	public OrderStatusModel() {
		super();
		
	}

	public OrderStatusModel(Integer ordstId, @NotNull @Size(max = 30) String ordstName) {
		
		this.ordstId = ordstId;
		this.ordstName = ordstName;
	}

	public String getOrdstName() {
		return ordstName;
	}

	public void setOrdstName(String ordstName) {
		this.ordstName = ordstName;
	}

	public Integer getOrdstId() {
		return ordstId;
	}

    // Getters and Setters
}
