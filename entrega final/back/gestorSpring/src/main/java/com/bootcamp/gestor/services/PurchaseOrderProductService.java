package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.PurchaseOrderProductModel;
import com.bootcamp.gestor.repositories.PurchaseOrderProductRepository;

@Service
public class PurchaseOrderProductService {

	@Autowired
	PurchaseOrderProductRepository purchOrdProdRepo;
	
	public List<PurchaseOrderProductModel> getAddresses(){
	    return null;
	}

	public Optional<PurchaseOrderProductModel> getPurchaseOrderProductById(int id) {
		return null;
	}

	public List<PurchaseOrderProductModel> createPurchaseOrderProduct(PurchaseOrderProductModel purchOrdProd){
		return null;
	}

	public List<PurchaseOrderProductModel> deletePurchaseOrderProduct(int id){
		return null;
	}

	public List<PurchaseOrderProductModel> updatePurchaseOrderProduct(int id, PurchaseOrderProductModel purchOrdProd){
		return null;
	}
}
