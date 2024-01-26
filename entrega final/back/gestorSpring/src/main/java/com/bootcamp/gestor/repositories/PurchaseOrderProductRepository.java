package com.bootcamp.gestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.PurchaseOrderProductModel;

public interface PurchaseOrderProductRepository extends JpaRepository<PurchaseOrderProductModel, Long> {

}
