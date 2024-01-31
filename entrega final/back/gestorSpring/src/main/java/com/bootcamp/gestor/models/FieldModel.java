package com.bootcamp.gestor.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "fields")
public class FieldModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Integer fieldId;

    @NotNull(message="Field name can't be null")
//    @NotBlank(message="Field name can't be empty")
    @Size(max = 20)
    @Column(name = "field_name", nullable = false, length = 20)
    private String fieldName;

    @NotNull(message="Field description can't be null")
    @Size(max = 200)
    @Column(name = "field_detail", nullable = false, length = 200)
    private String fieldDetail;

    @NotNull(message="Creation date can't be null")
//    @NotBlank(message="Creation date can't be empty")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull(message="Updating date can't be null")
//    @NotBlank(message="Updating date can't be empty")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	public FieldModel() {
		super();
		
	}

	public FieldModel(Integer fieldId, @NotNull @Size(max = 20) String fieldName,
			@NotNull @Size(max = 200) String fieldDetail) {;
		this.fieldId = fieldId;
		this.fieldName = fieldName;
		this.fieldDetail = fieldDetail;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	//--------------getters y setters
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDetail() {
		return fieldDetail;
	}

	public void setFieldDetail(String fieldDetail) {
		this.fieldDetail = fieldDetail;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt() {
		this.updatedAt = LocalDateTime.now();
	}

	public Integer getFieldId() {
		return fieldId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

    
}