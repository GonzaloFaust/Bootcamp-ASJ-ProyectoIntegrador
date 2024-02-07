package com.bootcamp.gestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bootcamp.gestor.models.StateModel;

public interface StateRepository extends JpaRepository<StateModel,Integer>{

	@Query(value="SELECT s.* FROM states s WHERE s.country_id = :coun_id", nativeQuery=true)
	List<StateModel> getStatesByCountry(@Param("coun_id") int coun_id);
}


