package com.bootcamp.gestor.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.gestor.models.PurchaseOrderProductModel;
import com.bootcamp.gestor.services.PurchaseOrderProductService;

@RestController
@RequestMapping("/purchase-order-product")
@CrossOrigin(origins="http://localhost:4200")
public class PurchaseOrderProductController {

	@Autowired
	PurchaseOrderProductService purchOrdProdService;
	
//	@GetMapping()
//	public ResponseEntity<List<PurchaseOrderProductModel>> getPurchaseOrders()
//	{
//		return ResponseEntity.ok(purchOrdProdService.get());
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<PurchaseOrderProductModel>> getPurchaseOrderProductById(@PathVariable int id ){
		return ResponseEntity.ok(purchOrdProdService.getPurchaseOrderProductById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<PurchaseOrderProductModel>> createPurchaseOrderProduct( @RequestBody PurchaseOrderProductModel purchaseOrder){
		return ResponseEntity.ok(purchOrdProdService.createPurchaseOrderProduct(purchaseOrder));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<PurchaseOrderProductModel>> updatePurchaseOrderProduct(@PathVariable int id, @RequestBody PurchaseOrderProductModel purchaseOrder){
		return ResponseEntity.ok(purchOrdProdService.updatePurchaseOrderProduct(id, purchaseOrder));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<PurchaseOrderProductModel>> deletePurchaseOrderProduct(@PathVariable int id) {
        return ResponseEntity.ok(purchOrdProdService.deletePurchaseOrderProduct(id));
    }
}
