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

import com.bootcamp.gestor.models.AddressModel;
import com.bootcamp.gestor.services.AddressService;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins="http://localhost:4200")
public class AddressController {

	@Autowired
	AddressService addressService;

	@GetMapping()
	public ResponseEntity<List<AddressModel>> getAdresses()
	{
		return ResponseEntity.ok(addressService.getAddresses());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<AddressModel>> getAddressById(@PathVariable int id ){
		return ResponseEntity.ok(addressService.getAddressById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<AddressModel>> createAddress( @RequestBody AddressModel address){
		return ResponseEntity.ok(addressService.createAddress(address));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<AddressModel>> updateAddress(@PathVariable int id, @RequestBody AddressModel address){
		return ResponseEntity.ok(addressService.updateAddress(id, address));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<AddressModel>> deleteAddress(@PathVariable int id) {
        return ResponseEntity.ok(addressService.deleteAddress(id));
    }
}
