package com.bootcamp.gestor.models;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "purchase_order_product")
public class PurchaseOrderProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pop_id")
    private Long popId;

    @JdbcTypeCode(SqlTypes.JSON)
    @NotNull(message="Order id can't be null")
//    @NotBlank(message="Order id can't be empty")
    @Column(name = "ord_id", nullable = false)
    private PurchaseOrderModel order;

    @JdbcTypeCode(SqlTypes.JSON)
    @NotNull(message="Product id can't be null")
//    @NotBlank(message="Product id can't be empty")
    @Column(name = "prod_id", nullable = false)
    private ProductModel product;

    @NotNull(message="Product quantity can't be null")
//    @NotBlank(message="Product quantity can't be empty")
    @Column(name = "prod_quantity", nullable = false)
    private Integer prodQuantity;

	public PurchaseOrderProductModel() {
		super();
		
	}

	public PurchaseOrderProductModel(Long popId, @NotNull PurchaseOrderModel order, @NotNull ProductModel product,
			@NotNull Integer prodQuantity) {
		super();
		this.popId = popId;
		this.order = order;
		this.product = product;
		this.prodQuantity = prodQuantity;
	}

	public Long getPopId() {
		return popId;
	}

	public PurchaseOrderModel getOrdId() {
		return order;
	}

	public ProductModel getProdId() {
		return product;
	}

	public Integer getProdQuantity() {
		return prodQuantity;
	}

    // Getters and Setters
}
