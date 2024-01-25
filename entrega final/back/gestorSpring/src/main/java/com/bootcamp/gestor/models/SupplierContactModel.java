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
@Table(name = "suppliers_contacts")
public class SupplierContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sup_contact_id")
    private Integer supContactId;

    @NotNull(message="Contact name can't be null")
    @NotBlank(message="Contact name can't be empty")
    @Size(max = 20)
    @Column(name = "sup_contact_name", nullable = false, length = 20)
    private String supContactName;

    @NotNull(message="Contact last name can't be null")
    @NotBlank(message="Contact last name can't be empty")
    @Size(max = 20)
    @Column(name = "sup_contact_lastname", nullable = false, length = 20)
    private String supContactLastname;

    @NotNull(message="Contact phone number can't be null")
    @NotBlank(message="Contact phone number can't be empty")
    @Size(max = 11)
    @Column(name = "sup_contact_telephone", nullable = false, length = 11)
    private String supContactTelephone;

    @NotNull(message="Contact e-mail can't be null")
    @NotBlank(message="Contact e-mail can't be empty")
    @Size(max = 40)
    @Column(name = "sup_contact_email", nullable = false, length = 40)
    private String supContactEmail;

    @NotNull(message="Contact role can't be null")
    @Size(max = 30)
    @Column(name = "sup_contact_role", nullable = false, length = 30)
    private String supContactRole;

    @NotNull(message="Creation date can't be null")
    @NotBlank(message="Creation date can't be empty")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull(message="Updating date can't be null")
    @NotBlank(message="Updating date can't be empty")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    

	public SupplierContactModel() {
		super();
		
	}

	public SupplierContactModel(Integer supContactId, @NotNull @Size(max = 20) String supContactName,
			@NotNull @Size(max = 20) String supContactLastname, @NotNull @Size(max = 11) String supContactTelephone,
			@NotNull @Size(max = 40) String supContactEmail, @NotNull @Size(max = 30) String supContactRole) {
		
		this.supContactId = supContactId;
		this.supContactName = supContactName;
		this.supContactLastname = supContactLastname;
		this.supContactTelephone = supContactTelephone;
		this.supContactEmail = supContactEmail;
		this.supContactRole = supContactRole;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	
	//---------getters y setters
	
	
	public String getSupContactName() {
		return supContactName;
	}

	public void setSupContactName(String supContactName) {
		this.supContactName = supContactName;
	}

	public String getSupContactLastname() {
		return supContactLastname;
	}

	public void setSupContactLastname(String supContactLastname) {
		this.supContactLastname = supContactLastname;
	}

	public String getSupContactTelephone() {
		return supContactTelephone;
	}

	public void setSupContactTelephone(String supContactTelephone) {
		this.supContactTelephone = supContactTelephone;
	}

	public String getSupContactEmail() {
		return supContactEmail;
	}

	public void setSupContactEmail(String supContactEmail) {
		this.supContactEmail = supContactEmail;
	}

	public String getSupContactRole() {
		return supContactRole;
	}

	public void setSupContactRole(String supContactRole) {
		this.supContactRole = supContactRole;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt() {
		this.updatedAt = LocalDateTime.now();
	}

	public Integer getSupContactId() {
		return supContactId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}