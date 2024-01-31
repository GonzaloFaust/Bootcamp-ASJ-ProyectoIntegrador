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

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ord_id")
	private Integer ordId;

	
	@NotNull(message = "Order status id can't be null")
//    @NotBlank(message="Order status id can't be empty")
	@ManyToOne
	@JoinColumn(name = "ord_status_id")
	private OrderStatusModel ordStatus;

	@NotNull(message = "Order creation date can't be null")
    @NotBlank(message="Order creation date can't be empty")
	@Column(name = "ord_issue_date", nullable = false)
	private Date ordIssueDate;

	@NotNull(message = "Expect delivery date can't be null")
    @NotBlank(message="Expect delivery date can't be empty")
	@Column(name = "ord_exp_deliver_date", nullable = false)
	private Date ordExpDeliverDate;

	@OneToMany(mappedBy = "purchase_order_detail_list", cascade = CascadeType.ALL)
	private List<PurchaseOrderProductModel> orderDetail;
	
	@NotNull(message = "Address id can't be null")
//    @NotBlank(message="Address id can't be empty")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addr_id")
	private AddressModel address;

	
	@NotNull(message = "Supplier id can't be null")
//    @NotBlank(message="Supplier id can't be empty")
	@ManyToOne
	@JoinColumn(name = "sup_id")
	private SupplierModel supplier;

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

	public PurchaseOrderModel() {
		super();

	}

	public PurchaseOrderModel(OrderStatusModel ordStatus, Date ordIssueDate, Date ordExpDeliverDate,
			AddressModel address, SupplierModel supplier) {

		this.ordStatus = ordStatus;
		this.ordIssueDate = ordIssueDate;
		this.ordExpDeliverDate = ordExpDeliverDate;
		this.address = address;
		this.supplier = supplier;
	}
	// -----getters y settres

	public OrderStatusModel getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(OrderStatusModel ordStatus) {
		this.ordStatus = ordStatus;
	}

	public Date getOrdExpDeliverDate() {
		return ordExpDeliverDate;
	}

	public void setOrdExpDeliverDate(Date ordExpDeliverDate) {
		this.ordExpDeliverDate = ordExpDeliverDate;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public Date getOrdIssueDate() {
		return ordIssueDate;
	}

	public AddressModel getAddress() {
		return address;
	}

	public SupplierModel getSupplier() {
		return supplier;
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