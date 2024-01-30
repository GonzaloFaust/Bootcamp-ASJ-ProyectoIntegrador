package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.OrderStatusModel;
import com.bootcamp.gestor.repositories.OrderStatusRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderStatusService {

	@Autowired
	OrderStatusRepository orderStatusRepo;
	
	public List<OrderStatusModel> getAllOrderStatuses(){
	    return orderStatusRepo.findAll();
	}

	public OrderStatusModel getOrderStatusById(int id) {
	    return orderStatusRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Couldn´t find an Order Status with the id " + id));
	}

	public List<OrderStatusModel> createOrderStatus(OrderStatusModel orderStatus){
	    if(orderStatus.getOrdstName().isEmpty()) throw new IllegalArgumentException("Name of order status is empty");
	    for(OrderStatusModel o:orderStatusRepo.findAll()) {
	        if(o.getOrdstName().toLowerCase().equals(orderStatus.getOrdstName().toLowerCase()))
	            throw new EntityExistsException("The value already exists");
	    }
	    try {            
	        orderStatusRepo.save(orderStatus);
	        return orderStatusRepo.findAll();
	    }
	    catch(Exception e) {
	        throw new RuntimeException("Error creating order status", e);
	    }
	}

	public String deleteOrderStatus(int id){
	    Optional<OrderStatusModel> orderStatusExists = orderStatusRepo.findById(id);
	    if (orderStatusExists.isPresent()) {
	        OrderStatusModel o = orderStatusExists.get();
	        orderStatusRepo.deleteById(id);;
	        return "Order status " + o.getOrdstId() + " deleted succesfully";
	    } else {
	        throw new EntityNotFoundException("Couldn´t find an Order Status with the id " + id);
	    }
	    
	}

	public String updateOrderStatus(int id, OrderStatusModel orderStatus){
	    Optional<OrderStatusModel> orderStatusExists = orderStatusRepo.findById(id);
	    
	    if (orderStatusExists.isPresent()) {
	        
	        if(orderStatus.getOrdstName().isEmpty()) throw new IllegalArgumentException("Name of order status is empty");
	        for(OrderStatusModel o:orderStatusRepo.findAll()) {
	            if(o.getOrdstName().toLowerCase().equals(orderStatus.getOrdstName().toLowerCase()))
	                throw new EntityExistsException("The value already exists");
	        }

	        OrderStatusModel o = orderStatusExists.get();
	        o.setOrdstName(orderStatus.getOrdstName());
	        orderStatusRepo.save(o);
	        return "Order status " + o.getOrdstId() + " updated succesfully";
	    } else {
	        throw new EntityNotFoundException("Couldn´t find an Order Status with the id " + id);
	    }

	}

//	public List<OrderStatusModel> getAllOrderStatus() {
//		return null;
//	}
//
//	public Optional<OrderStatusModel> getOrderStatusById(int id) {
//		return null;
//	}
//
//	public List<OrderStatusModel> createOrderStatus(OrderStatusModel orderStatus) {
//		return null;
//	}
//
//	public List<OrderStatusModel> deleteOrderStatus(int id) {
//		return null;
//	}
//
//	public List<OrderStatusModel> updateOrderStatus(int id, OrderStatusModel orderStatus) {
//		return null;
//	}
}
