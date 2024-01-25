package com.bootcamp.gestor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.gestor.models.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel,Long>{

}
