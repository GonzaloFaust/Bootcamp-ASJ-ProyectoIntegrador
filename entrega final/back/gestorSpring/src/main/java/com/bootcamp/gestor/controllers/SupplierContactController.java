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

import com.bootcamp.gestor.models.SupplierContactModel;
import com.bootcamp.gestor.services.SupplierContactService;

@RestController
@RequestMapping("/supplier-contact")
@CrossOrigin(origins="http://localhost:4200")
public class SupplierContactController {
	@Autowired 
	SupplierContactService supplierContactService;
	
	@GetMapping()
	public ResponseEntity<List<SupplierContactModel>> getSupplierContacts()
	{
		return ResponseEntity.ok(supplierContactService.getAllSupplierContacts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<SupplierContactModel>> getSupplierContactById(@PathVariable int id ){
		return ResponseEntity.ok(supplierContactService.getSupplierContactById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<SupplierContactModel>> createSupplierContact( @RequestBody SupplierContactModel supplierContact){
		return ResponseEntity.ok(supplierContactService.createSupplierContact(supplierContact));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<SupplierContactModel>> updateSupplierContact(@PathVariable int id, @RequestBody SupplierContactModel supplierContact){
		return ResponseEntity.ok(supplierContactService.updateSupplierContact(id, supplierContact));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<SupplierContactModel>> deleteSupplierContact(@PathVariable int id) {
        return ResponseEntity.ok(supplierContactService.deleteSupplierContact(id));
    }
}