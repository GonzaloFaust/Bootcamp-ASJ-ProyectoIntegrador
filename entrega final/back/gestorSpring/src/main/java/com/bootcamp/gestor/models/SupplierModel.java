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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
//    @NotBlank(message="Field id can't be empty")
    @ManyToOne
    @JoinColumn(name = "field_id")
    private FieldModel field;

    
    @NotNull(message="Image can't be null")
    @NotBlank(message="Image can't be empty")
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
//    @NotBlank(message="Address id can't be empty")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addr_id")
    private AddressModel address;

    @NotNull(message="Taxpayer Id Number can't be null")
    @NotBlank(message="Taxpayer Id Number can't be empty")
    @Size(max = 11)
    @Column(name = "sup_cuit", length = 11)
    private String supCuit;


    @NotNull(message="Tax condition id can't be null")
//    @NotBlank(message="Tax condition id can't be empty")
    @ManyToOne
    @JoinColumn(name = "tax_id")
    private TaxConditionModel taxCond;

   
    @NotNull(message="Supplier Contact can't be null")
//    @NotBlank(message="Contact id can't be empty")
    @OneToOne(mappedBy="supplier",cascade=CascadeType.ALL)//fijate de que lado va el mapped by, si el padre o el hijo
    @JoinColumn(name = "sup_contact_id")                   //fijate de que lado va el joincolumn
    private SupplierContactModel supContact;

    @NotNull(message="Activity status can't be null")
//    @NotBlank(message="Deletion can't be empty")
    @Column(name = "sup_isactive")
    private Boolean isActive;
    
    @OneToMany(mappedBy="supplier",cascade= CascadeType.ALL)
    private List<PurchaseOrderModel> supplierOrdersList;
    

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
	
	@OneToMany(mappedBy="supplier", cascade=CascadeType.ALL)
	private List<ProductModel> products;

	public SupplierModel() {
		super();
		
	}

	public SupplierModel(String supCode,
			 String supBussinessName,  FieldModel field, String supImage,
			 String supWebsite,  String supEmail,
			 String supTelephone,  AddressModel address,  String supCuit,
			 TaxConditionModel taxCond, SupplierContactModel supContact
			) {

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

	}
	
	//----------getters y setters

	public String getSupBussinessName() {
		return supBussinessName;
	}

	public void setSupBussinessName(String supBussinessName) {
		this.supBussinessName = supBussinessName;
	}


	public void setField(FieldModel field) {
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

	

	public AddressModel getAddress() {
		return address;
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

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}
	public TaxConditionModel getTaxCond() {
		return taxCond;
	}

	public void setTaxCond(TaxConditionModel taxCond) {
		this.taxCond = taxCond;
	}

	public SupplierContactModel getSupContact() {
		return supContact;
	}

	public void setSupContact(SupplierContactModel supContact) {
		this.supContact = supContact;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public FieldModel getField() {
		return field;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	

}