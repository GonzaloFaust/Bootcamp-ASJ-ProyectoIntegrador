package com.bootcamp.gestor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bootcamp.gestor.models.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel,Integer>{

	@Query(value="SELECT c.* FROM categories c JOIN fields f ON c.field_id=f.field_id JOIN suppliers s ON f.field_id = s.field_id WHERE s.sup_id= :supplier", nativeQuery=true)
	public List<CategoryModel> getCategoriesBySupplier(@Param("supplier")int id);
}
