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

import com.bootcamp.gestor.models.OrderStatusModel;
import com.bootcamp.gestor.services.OrderStatusService;

@RestController
@RequestMapping("/order-status")
@CrossOrigin(origins="http://localhost:4200")
public class OrderStatusController {
	@Autowired
	OrderStatusService orderStatusService;
	
	@GetMapping()
	public ResponseEntity<List<OrderStatusModel>> getOrderStatuss()
	{
		return ResponseEntity.ok(orderStatusService.getAllOrderStatus());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<OrderStatusModel>> getOrderStatusById(@PathVariable int id ){
		return ResponseEntity.ok(orderStatusService.getOrderStatusById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<OrderStatusModel>> createOrderStatus( @RequestBody OrderStatusModel orderStatus){
		return ResponseEntity.ok(orderStatusService.createOrderStatus(orderStatus));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<OrderStatusModel>> updateOrderStatus(@PathVariable int id, @RequestBody OrderStatusModel orderStatus){
		return ResponseEntity.ok(orderStatusService.updateOrderStatus(id, orderStatus));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<OrderStatusModel>> deleteOrderStatus(@PathVariable int id) {
        return ResponseEntity.ok(orderStatusService.deleteOrderStatus(id));
    }
}
