package com.bootcamp.gestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.StateModel;

public interface StateRepository extends JpaRepository<StateModel,Integer>{

}
