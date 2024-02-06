package com.bootcamp.gestor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/purchase-order-product")
@CrossOrigin(origins = "http://localhost:4200")
public class PurchaseOrderProductController {

	@Autowired
	PurchaseOrderProductService purchOrdProdService;

	@GetMapping()
	public ResponseEntity<List<PurchaseOrderProductModel>> getPurchaseOrders()
	{
		return ResponseEntity.ok(purchOrdProdService.getAllPurchaseOrderProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getPurchaseOrderProductById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(purchOrdProdService.getPurchaseOrderProductById(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<Object> createPurchaseOrderProduct(
			@RequestBody PurchaseOrderProductModel purchaseOrder) {
		try {
		return new ResponseEntity<>(purchOrdProdService.createPurchaseOrderProduct(purchaseOrder), HttpStatus.CREATED);
	} catch (EntityNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	} catch (IllegalArgumentException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	} catch (EntityExistsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	} catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updatePurchaseOrderProduct(@PathVariable int id,
			@RequestBody PurchaseOrderProductModel purchaseOrder) {
		return new ResponseEntity<>("This action is not allowed", HttpStatus.METHOD_NOT_ALLOWED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePurchaseOrderProduct(@PathVariable int id) {
		return new ResponseEntity<>("This action is not allowed", HttpStatus.METHOD_NOT_ALLOWED);
	}
}
