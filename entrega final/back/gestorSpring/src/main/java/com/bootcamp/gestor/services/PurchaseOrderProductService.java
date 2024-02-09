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
	public List<PurchaseOrderProductModel> getPurchaseOrderProductsByOrder(int id){
	    return purchOrdProdRepo.findByOrder(id);
	}
	
	public PurchaseOrderProductModel getPurchaseOrderProductById(long id) {
		return purchOrdProdRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar una orden de compra con el id " + id));
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
				purchOrdProd.setPrice(product.getProdPrice());
				return purchOrdProdRepo.save(purchOrdProd);
			}
			else {
				throw new EntityNotFoundException("No se pudo encontrar la orden o el producto para este detalle de orden");
			}
		}
		else
		{
			throw new IllegalArgumentException("La cantidad de producto debe ser mayor a cero");
		}
	}



}
