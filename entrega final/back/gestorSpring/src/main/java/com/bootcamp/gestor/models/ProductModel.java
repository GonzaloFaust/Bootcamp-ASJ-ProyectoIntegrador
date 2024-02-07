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
//    @NotBlank(message="Product SKU can't be empty")
    @Size(max = 13)
    @Column(name = "prod_sku", length = 13)
    private String prodSku;

    @NotNull(message="Supplier id can't be null")
//    @NotBlank(message="Supplier id can't be empty")
    @ManyToOne
    @JoinColumn(name = "sup_id")
    private SupplierModel supplier;


    @NotNull(message="Category id can't be null")
//    @NotBlank(message="Category id can't be empty")
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private CategoryModel category;

   
//    @NotBlank(message="Image url can't be empty")
    @Column(name = "prod_image")
    private String prodImage;

    @NotNull(message="Product name can't be null")
//    @NotBlank(message="Product name can't be empty")
    @Size(max = 30)
    @Column(name = "prod_name", nullable = false, length = 30)
    private String prodName;

    @NotNull(message="Product description can't be null")
//    @NotBlank(message="Product description can't be empty")
    @Column(name = "prod_description", nullable = false, columnDefinition = "TEXT")
    private String prodDescription;

    @NotNull(message="Product price can't be null")
//    @NotBlank(message="Product price can't be empty")
    @Column(name = "prod_price", nullable = false)
    private Float prodPrice;

 // @NotNull(message="Creation date can't be null")
//  @NotBlank(message="Creation date can't be empty")
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

//  @NotNull(message="Updating date can't be null")
//  @NotBlank(message="Updating date can't be empty")
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

    @NotNull(message="Product availability can't be null")
//    @NotBlank(message="Product availability can't be empty")
    @Column(name = "prod_available")
    private Boolean prodAvailable;
    
	public ProductModel() {
		super();
		
	}
	

	public ProductModel( String prodSku,  SupplierModel supplier,
			 CategoryModel category, String prodImage,  String prodName,
			 String prodDescription, Float prodPrice) {
		
		this.prodSku = prodSku;
		this.supplier = supplier;
		this.category = category;
		this.prodImage = prodImage;
		this.prodName = prodName;
		this.prodDescription = prodDescription;
		this.prodPrice = prodPrice;
		this.prodAvailable =true;
		
	}
	public CategoryModel getCategory() {
		return this.category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
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

	


	public Integer getProdId() {
		return prodId;
	}

	public String getProdSku() {
		return prodSku;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier= supplier;
	}

	
	public void setProdAvailable(Boolean prodAvailable) {
		this.prodAvailable=prodAvailable;
	}
	
	public Boolean getProdAvailable() {
		return this.prodAvailable;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

}
