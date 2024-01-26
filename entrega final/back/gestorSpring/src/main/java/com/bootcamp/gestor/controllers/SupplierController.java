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

import com.bootcamp.gestor.models.SupplierModel;
import com.bootcamp.gestor.services.SupplierService;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins="http://localhost:4200")
public class SupplierController {
	@Autowired
	SupplierService supplierService;
	
	@GetMapping()
	public ResponseEntity<List<SupplierModel>> getSuppliers()
	{
		return ResponseEntity.ok(supplierService.getSuppliers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<SupplierModel>> getSupplierById(@PathVariable int id ){
		return ResponseEntity.ok(supplierService.getSupplierById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<SupplierModel>> createSupplier( @RequestBody SupplierModel product){
		return ResponseEntity.ok(supplierService.createSupplier(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<SupplierModel>> updateSupplier(@PathVariable int id, @RequestBody SupplierModel product){
		return ResponseEntity.ok(supplierService.updateSupplier(id, product));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<SupplierModel>> deleteSupplier(@PathVariable int id) {
        return ResponseEntity.ok(supplierService.deleteSupplier(id));
    }
}
