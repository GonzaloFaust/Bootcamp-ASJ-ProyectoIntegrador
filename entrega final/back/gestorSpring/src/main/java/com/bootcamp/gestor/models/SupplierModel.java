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
@Table(name = "suppliers")
public class SupplierModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sup_id")
    private Integer supId;

    @NotNull(message="Supplier code can't be null")
//    @NotBlank(message="Supplier code can't be empty")
    @Size(max = 5)
    @Column(name = "sup_code", nullable = false, length = 5, unique = true)
    private String supCode;

    @NotNull(message="Bussiness name can't be null")
//    @NotBlank(message="Bussiness name can't be empty")
    @Size(max = 30)
    @Column(name = "sup_bussiness_name", nullable = false, length = 30)
    private String supBussinessName;

    @JdbcTypeCode(SqlTypes.JSON)
    @NotNull(message="Field id can't be null")
//    @NotBlank(message="Field id can't be empty")
    @Column(name = "field_id", nullable = false)
    private FieldModel field;

    @Column(name = "sup_image")
    private String supImage;

    @NotNull(message="Website url can't be null")
//    @NotBlank(message="Website url can't be empty")
    @Size(max = 40)
    @Column(name = "sup_website", nullable = false, length = 40)
    private String supWebsite;

    @NotNull(message="E-mail address can't be null")
//    @NotBlank(message="E-mail address can't be empty")
    @Size(max = 40)
    @Column(name = "sup_email", nullable = false, length = 40)
    private String supEmail;

    @NotNull(message="Telephone number can't be null")
//    @NotBlank(message="Telephone number can't be empty")
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
    
    @JdbcTypeCode(SqlTypes.JSON)
    @NotNull(message="Address id can't be null")
//    @NotBlank(message="Address id can't be empty")
    @Column(name = "addr_id", nullable = false)
    private AddressModel address;

    @NotNull(message="Taxpayer Id Number can't be null")
//    @NotBlank(message="Taxpayer Id Number can't be empty")
    @Size(max = 11)
    @Column(name = "sup_cuit", nullable = false, length = 11)
    private String supCuit;

    @JdbcTypeCode(SqlTypes.JSON)
    @NotNull(message="Tax condition id can't be null")
//    @NotBlank(message="Tax condition id can't be empty")
    @Column(name = "tax_id", nullable = false)
    private TaxConditionModel taxCond;

    @JdbcTypeCode(SqlTypes.JSON)
    @NotNull(message="Contact id can't be null")
//    @NotBlank(message="Contact id can't be empty")
    @Column(name = "sup_contact_id", nullable = false)
    private SupplierContactModel supContact;

    @NotNull(message="Deletion can't be null")
//    @NotBlank(message="Deletion can't be empty")
    @Column(name = "sup_isdeleted", nullable = false)
    private Boolean isActive;

    @NotNull(message="Creation date can't be null")
//    @NotBlank(message="Creation date can't be empty")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull(message="Updating date can't be null")
//    @NotBlank(message="Updating date can't be empty")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	public SupplierModel() {
		super();
		
	}

	public SupplierModel(Integer supId, @NotNull @Size(max = 5) String supCode,
			@NotNull @Size(max = 30) String supBussinessName, @NotNull FieldModel field, String supImage,
			@NotNull @Size(max = 40) String supWebsite, @NotNull @Size(max = 40) String supEmail,
			@NotNull @Size(max = 11) String supTelephone,  @NotNull AddressModel address, @NotNull @Size(max = 11) String supCuit,
			@NotNull TaxConditionModel taxCond, @NotNull SupplierContactModel supContact
			) {
		super();
		this.supId = supId;
		this.supCode = supCode;
		this.supBussinessName = supBussinessName;
		this.field = field;
		this.supImage = supImage;
		this.supWebsite = supWebsite;
		this.supEmail = supEmail;
		this.supTelephone = supTelephone;
		this.address = address;
		this.supCuit = supCuit;
		this.taxCond = taxCond;
		this.supContact = supContact;
		this.isActive = true;
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

	public FieldModel getFieldId() {
		return field;
	}

	public void setFieldId(FieldModel field) {
		this.field = field;
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

	

	public AddressModel getAddrId() {
		return address;
	}


	public TaxConditionModel getTaxId() {
		return taxCond;
	}

	public void setTaxId(TaxConditionModel taxCond) {
		this.taxCond = taxCond;
	}

	public SupplierContactModel getSupContactId() {
		return supContact;
	}

	public void setSupContactId(SupplierContactModel supContact) {
		this.supContact = supContact;
	}

	public Boolean getSupIsdeleted() {
		return isActive;
	}

	public void setSupIsActive(Boolean supIsActive) {
		this.isActive = supIsActive;
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