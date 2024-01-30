package com.bootcamp.gestor.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @NotNull(message="Creation date can't be null")
//    @NotBlank(message="Creation date can't be empty")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull(message="Updating date can't be null")
//    @NotBlank(message="Updating date can't be empty")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @NotNull(message="Field id can't be null")
//    @NotBlank(message="Field id can't be empty")
    @Column(name = "field_id")
    private FieldModel field;
    
    

	public CategoryModel() {
		super();
		
	}

	public CategoryModel(Integer catId, String catName, String catDetail, FieldModel field) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.catDetail = catDetail;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt() {
		this.updatedAt = LocalDateTime.now();
	}

	public Integer getCatId() {
		return catId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public FieldModel getFieldId() {
		return this.field;
	}
	
	public void setFieldId(FieldModel field) {
		this.field = field;
	}
	

    // Getters and Setters
}
