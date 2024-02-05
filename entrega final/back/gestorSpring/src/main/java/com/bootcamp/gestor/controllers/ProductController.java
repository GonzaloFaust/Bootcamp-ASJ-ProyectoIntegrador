package com.bootcamp.gestor.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.bootcamp.gestor.models.ProductModel;
import com.bootcamp.gestor.services.ProductService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins="http://localhost:4200")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping()
	public ResponseEntity<List<ProductModel>> getProducts()
	{
		return ResponseEntity.ok(productService.getProducts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable int id ){
		try {
		return ResponseEntity.ok(productService.getProductById(id));
	} catch (EntityNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	} catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	
	
	@PostMapping()
	public ResponseEntity<Object> createProduct( @RequestBody ProductModel product){
		try {
		return ResponseEntity.ok(productService.createProduct(product));
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
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody ProductModel product){
		try {
		return ResponseEntity.ok(productService.updateProduct(id, product));
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
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        try {
    	return ResponseEntity.ok(productService.deleteProduct(id));
        } catch (EntityNotFoundException e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	} catch (Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
