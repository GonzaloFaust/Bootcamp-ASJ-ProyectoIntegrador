package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.CountryModel;
import com.bootcamp.gestor.repositories.CountryRepository;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepo;
	
	public List<CountryModel> getCountries(){
	    return null;
	}

	public Optional<CountryModel> getCountryById(int id) {
		return null;
	}

	public List<CountryModel> createCountry(CountryModel country){
		return null;
	}

	public List<CountryModel> deleteCountry(int id){
		return null;
	}

	public List<CountryModel> updateCountry(int id, CountryModel country){
		return null;
	}
}
