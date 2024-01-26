package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.SupplierContactModel;
import com.bootcamp.gestor.repositories.SupplierContactRepository;

@Service
public class SupplierContactService {

	@Autowired
	SupplierContactRepository supplierContactRepo;
	
	public List<SupplierContactModel> getAllSupplierContacts(){
	    return null;
	}

	public Optional<SupplierContactModel> getSupplierContactById(int id) {
		return null;
	}

	public List<SupplierContactModel> createSupplierContact(SupplierContactModel supplierContact){
		return null;
	}

	public List<SupplierContactModel> deleteSupplierContact(int id){
		return null;
	}

	public List<SupplierContactModel> updateSupplierContact(int id, SupplierContactModel supplierContact){
		return null;
	}
}
