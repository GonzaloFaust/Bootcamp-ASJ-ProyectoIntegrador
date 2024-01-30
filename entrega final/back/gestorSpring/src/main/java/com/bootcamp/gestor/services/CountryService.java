package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.CountryModel;
import com.bootcamp.gestor.repositories.CountryRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepo;
	
	public List<CountryModel> getCountries(){
	    return countryRepo.findAll();
	}

	public CountryModel getCountryById(int id) {
	    return countryRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Couldn´t find a Country with the id " + id));
	}

	public List<CountryModel> createCountry(CountryModel country){
	    if(country.getCounName().isEmpty()) throw new IllegalArgumentException("Name of country is empty");
	    for(CountryModel c:countryRepo.findAll()) {
	        if(c.getCounName().toLowerCase().equals(country.getCounName().toLowerCase()))
	            throw new EntityExistsException("The value already exists");
	    }
	    try {            
	        countryRepo.save(country);
	        return countryRepo.findAll();
	    }
	    catch(Exception e) {
	        throw new RuntimeException("Error creating country", e);
	    }
	}

	public String deleteCountry(int id){
	    Optional<CountryModel> countryExists = countryRepo.findById(id);
	    if (countryExists.isPresent()) {
	        CountryModel c = countryExists.get();
	        countryRepo.deleteById(id);;
	        return "Country " + c.getCounId() + " deleted succesfully";
	    } else {
	        throw new EntityNotFoundException("Couldn´t find a Country with the id " + id);
	    }
	    
	}

	public String updateCountry(int id, CountryModel country){
	    Optional<CountryModel> countryExists = countryRepo.findById(id);
	    
	    if (countryExists.isPresent()) {
	        
	        if(country.getCounName().isEmpty()) throw new IllegalArgumentException("Name of country is empty");
	        for(CountryModel c:countryRepo.findAll()) {
	            if(c.getCounName().toLowerCase().equals(country.getCounName().toLowerCase()))
	                throw new EntityExistsException("The value already exists");
	        }

	        CountryModel c = countryExists.get();
	        c.setCounName(country.getCounName());
	        countryRepo.save(c);
	        return "Country " + c.getCounId() + " updated succesfully";
	    } else {
	        throw new EntityNotFoundException("Couldn´t find a Country with the id " + id);
	    }

	}
}
