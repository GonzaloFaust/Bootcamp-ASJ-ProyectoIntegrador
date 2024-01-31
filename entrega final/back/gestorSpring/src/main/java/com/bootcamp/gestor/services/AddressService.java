package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.AddressModel;
import com.bootcamp.gestor.models.StateModel;
import com.bootcamp.gestor.repositories.AddressRepository;
import com.bootcamp.gestor.repositories.StateRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepo;
	
	@Autowired
	StateRepository stateRepo;
	
	public List<AddressModel> getAddresses(){
	    return addressRepo.findAll();
	}

	public AddressModel getAddressById(Long id) {
		
			return addressRepo.findById(id).orElseThrow();

	}
	
	
	public List<AddressModel> createAddress(AddressModel address) {
//		try {
		if (address.getAddrStreet().isEmpty() || address.getCityName().isEmpty() || address.getAddrPostcode().isEmpty())
			throw new IllegalArgumentException("There's missing data");
		Optional<StateModel> s = stateRepo.findById(address.getState().getStateId());
		if (s.isPresent()) {
			StateModel state = s.get();
			for (AddressModel a : addressRepo.findAll()) {
				if (a.getAddrStreet().toLowerCase().equals(address.getAddrStreet().toLowerCase())
						&& a.getAddrPostcode().toLowerCase().equals(address.getAddrPostcode().toLowerCase() )
						&& a.getCityName().toLowerCase().equals(address.getCityName().toLowerCase() )
						&& a.getAddrApartment().toLowerCase().equals(address.getAddrApartment().toLowerCase() )
						&& a.getAddrFloor() == address.getAddrFloor()
						&& a.getState().getStateId()==state.getStateId()
						)
					throw new EntityExistsException("The value already exists");
			}
			address.setState(state);
		} else {
			throw new EntityNotFoundException("Couldn't find the state of this address");
		}

			addressRepo.save(address);
			return addressRepo.findAll();

//		} catch (
//
//		Exception e) {
//			throw new RuntimeException("Error creating state", e);
//		}
	}
	public String updateAddress(Long id, AddressModel address) {
		Optional<AddressModel> addressExists = addressRepo.findById(id);
		
		if (addressExists.isPresent()) {
			
			if (address.getAddrStreet().isEmpty() || address.getCityName().isEmpty() || address.getAddrPostcode().isEmpty())
				throw new IllegalArgumentException("There's missing data");
			
			Optional<StateModel> s = stateRepo.findById(address.getState().getStateId());
			
			if (s.isPresent()) {
				for (AddressModel a : addressRepo.findAll()) {
					if (a.getAddrStreet().toLowerCase().equals(address.getAddrStreet().toLowerCase())
							&& a.getAddrPostcode().toLowerCase().equals(address.getAddrPostcode().toLowerCase() )
							&& a.getCityName().toLowerCase().equals(address.getCityName().toLowerCase() )
							&& a.getAddrApartment().toLowerCase().equals(address.getAddrApartment().toLowerCase() )
							&& a.getAddrFloor() == address.getAddrFloor()
							&& a.getState().getStateId()==address.getState().getStateId()
							)
						throw new EntityExistsException("The value already exists");
				}
				
				AddressModel a = addressExists.get();
				StateModel state= s.get();
				a.setAddrStreet(address.getAddrStreet());
				a.setAddrPostcode(address.getAddrPostcode());
				a.setCityName(address.getCityName());
				a.setAddrFloor(address.getAddrFloor());
				a.setAddrApartment(address.getAddrApartment());
				a.setState(state);
				
				
				addressRepo.save(a);
				return "Address " + a.getAddrId() + " updated succesfully";
			}
			else {
				throw new EntityNotFoundException("Couldn´t find a State for this address ");
			}
		} else {
			throw new EntityNotFoundException("Couldn´t find an address with the id " + id);
		}
		
	}

	public String deleteAddress(Long id) {
		Optional<AddressModel> addressExists = addressRepo.findById(id);
		if (addressExists.isPresent()) {
			AddressModel a = addressExists.get();
			addressRepo.deleteById(id);
			return "Address " + a.getAddrId() + " deleted succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find an address with the id " + id);
		}

	}


//	public List<AddressModel> createAddress(AddressModel address){
//		return null;
//	}
//
//	public List<AddressModel> deleteAddress(int id){
//		return null;
//	}
//
//	public List<AddressModel> updateAddress(int id, AddressModel address){
//		return null;
//	}
}
