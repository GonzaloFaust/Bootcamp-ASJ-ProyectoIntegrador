package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.AddressModel;
import com.bootcamp.gestor.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepo;
	
	public List<AddressModel> getAddresses(){
	    return null;
	}

	public Optional<AddressModel> getAddressById(int id) {
		return null;
	}

	public List<AddressModel> createAddress(AddressModel address){
		return null;
	}

	public List<AddressModel> deleteAddress(int id){
		return null;
	}

	public List<AddressModel> updateAddress(int id, AddressModel address){
		return null;
	}
}
