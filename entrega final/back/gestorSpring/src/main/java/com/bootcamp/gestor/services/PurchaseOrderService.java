package com.bootcamp.gestor.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.AddressModel;
import com.bootcamp.gestor.models.OrderStatusModel;
import com.bootcamp.gestor.models.PurchaseOrderModel;
import com.bootcamp.gestor.models.SupplierModel;
import com.bootcamp.gestor.repositories.AddressRepository;
import com.bootcamp.gestor.repositories.OrderStatusRepository;
import com.bootcamp.gestor.repositories.PurchaseOrderRepository;
import com.bootcamp.gestor.repositories.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepo;

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	SupplierRepository supplierRepo;

	@Autowired
	OrderStatusRepository orderStatusRepo;

	public List<PurchaseOrderModel> getAllPurchaseOrders() {
		return purchaseOrderRepo.findAll();
	}

	public PurchaseOrderModel getPurchaseOrderById(int id) {

		return purchaseOrderRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Couldn´t find a Purchase Order with the id " + id));
	}

	public List<PurchaseOrderModel> createPurchaseOrder(PurchaseOrderModel purchOrder) {
		Optional<AddressModel> a = addressRepo.findById(purchOrder.getAddress().getAddrId());
		Optional<SupplierModel> s = supplierRepo.findById(purchOrder.getSupplier().getSupId());
		Optional<OrderStatusModel> os = orderStatusRepo.findById(purchOrder.getOrdStatus().getOrdstId());

		
		//cotrolar fechas
		if (!a.isPresent())
			throw new EntityNotFoundException("Couldn't find the address for this order");
		if (!s.isPresent())
			throw new EntityNotFoundException("Couldn't find the supplier for this order");
		if (!os.isPresent())
			throw new EntityNotFoundException("Couldn't find the status of this order");
		AddressModel address = a.get();
		SupplierModel supplier = s.get();
		OrderStatusModel status = os.get();
		purchOrder.setAddress(address);
		purchOrder.setSupplier(supplier);
		purchOrder.setOrdStatus(status);
		purchaseOrderRepo.save(purchOrder);
		return purchaseOrderRepo.findAll();
	}

	public String updatePurchaseOrder(int id, PurchaseOrderModel purchOrder) {
		Optional<PurchaseOrderModel> o = purchaseOrderRepo.findById(id);
		if (o.isPresent()) {

			Optional<AddressModel> a = addressRepo.findById(purchOrder.getAddress().getAddrId());
			Optional<OrderStatusModel> os = orderStatusRepo.findById(purchOrder.getOrdStatus().getOrdstId());
//		if(Assert.isNull(purchOrder.getOrdIssueDate(), "")) {
//			validar fechas de inicio y fecha de entrega
//		}
			if (!a.isPresent())
				throw new EntityNotFoundException("Couldn't find the address for this order");

			if (!os.isPresent())
				throw new EntityNotFoundException("Couldn't find the status of this order");
			AddressModel address = a.get();

			OrderStatusModel status = os.get();
			purchOrder.setAddress(address);

			purchOrder.setOrdStatus(status);
			purchaseOrderRepo.save(purchOrder);
			return "Purchase Order " + purchOrder.getOrdId() + " updated succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find an order with the id " + id);

		}
	}

	public String deletePurchaseOrder(int id) {
		Optional<PurchaseOrderModel> o = purchaseOrderRepo.findById(id);
		if(o.isPresent()) {
			
			purchaseOrderRepo.deleteById(id);
			return "Purchase Order " + id + " deleted succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find an order with the id " + id);
		}
	}
}
