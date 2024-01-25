package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.CategoryModel;
import com.bootcamp.gestor.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepo;
	
	public List<CategoryModel> getCategories(){
	    return null;
	}

	public Optional<CategoryModel> getCategoryById(int id) {
		return null;
	}
	
	public List<CategoryModel> createCategory(CategoryModel category){
		return null;
	}

	public List<CategoryModel> deleteCategory(int id){
		return null;
	}

	public List<CategoryModel> updateCategory(int id, CategoryModel category){
		return null;
	}
}
