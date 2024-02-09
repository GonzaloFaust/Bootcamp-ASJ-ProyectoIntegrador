package com.bootcamp.gestor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.gestor.ErrorHandler;
import com.bootcamp.gestor.models.PurchaseOrderModel;
import com.bootcamp.gestor.services.PurchaseOrderService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/purchase-order")
@CrossOrigin(origins = "http://localhost:4200")
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService purchaseOrderService;

	@GetMapping()
	public ResponseEntity<List<PurchaseOrderModel>> getPurchaseOrders() {
		return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
	}
	
	@GetMapping("/by")
	public ResponseEntity<List<PurchaseOrderModel>> getProductsBySupplier(@RequestParam("status") int id){

		return ResponseEntity.ok(purchaseOrderService.getOrderByStatus(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getPurchaseOrderById(@PathVariable String id) {
		try {
			int numberId = Integer.parseInt(id);
			return ResponseEntity.ok(purchaseOrderService.getPurchaseOrderById(numberId));
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<Object> createPurchaseOrder(@Valid @RequestBody PurchaseOrderModel purchaseOrder, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(purchaseOrder));
			}
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
	public ResponseEntity<String> updatePurchaseOrder(@PathVariable int id, @Valid @RequestBody PurchaseOrderModel purchaseOrder, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(purchaseOrderService.updatePurchaseOrder(id, purchaseOrder));
			}
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


	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePurchaseOrder(@PathVariable int id) {
		try {
			return ResponseEntity.ok(purchaseOrderService.deletePurchaseOrder(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
