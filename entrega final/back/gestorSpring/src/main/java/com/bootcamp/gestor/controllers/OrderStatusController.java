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
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.gestor.ErrorHandler;
import com.bootcamp.gestor.models.OrderStatusModel;
import com.bootcamp.gestor.services.OrderStatusService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order-status")
@CrossOrigin(origins="http://localhost:4200")
public class OrderStatusController {
	@Autowired
	OrderStatusService orderStatusService;
	
	@GetMapping()
	public ResponseEntity<List<OrderStatusModel>> getOrderStatuses() {
		return ResponseEntity.ok(orderStatusService.getAllOrderStatuses());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOrderStatusById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(orderStatusService.getOrderStatusById(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<Object> createOrderStatus(@Valid @RequestBody OrderStatusModel orderStatus, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(orderStatusService.createOrderStatus(orderStatus));
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
	public ResponseEntity<Object> updateOrderStatus(@PathVariable int id, @Valid @RequestBody OrderStatusModel orderStatus, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(orderStatusService.updateOrderStatus(id, orderStatus));
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
	public ResponseEntity<String> deleteOrderStatus(@PathVariable int id) {
		try {
			return ResponseEntity.ok(orderStatusService.deleteOrderStatus(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@GetMapping()
//	public ResponseEntity<List<OrderStatusModel>> getOrderStatuss()
//	{
//		return ResponseEntity.ok(orderStatusService.getAllOrderStatus());
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Optional<OrderStatusModel>> getOrderStatusById(@PathVariable int id ){
//		return ResponseEntity.ok(orderStatusService.getOrderStatusById(id));
//	}
//	
//	@PostMapping()
//	public ResponseEntity<List<OrderStatusModel>> createOrderStatus( @RequestBody OrderStatusModel orderStatus){
//		return ResponseEntity.ok(orderStatusService.createOrderStatus(orderStatus));
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<List<OrderStatusModel>> updateOrderStatus(@PathVariable int id, @RequestBody OrderStatusModel orderStatus){
//		return ResponseEntity.ok(orderStatusService.updateOrderStatus(id, orderStatus));
//	}
//	
//	
//    @DeleteMapping("/{id}")
//    public ResponseEntity<List<OrderStatusModel>> deleteOrderStatus(@PathVariable int id) {
//        return ResponseEntity.ok(orderStatusService.deleteOrderStatus(id));
//    }
}
