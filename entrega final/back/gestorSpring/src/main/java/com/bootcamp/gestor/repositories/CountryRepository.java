package com.bootcamp.gestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bootcamp.gestor.models.CountryModel;
import com.bootcamp.gestor.models.StateModel;

public interface CountryRepository extends JpaRepository<CountryModel,Integer>{

	
}
