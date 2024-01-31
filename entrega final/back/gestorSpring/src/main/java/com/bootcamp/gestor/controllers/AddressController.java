package com.bootcamp.gestor.controllers;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.bootcamp.gestor.models.AddressModel;
import com.bootcamp.gestor.services.AddressService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins="http://localhost:4200")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	
	@GetMapping()
	public ResponseEntity<List<AddressModel>> getAddresses() {
		return ResponseEntity.ok(addressService.getAddresses());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getAddressById(@PathVariable String id ){
		try {
			Long numberId = Long.parseLong(id);
			return ResponseEntity.ok(addressService.getAddressById(numberId));
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		catch(NumberFormatException e) {
			
			return new ResponseEntity<>("Expected a number",HttpStatus.BAD_REQUEST);
		}
	}
//	@GetMapping("/{id}")
//	public ResponseEntity<Object> getAddressById(@PathVariable Long id) {
//		try {
//			return ResponseEntity.ok(addressService.getAddressById(id));
//		} catch (EntityNotFoundException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@PostMapping()
	public ResponseEntity<Object> createAddress(@RequestBody AddressModel address) {
		try {
			return ResponseEntity.ok(addressService.createAddress(address));
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
	public ResponseEntity<Object> updateAddress(@PathVariable Long id, @RequestBody AddressModel address) {
		try {
			return ResponseEntity.ok(addressService.updateAddress(id, address));
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
	public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(addressService.deleteAddress(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	

//	@GetMapping()
//	public ResponseEntity<List<AddressModel>> getAdresses()
//	{
//		return ResponseEntity.ok(addressService.getAddresses());
//	}
	
	
	
//	@PostMapping()
//	public ResponseEntity<List<AddressModel>> createAddress( @RequestBody AddressModel address){
//		return ResponseEntity.ok(addressService.createAddress(address));
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<List<AddressModel>> updateAddress(@PathVariable int id, @RequestBody AddressModel address){
//		return ResponseEntity.ok(addressService.updateAddress(id, address));
//	}
//	
//	
//    @DeleteMapping("/{id}")
//    public ResponseEntity<List<AddressModel>> deleteAddress(@PathVariable int id) {
//        return ResponseEntity.ok(addressService.deleteAddress(id));
//    }
}
