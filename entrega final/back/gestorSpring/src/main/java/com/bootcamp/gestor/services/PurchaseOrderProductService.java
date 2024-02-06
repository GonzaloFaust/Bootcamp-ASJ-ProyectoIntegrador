package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.ProductModel;
import com.bootcamp.gestor.models.PurchaseOrderModel;
import com.bootcamp.gestor.models.PurchaseOrderProductModel;
import com.bootcamp.gestor.repositories.ProductRepository;
import com.bootcamp.gestor.repositories.PurchaseOrderProductRepository;
import com.bootcamp.gestor.repositories.PurchaseOrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PurchaseOrderProductService {

	@Autowired
	PurchaseOrderProductRepository purchOrdProdRepo;
	
	@Autowired
	PurchaseOrderRepository orderRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	public List<PurchaseOrderProductModel> getAllPurchaseOrderProducts(){
	    return purchOrdProdRepo.findAll();
	}

	public PurchaseOrderProductModel getPurchaseOrderProductById(long id) {
		return purchOrdProdRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Couldn´t find a purchase order item with the id " + id));
	}

	public PurchaseOrderProductModel createPurchaseOrderProduct(PurchaseOrderProductModel purchOrdProd){
		if(purchOrdProd.getProdQuantity()>0) {
			
			Optional<PurchaseOrderModel> order=orderRepo.findById(purchOrdProd.getOrder().getOrdId());
			Optional<ProductModel> prod =productRepo.findById(purchOrdProd.getProduct().getProdId());
			if(order.isPresent() && prod.isPresent()) {
				PurchaseOrderModel purchOrder= order.get();
				ProductModel product= prod.get();
				purchOrdProd.setOrder(purchOrder);
				purchOrdProd.setProduct(product);
				return purchOrdProdRepo.save(purchOrdProd);
			}
			else {
				throw new EntityNotFoundException("Couldn't find order or product of this order item");
			}
		}
		else
		{
			throw new IllegalArgumentException("Product quantity must be greater than zero");
		}
	}



}
