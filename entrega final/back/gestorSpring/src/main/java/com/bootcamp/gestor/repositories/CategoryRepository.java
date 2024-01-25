package com.bootcamp.gestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel,Integer>{

}
