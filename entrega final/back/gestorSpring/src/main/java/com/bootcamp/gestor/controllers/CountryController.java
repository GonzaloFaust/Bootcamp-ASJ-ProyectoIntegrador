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

import com.bootcamp.gestor.models.CountryModel;
import com.bootcamp.gestor.services.CountryService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/country")
@CrossOrigin(origins="http://localhost:4200")
public class CountryController {

	@Autowired
	CountryService countryService;
	@GetMapping()
	public ResponseEntity<List<CountryModel>> getCountries() {
		return ResponseEntity.ok(countryService.getCountries());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCountryById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(countryService.getCountryById(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<Object> createCountry(@RequestBody CountryModel country) {
		try {
			return ResponseEntity.ok(countryService.createCountry(country));
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
	public ResponseEntity<Object> updateCountry(@PathVariable int id, @RequestBody CountryModel country) {
		try {
			return ResponseEntity.ok(countryService.updateCountry(id, country));
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
    public ResponseEntity<String> deleteCountry(@PathVariable int id) {
    	try {
    		return ResponseEntity.ok(countryService.deleteCountry(id));
    	} catch (EntityNotFoundException e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    	} catch (Exception e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
