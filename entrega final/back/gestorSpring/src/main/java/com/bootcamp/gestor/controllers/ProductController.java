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

import com.bootcamp.gestor.models.ProductModel;
import com.bootcamp.gestor.services.ProductService;

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
	public ResponseEntity<Optional<ProductModel>> getProductById(@PathVariable int id ){
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<ProductModel>> createProduct( @RequestBody ProductModel product){
		return ResponseEntity.ok(productService.createProduct(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<ProductModel>> updateProduct(@PathVariable int id, @RequestBody ProductModel product){
		return ResponseEntity.ok(productService.updateProduct(id, product));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ProductModel>> deleteProduct(@PathVariable int id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
