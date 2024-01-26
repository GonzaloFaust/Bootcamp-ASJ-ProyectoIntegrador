package com.bootcamp.gestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.TaxConditionModel;

public interface TaxConditionRepository extends JpaRepository<TaxConditionModel,Integer>{

}
