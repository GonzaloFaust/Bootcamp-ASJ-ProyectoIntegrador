package com.bootcamp.gestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.OrderStatusModel;
import com.bootcamp.gestor.models.PurchaseOrderModel;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderModel,Integer>{

	List<PurchaseOrderModel> findByordStatus(OrderStatusModel ordStatus);
}
