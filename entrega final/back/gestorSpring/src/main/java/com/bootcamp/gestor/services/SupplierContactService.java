package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.SupplierContactModel;
import com.bootcamp.gestor.repositories.SupplierContactRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SupplierContactService {

	@Autowired
	SupplierContactRepository supplierContactRepo;
	
	public List<SupplierContactModel> getAllSupplierContacts(){
	    return supplierContactRepo.findAll();
	}

	public SupplierContactModel getSupplierContactById(int id) {
	    return supplierContactRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Couldn´t find a Supplier Contact with the id " + id));
	}

	public SupplierContactModel createSupplierContact(SupplierContactModel supplierContact){
	    if(supplierContact.getSupContactName().isEmpty()) throw new IllegalArgumentException("Name of supplier contact is empty");
	    for(SupplierContactModel s:supplierContactRepo.findAll()) {
	        if(s.getSupContactName().toLowerCase().equals(supplierContact.getSupContactName().toLowerCase()))
	            throw new EntityExistsException("The value already exists");
	    }
	   // try {            
	        supplierContactRepo.save(supplierContact);
	        return supplierContactRepo.findById(supplierContact.getSupContactId()).get();
	 //   }
	  //  catch(Exception e) {
	       // throw new RuntimeException("Error creating supplier contact", e);
	   // }
	}

	public String deleteSupplierContact(int id){
	    Optional<SupplierContactModel> supplierContactExists = supplierContactRepo.findById(id);
	    if (supplierContactExists.isPresent()) {
	        SupplierContactModel s = supplierContactExists.get();
	        supplierContactRepo.deleteById(id);;
	        return "Supplier contact " + s.getSupContactId() + " deleted succesfully";
	    } else {
	        throw new EntityNotFoundException("Couldn´t find a Supplier Contact with the id " + id);
	    }
	    
	}

	public String updateSupplierContact(int id, SupplierContactModel supplierContact){
	    Optional<SupplierContactModel> supplierContactExists = supplierContactRepo.findById(id);
	    
	    if (supplierContactExists.isPresent()) {
	        
	        if(supplierContact.getSupContactName().isEmpty()) throw new IllegalArgumentException("Name of supplier contact is empty");
	        for(SupplierContactModel s:supplierContactRepo.findAll()) {
	            if(s.getSupContactName().toLowerCase().equals(supplierContact.getSupContactName().toLowerCase()))
	                throw new EntityExistsException("The value already exists");
	        }

	        SupplierContactModel s = supplierContactExists.get();
	        s.setSupContactName(supplierContact.getSupContactName());
	        supplierContactRepo.save(s);
	        return "Supplier contact " + s.getSupContactId() + " updated succesfully";
	    } else {
	        throw new EntityNotFoundException("Couldn´t find a Supplier Contact with the id " + id);
	    }

	}
	
//	public List<SupplierContactModel> getAllSupplierContacts(){
//	    return null;
//	}
//
//	public Optional<SupplierContactModel> getSupplierContactById(int id) {
//		return null;
//	}
//
//	public List<SupplierContactModel> createSupplierContact(SupplierContactModel supplierContact){
//		return null;
//	}
//
//	public List<SupplierContactModel> deleteSupplierContact(int id){
//		return null;
//	}
//
//	public List<SupplierContactModel> updateSupplierContact(int id, SupplierContactModel supplierContact){
//		return null;
//	}
}
