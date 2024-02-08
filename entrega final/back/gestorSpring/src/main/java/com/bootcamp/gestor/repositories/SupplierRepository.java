package com.bootcamp.gestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.bootcamp.gestor.models.SupplierModel;

public interface SupplierRepository extends JpaRepository<SupplierModel,Integer>{

	

	@Query(value="SELECT * FROM SUPPLIERS s WHERE LOWER(s.sup_bussiness_name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(s.sup_code) LIKE LOWER(CONCAT('%', :searchTerm, '%'))", nativeQuery=true)
	List<SupplierModel> findByTitleContaining(@Param("searchTerm") String searchTerm);

}
