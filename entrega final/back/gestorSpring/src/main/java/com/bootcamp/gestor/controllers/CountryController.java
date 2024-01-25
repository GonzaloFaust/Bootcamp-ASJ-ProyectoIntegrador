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

import com.bootcamp.gestor.models.CountryModel;
import com.bootcamp.gestor.services.CountryService;

@RestController
@RequestMapping("/country")
@CrossOrigin(origins="http://localhost:4200")
public class CountryController {

	@Autowired
	CountryService countryService;
	@GetMapping()
	public ResponseEntity<List<CountryModel>> getCountries()
	{
		return ResponseEntity.ok(countryService.getCountries());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<CountryModel>> getCountryById(@PathVariable int id ){
		return ResponseEntity.ok(countryService.getCountryById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<CountryModel>> createCountry( @RequestBody CountryModel country){
		return ResponseEntity.ok(countryService.createCountry(country));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<CountryModel>> updateCountry(@PathVariable int id, @RequestBody CountryModel country){
		return ResponseEntity.ok(countryService.updateCountry(id, country));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<CountryModel>> deleteVideo(@PathVariable int id) {
        return ResponseEntity.ok(countryService.deleteCountry(id));
    }
}
