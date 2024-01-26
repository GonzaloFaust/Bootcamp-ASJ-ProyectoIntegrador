package com.bootcamp.gestor.models;

import java.time.LocalDateTime;

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
@Table(name = "products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Integer prodId;

    @NotNull(message="Product SKU can't be null")
    @NotBlank(message="Product SKU can't be empty")
    @Size(max = 13)
    @Column(name = "prod_sku", nullable = false, length = 13, unique=true)
    private String prodSku;

    @NotNull(message="Supplier id can't be null")
    @NotBlank(message="Supplier id can't be empty")
    @Column(name = "sup_id", nullable = false)
    private Integer supId;

    @NotNull(message="Category id can't be null")
    @NotBlank(message="Category id can't be empty")
    @Column(name = "cat_id", nullable = false)
    private Integer catId;

   
    @NotBlank(message="Image url can't be empty")
    @Column(name = "prod_image")
    private String prodImage;

    @NotNull(message="Product name can't be null")
    @NotBlank(message="Product name can't be empty")
    @Size(max = 30)
    @Column(name = "prod_name", nullable = false, length = 30)
    private String prodName;

    @NotNull(message="Product description can't be null")
    @NotBlank(message="Product description can't be empty")
    @Column(name = "prod_description", nullable = false, columnDefinition = "TEXT")
    private String prodDescription;

    @NotNull(message="Product price can't be null")
    @NotBlank(message="Product price can't be empty")
    @Column(name = "prod_price", nullable = false)
    private Float prodPrice;

    @NotNull(message="Creation date can't be null")
    @NotBlank(message="Creation date can't be empty")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull(message="Updating date can't be null")
    @NotBlank(message="Updating date can't be empty")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull(message="Product availability can't be null")
    @NotBlank(message="Product availability can't be empty")
    @Column(name = "updated_at")
    private Boolean prodAvailable;
    
	public ProductModel() {
		super();
		
	}
	

	public ProductModel(Integer prodId, @NotNull @Size(max = 13) String prodSku, @NotNull Integer supId,
			@NotNull Integer catId, String prodImage, @NotNull @Size(max = 30) String prodName,
			@NotNull String prodDescription, @NotNull Float prodPrice) {
		super();
		this.prodId = prodId;
		this.prodSku = prodSku;
		this.supId = supId;
		this.catId = catId;
		this.prodImage = prodImage;
		this.prodName = prodName;
		this.prodDescription = prodDescription;
		this.prodPrice = prodPrice;
		this.prodAvailable =true;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getProdImage() {
		return prodImage;
	}

	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdDescription() {
		return prodDescription;
	}

	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}

	public Float getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Float prodPrice) {
		this.prodPrice = prodPrice;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt() {
		this.updatedAt = LocalDateTime.now();
	}

	public Integer getProdId() {
		return prodId;
	}

	public String getProdSku() {
		return prodSku;
	}

	public Integer getSupId() {
		return supId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setProdAvailable(Boolean prodAvailable) {
		this.prodAvailable=prodAvailable;
	}
	
	public Boolean getProdAvailable() {
		return this.prodAvailable;
	}

}
