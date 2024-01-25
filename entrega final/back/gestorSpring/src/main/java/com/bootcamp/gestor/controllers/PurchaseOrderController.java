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

import com.bootcamp.gestor.models.PurchaseOrderModel;
import com.bootcamp.gestor.services.PurchaseOrderService;

@RestController
@RequestMapping("/purchase-order")
@CrossOrigin(origins="http://localhost:4200")
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@GetMapping()
	public ResponseEntity<List<PurchaseOrderModel>> getPurchaseOrders()
	{
		return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<PurchaseOrderModel>> getPurchaseOrderById(@PathVariable int id ){
		return ResponseEntity.ok(purchaseOrderService.getPurchaseOrderById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<PurchaseOrderModel>> createPurchaseOrder( @RequestBody PurchaseOrderModel purchaseOrder){
		return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(purchaseOrder));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<PurchaseOrderModel>> updatePurchaseOrder(@PathVariable int id, @RequestBody PurchaseOrderModel purchaseOrder){
		return ResponseEntity.ok(purchaseOrderService.updatePurchaseOrder(id, purchaseOrder));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<PurchaseOrderModel>> deletePurchaseOrder(@PathVariable int id) {
        return ResponseEntity.ok(purchaseOrderService.deletePurchaseOrder(id));
    }
	
}
