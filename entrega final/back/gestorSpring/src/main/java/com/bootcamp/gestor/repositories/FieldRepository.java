package com.bootcamp.gestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.FieldModel;

public interface FieldRepository extends JpaRepository<FieldModel,Integer>{

}
