package com.bootcamp.gestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.OrderStatusModel;

public interface OrderStatusRepository extends JpaRepository<OrderStatusModel,Integer>{

}
