package com.bootcamp.gestor.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "purchase_order_product")
public class PurchaseOrderProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pop_id")
    private Long popId;

 
    @NotNull(message="Order id can't be null")
//    @NotBlank(message="Order id can't be empty")
    @ManyToOne
    @JoinColumn(name = "ord_id")
    private PurchaseOrderModel order;

    
    @NotNull(message="Product id can't be null")
//    @NotBlank(message="Product id can't be empty")
    @ManyToOne
    @JoinColumn(name = "prod_id")
    private ProductModel product;

    @NotNull(message="Product quantity can't be null")
//    @NotBlank(message="Product quantity can't be empty")
    @Column(name = "prod_quantity", nullable = false)
    private Integer prodQuantity;

	public PurchaseOrderProductModel() {
		super();
		
	}

	public PurchaseOrderProductModel(PurchaseOrderModel order, ProductModel product,
		 Integer prodQuantity) {
		
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
