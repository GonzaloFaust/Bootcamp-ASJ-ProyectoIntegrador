package com.bootcamp.gestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.bootcamp.gestor.models.PurchaseOrderProductModel;

public interface PurchaseOrderProductRepository extends JpaRepository<PurchaseOrderProductModel, Long> {
	@Query(value="SELECT * FROM purchase_order_product p WHERE  p.ord_id = :order", nativeQuery=true)
	List<PurchaseOrderProductModel> findByOrder(@Param("order") Integer order);

}
