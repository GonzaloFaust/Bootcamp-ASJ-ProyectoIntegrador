package com.bootcamp.gestor.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.gestor.models.CategoryModel;
import com.bootcamp.gestor.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins="http://localhost:4200")
public class CategoryController {

	@Autowired 
	CategoryService categoryService;
	
	@GetMapping()
	public ResponseEntity<List<CategoryModel>> getCategories()
	{
		return ResponseEntity.ok(categoryService.getCategories());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<CategoryModel>> getCategoryById(@PathVariable int id ){
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<CategoryModel>> createCategory( @RequestBody CategoryModel category){
		return ResponseEntity.ok(categoryService.createCategory(category));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<CategoryModel>> updateCategory(@PathVariable int id, @RequestBody CategoryModel category){
		return ResponseEntity.ok(categoryService.updateCategory(id, category));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<CategoryModel>> deleteCategory(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}
