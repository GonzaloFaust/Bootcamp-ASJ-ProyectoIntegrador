package com.bootcamp.gestor.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "purchase_orders")
public class PurchaseOrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id")
    private Integer ordId;

    @NotNull(message="Order status id can't be null")
    @NotBlank(message="Order status id can't be empty")
    @Column(name = "ord_status_id", nullable = false)
    private Integer ordStatusId;

    @NotNull(message="Order creation date can't be null")
    @NotBlank(message="Order creation date can't be empty")
    @Column(name = "ord_issue_date", nullable = false)
    private LocalDate ordIssueDate;

    @NotNull(message="Expect delivery date can't be null")
    @NotBlank(message="Expect delivery date can't be empty")
    @Column(name = "ord_exp_deliver_date", nullable = false)
    private LocalDate ordExpDeliverDate;

//    @NotNull
//    @Size(max = 40)
//    @Column(name = "ord_address_name", nullable = false, length = 40)
//    private String ordAddressName;
//
//    @NotNull
//    @Column(name = "ord_address_num", nullable = false)
//    private Integer ordAddressNum;

    @NotNull(message="Address id can't be null")
    @NotBlank(message="Address id can't be empty")
    @Column(name = "addr_id", nullable = false)
    private Long addrId;

    @NotNull(message="Supplier id can't be null")
    @NotBlank(message="Supplier id can't be empty")
    @Column(name = "sup_id", nullable = false)
    private Integer supId;

    @NotNull(message="Creation date can't be null")
    @NotBlank(message="Creation date can't be empty")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull(message="Updating date can't be null")
    @NotBlank(message="Updating date can't be empty")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

	public PurchaseOrderModel() {
		super();
		
	}

	public PurchaseOrderModel(Integer ordId, @NotNull Integer ordStatusId, @NotNull LocalDate ordIssueDate,
			@NotNull LocalDate ordExpDeliverDate,  @NotNull Long addrId, @NotNull Integer supId,
			@NotNull LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.ordId = ordId;
		this.ordStatusId = ordStatusId;
		this.ordIssueDate = ordIssueDate;
		this.ordExpDeliverDate = ordExpDeliverDate;
		this.addrId = addrId;
		this.supId = supId;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	//-----getters y settres
	public Integer getOrdStatusId() {
		return ordStatusId;
	}

	public void setOrdStatusId(Integer ordStatusId) {
		this.ordStatusId = ordStatusId;
	}

	public LocalDate getOrdExpDeliverDate() {
		return ordExpDeliverDate;
	}

	public void setOrdExpDeliverDate(LocalDate ordExpDeliverDate) {
		this.ordExpDeliverDate = ordExpDeliverDate;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt() {
		this.updatedAt = LocalDateTime.now();
	}

	public Integer getOrdId() {
		return ordId;
	}

	public LocalDate getOrdIssueDate() {
		return ordIssueDate;
	}


	public Long getAddrId() {
		return addrId;
	}

	public Integer getSupId() {
		return supId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

    
}