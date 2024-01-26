package com.bootcamp.gestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel,Integer>{

}
