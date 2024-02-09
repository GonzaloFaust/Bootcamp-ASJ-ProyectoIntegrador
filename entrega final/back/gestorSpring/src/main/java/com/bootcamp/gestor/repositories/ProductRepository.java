package com.bootcamp.gestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bootcamp.gestor.models.CategoryModel;
import com.bootcamp.gestor.models.ProductModel;
import com.bootcamp.gestor.models.SupplierModel;

public interface ProductRepository extends JpaRepository<ProductModel,Integer>{
	
	@Query(value="SELECT * FROM Products p WHERE (p.prod_name LIKE CONCAT('%', :searchTerm, '%') OR p.prod_description LIKE CONCAT('%', :searchTerm, '%')) AND (p.cat_id = :category OR :category = -1)", nativeQuery=true)
	List<ProductModel> findByTitleContainingAndCategory(@Param("searchTerm") String searchTerm, @Param("category") Integer category);
	
	
	
	List<ProductModel> findBysupplier(SupplierModel supplier);
	
	List<ProductModel> findBycategory(CategoryModel category);
	
}

