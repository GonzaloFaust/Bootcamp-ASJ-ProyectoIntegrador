package com.bootcamp.gestor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "purchase_order_product")
public class PurchaseOrderProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pop_id")
    private Long popId;

    @NotNull(message="Order id can't be null")
    @NotBlank(message="Order id can't be empty")
    @Column(name = "ord_id", nullable = false)
    private Integer ordId;

    @NotNull(message="Product id can't be null")
    @NotBlank(message="Product id can't be empty")
    @Column(name = "prod_id", nullable = false)
    private Integer prodId;

    @NotNull(message="Product quantity can't be null")
    @NotBlank(message="Product quantity can't be empty")
    @Column(name = "prod_quantity", nullable = false)
    private Integer prodQuantity;

	public PurchaseOrderProductModel() {
		super();
		
	}

	public PurchaseOrderProductModel(Long popId, @NotNull Integer ordId, @NotNull Integer prodId,
			@NotNull Integer prodQuantity) {
		super();
		this.popId = popId;
		this.ordId = ordId;
		this.prodId = prodId;
		this.prodQuantity = prodQuantity;
	}

	public Long getPopId() {
		return popId;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public Integer getProdQuantity() {
		return prodQuantity;
	}

    // Getters and Setters
}
