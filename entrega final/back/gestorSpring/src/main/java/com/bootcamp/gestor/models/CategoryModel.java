package com.bootcamp.gestor.models;


import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;

    @NotNull(message="Category name can't be null")
//    @NotBlank(message="Category name can't be empty")
    @Size(max = 40)
    @Column(name = "cat_name", nullable = false, length = 40)
    private String catName;

    @NotNull(message="Category description can't be null")
    @Size(max = 200)
    @Column(name = "cat_detail", nullable = false, length = 200)
    private String catDetail;

//  @NotNull(message="Creation date can't be null")
//  @NotBlank(message="Creation date can't be empty")
  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

//  @NotNull(message="Updating date can't be null")
//  @NotBlank(message="Updating date can't be empty")
  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;
    
    
    @NotNull(message="Field id can't be null")
//    @NotBlank(message="Field id can't be empty")
    @ManyToOne()
    @JoinColumn(name = "field_id")
    private FieldModel field;
    
    @OneToMany(mappedBy="category", cascade=CascadeType.ALL)
    private List<ProductModel> products; 

	public CategoryModel() {
		super();
		
	}

	public CategoryModel( String catName, String catDetail, FieldModel field) {

		this.catName = catName;
		this.catDetail = catDetail;
		this.field= field;
	}
	
	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatDetail() {
		return catDetail;
	}

	public void setCatDetail(String catDetail) {
		this.catDetail = catDetail;
	}





	public Integer getCatId() {
		return catId;
	}

	

	public FieldModel getField() {
		return this.field;
	}
	
	public void setField(FieldModel field) {
		this.field = field;
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
