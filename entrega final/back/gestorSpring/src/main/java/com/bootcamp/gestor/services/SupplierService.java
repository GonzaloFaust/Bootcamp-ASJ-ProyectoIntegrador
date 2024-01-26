package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.SupplierModel;
import com.bootcamp.gestor.repositories.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	SupplierRepository supplierRepo;
	
	public List<SupplierModel> getSuppliers(){
	    return null;
	}

	public Optional<SupplierModel> getSupplierById(int id) {
		return null;
	}

	public List<SupplierModel> createSupplier(SupplierModel supplier){
		return null;
	}

	public List<SupplierModel> deleteSupplier(int id){
		return null;
	}

	public List<SupplierModel> updateSupplier(int id, SupplierModel supplier){
		return null;
	}
}
