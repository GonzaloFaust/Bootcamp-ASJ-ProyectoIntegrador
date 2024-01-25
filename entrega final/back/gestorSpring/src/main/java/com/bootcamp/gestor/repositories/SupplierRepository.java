package com.bootcamp.gestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.SupplierModel;

public interface SupplierRepository extends JpaRepository<SupplierModel,Integer>{

}
