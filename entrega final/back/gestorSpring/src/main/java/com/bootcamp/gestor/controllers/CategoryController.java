package com.bootcamp.gestor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.gestor.ErrorHandler;
import com.bootcamp.gestor.models.CategoryModel;
import com.bootcamp.gestor.services.CategoryService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

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
	public ResponseEntity<Object> getCategoryById(@PathVariable int id ){
		try {
		return ResponseEntity.ok(categoryService.getCategoryById(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/by")
	public ResponseEntity<Object> getCategoryBySupplier(@RequestParam("supplier") int id ){
		try {
		return ResponseEntity.ok(categoryService.getCategoryBySupplier(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryModel category, BindingResult bindingResult){
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(categoryService.createCategory(category));
			}
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (EntityExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryModel category, BindingResult bindingResult){
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(categoryService.updateCategory(id, category));
			}
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (EntityExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
       
    	try {
    	return ResponseEntity.ok(categoryService.deleteCategory(id));
    } catch (EntityNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    } catch (EntityExistsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	} catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
}
