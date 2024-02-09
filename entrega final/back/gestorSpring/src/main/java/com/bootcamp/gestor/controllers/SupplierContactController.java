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
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.gestor.ErrorHandler;
import com.bootcamp.gestor.models.SupplierContactModel;
import com.bootcamp.gestor.services.SupplierContactService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplier-contact")
@CrossOrigin(origins="http://localhost:4200")
public class SupplierContactController {
	@Autowired 
	SupplierContactService supplierContactService;
	
	@GetMapping()
	public ResponseEntity<List<SupplierContactModel>> getSupplierContacts() {
		return ResponseEntity.ok(supplierContactService.getAllSupplierContacts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getSupplierContactById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(supplierContactService.getSupplierContactById(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<Object> createSupplierContact(@Valid @RequestBody SupplierContactModel supplierContact, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(supplierContactService.createSupplierContact(supplierContact));
			}
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (EntityExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateSupplierContact(@PathVariable int id, @Valid @RequestBody SupplierContactModel supplierContact, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(supplierContactService.updateSupplierContact(id, supplierContact));
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
	public ResponseEntity<String> deleteSupplierContact(@PathVariable int id) {
		try {
			return ResponseEntity.ok(supplierContactService.deleteSupplierContact(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	
//	@GetMapping()
//	public ResponseEntity<List<SupplierContactModel>> getSupplierContacts()
//	{
//		return ResponseEntity.ok(supplierContactService.getAllSupplierContacts());
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Optional<SupplierContactModel>> getSupplierContactById(@PathVariable int id ){
//		return ResponseEntity.ok(supplierContactService.getSupplierContactById(id));
//	}
//	
//	@PostMapping()
//	public ResponseEntity<List<SupplierContactModel>> createSupplierContact( @RequestBody SupplierContactModel supplierContact){
//		return ResponseEntity.ok(supplierContactService.createSupplierContact(supplierContact));
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<List<SupplierContactModel>> updateSupplierContact(@PathVariable int id, @RequestBody SupplierContactModel supplierContact){
//		return ResponseEntity.ok(supplierContactService.updateSupplierContact(id, supplierContact));
//	}
//	
//	
//    @DeleteMapping("/{id}")
//    public ResponseEntity<List<SupplierContactModel>> deleteSupplierContact(@PathVariable int id) {
//        return ResponseEntity.ok(supplierContactService.deleteSupplierContact(id));
//    }
}
