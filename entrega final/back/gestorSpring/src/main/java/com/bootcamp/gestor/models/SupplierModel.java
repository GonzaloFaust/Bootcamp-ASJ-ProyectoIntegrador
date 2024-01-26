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
@Table(name = "suppliers")
public class SupplierModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sup_id")
    private Integer supId;

    @NotNull(message="Supplier code can't be null")
    @NotBlank(message="Supplier code can't be empty")
    @Size(max = 5)
    @Column(name = "sup_code", nullable = false, length = 5, unique = true)
    private String supCode;

    @NotNull(message="Bussiness name can't be null")
    @NotBlank(message="Bussiness name can't be empty")
    @Size(max = 30)
    @Column(name = "sup_bussiness_name", nullable = false, length = 30)
    private String supBussinessName;

    @NotNull(message="Field id can't be null")
    @NotBlank(message="Field id can't be empty")
    @Column(name = "field_id", nullable = false)
    private Integer fieldId;

    @Column(name = "sup_image")
    private String supImage;

    @NotNull(message="Website url can't be null")
    @NotBlank(message="Website url can't be empty")
    @Size(max = 40)
    @Column(name = "sup_website", nullable = false, length = 40)
    private String supWebsite;

    @NotNull(message="E-mail address can't be null")
    @NotBlank(message="E-mail address can't be empty")
    @Size(max = 40)
    @Column(name = "sup_email", nullable = false, length = 40)
    private String supEmail;

    @NotNull(message="Telephone number can't be null")
    @NotBlank(message="Telephone number can't be empty")
    @Size(max = 11)
    @Column(name = "sup_telephone", nullable = false, length = 11)
    private String supTelephone;

//    @NotNull
//    @Size(max = 40)
//    @Column(name = "sup_address_name", nullable = false, length = 40)
//    private String supAddressName;
//
//    @NotNull
//    @Column(name = "sup_addresss_num", nullable = false)
//    private Integer supAddresssNum;
    
    
    @NotNull(message="Address id can't be null")
    @NotBlank(message="Address id can't be empty")
    @Column(name = "addr_id", nullable = false)
    private Long addrId;

    @NotNull(message="Taxpayer Id Number can't be null")
    @NotBlank(message="Taxpayer Id Number can't be empty")
    @Size(max = 11)
    @Column(name = "sup_cuit", nullable = false, length = 11)
    private String supCuit;

    @NotNull(message="Tax condition id can't be null")
    @NotBlank(message="Tax condition id can't be empty")
    @Column(name = "tax_id", nullable = false)
    private Integer taxId;

    @NotNull(message="Contact id can't be null")
    @NotBlank(message="Contact id can't be empty")
    @Column(name = "sup_contact_id", nullable = false)
    private Integer supContactId;

    @NotNull(message="Deletion can't be null")
    @NotBlank(message="Deletion can't be empty")
    @Column(name = "sup_isdeleted", nullable = false)
    private Boolean supIsdeleted;

    @NotNull(message="Creation date can't be null")
    @NotBlank(message="Creation date can't be empty")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull(message="Updating date can't be null")
    @NotBlank(message="Updating date can't be empty")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	public SupplierModel() {
		super();
		
	}

	public SupplierModel(Integer supId, @NotNull @Size(max = 5) String supCode,
			@NotNull @Size(max = 30) String supBussinessName, @NotNull Integer fieldId, String supImage,
			@NotNull @Size(max = 40) String supWebsite, @NotNull @Size(max = 40) String supEmail,
			@NotNull @Size(max = 11) String supTelephone,  @NotNull Long addrId, @NotNull @Size(max = 11) String supCuit,
			@NotNull Integer taxId, @NotNull Integer supContactId
			) {
		super();
		this.supId = supId;
		this.supCode = supCode;
		this.supBussinessName = supBussinessName;
		this.fieldId = fieldId;
		this.supImage = supImage;
		this.supWebsite = supWebsite;
		this.supEmail = supEmail;
		this.supTelephone = supTelephone;
		this.addrId = addrId;
		this.supCuit = supCuit;
		this.taxId = taxId;
		this.supContactId = supContactId;
		this.supIsdeleted = false;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	//----------getters y setters

	public String getSupBussinessName() {
		return supBussinessName;
	}

	public void setSupBussinessName(String supBussinessName) {
		this.supBussinessName = supBussinessName;
	}

	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public String getSupImage() {
		return supImage;
	}

	public void setSupImage(String supImage) {
		this.supImage = supImage;
	}

	public String getSupWebsite() {
		return supWebsite;
	}

	public void setSupWebsite(String supWebsite) {
		this.supWebsite = supWebsite;
	}

	public String getSupEmail() {
		return supEmail;
	}

	public void setSupEmail(String supEmail) {
		this.supEmail = supEmail;
	}

	public String getSupTelephone() {
		return supTelephone;
	}

	public void setSupTelephone(String supTelephone) {
		this.supTelephone = supTelephone;
	}

	

	public Long getAddrId() {
		return addrId;
	}


	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public Integer getSupContactId() {
		return supContactId;
	}

	public void setSupContactId(Integer supContactId) {
		this.supContactId = supContactId;
	}

	public Boolean getSupIsdeleted() {
		return supIsdeleted;
	}

	public void setSupIsdeleted(Boolean supIsdeleted) {
		this.supIsdeleted = supIsdeleted;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getSupId() {
		return supId;
	}

	public String getSupCode() {
		return supCode;
	}

	public String getSupCuit() {
		return supCuit;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}