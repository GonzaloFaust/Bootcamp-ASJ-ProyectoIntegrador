package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.OrderStatusModel;
import com.bootcamp.gestor.repositories.OrderStatusRepository;

@Service
public class OrderStatusService {

	@Autowired
	OrderStatusRepository orderStatusRepo;

	public List<OrderStatusModel> getAllOrderStatus() {
		return null;
	}

	public Optional<OrderStatusModel> getOrderStatusById(int id) {
		return null;
	}

	public List<OrderStatusModel> createOrderStatus(OrderStatusModel orderStatus) {
		return null;
	}

	public List<OrderStatusModel> deleteOrderStatus(int id) {
		return null;
	}

	public List<OrderStatusModel> updateOrderStatus(int id, OrderStatusModel orderStatus) {
		return null;
	}
}
