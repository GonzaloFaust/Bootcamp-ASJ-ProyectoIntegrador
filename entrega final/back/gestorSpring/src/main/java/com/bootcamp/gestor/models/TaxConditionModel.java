package com.bootcamp.gestor.models;

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
@Table(name = "tax_conditions")
public class TaxConditionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_id")
    private Integer taxId;

    @NotNull(message="Tax condition title can't be null")
//    @NotBlank(message="Tax condition title can't be empty")
    @Size(max = 50)
    @Column(name = "tax_cond_title", nullable = false, length = 50)
    private String taxCondTitle;

	public TaxConditionModel() {
		super();
		
	}

	public TaxConditionModel(Integer taxId, @NotNull @Size(max = 50) String taxCondTitle) {
		
		this.taxId = taxId;
		this.taxCondTitle = taxCondTitle;
	}

	//---------getters y setters
	public String getTaxCondTitle() {
		return taxCondTitle;
	}

	public void setTaxCondTitle(String taxCondTitle) {
		this.taxCondTitle = taxCondTitle;
	}

	public Integer getTaxId() {
		return taxId;
	}
	

}
