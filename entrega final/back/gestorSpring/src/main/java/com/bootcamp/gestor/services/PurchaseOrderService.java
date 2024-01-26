package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.PurchaseOrderModel;
import com.bootcamp.gestor.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepo;
	
	public List<PurchaseOrderModel> getAllPurchaseOrders(){
	    return null;
	}

	public Optional<PurchaseOrderModel> getPurchaseOrderById(int id) {
		return null;
	}

	public List<PurchaseOrderModel> createPurchaseOrder(PurchaseOrderModel purchOrder){
		return null;
	}

	public List<PurchaseOrderModel> deletePurchaseOrder(int id){
		return null;
	}

	public List<PurchaseOrderModel> updatePurchaseOrder(int id, PurchaseOrderModel purchOrder){
		return null;
	}
}
