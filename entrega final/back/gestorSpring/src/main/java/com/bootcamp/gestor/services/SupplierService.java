package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.bootcamp.gestor.models.AddressModel;
import com.bootcamp.gestor.models.FieldModel;
//import com.bootcamp.gestor.models.SupplierContactModel;
import com.bootcamp.gestor.models.SupplierModel;
import com.bootcamp.gestor.models.TaxConditionModel;
import com.bootcamp.gestor.repositories.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SupplierService {

	@Autowired
	SupplierRepository supplierRepo;
	
	@Autowired
	FieldService fieldService;
	
	@Autowired
	TaxConditionService taxCondService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	SupplierContactService supplierContactService;
	
	public List<SupplierModel> getSuppliers(){
	    return supplierRepo.findAll();
	}

	public SupplierModel getSupplierById(int id) {
		return supplierRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Couldn´t find a supplier with the id " + id));
	}

	public SupplierModel createSupplier(SupplierModel supplier){
//		FieldModel field= fieldService.getFieldById(supplier.getField().getFieldId());
//		//AddressModel address= addressService.createAddress(supplier.getAddress());
//		//SupplierContactModel supContact= supplierContactService.createSupplierContact(supplier.getSupContact());
//		TaxConditionModel tax =taxCondService.getTaxConditionById(supplier.getTaxCond().getTaxId());
//		
//		//supplier.setAddress(address);
//		//supplier.setSupContact(supContact);
//		supplier.setField(field);
//		supplier.setTaxCond(tax);
//		supplierRepo.save(supplier);
//		return supplierRepo.findById(supplier.getSupId()).get();
		
		
		
	
	    if (supplier.getSupCode().isEmpty() || supplier.getSupBussinessName().isEmpty() || supplier.getSupImage().isEmpty() || supplier.getSupWebsite().isEmpty() || supplier.getSupEmail().isEmpty() || supplier.getSupTelephone().isEmpty() || supplier.getSupCuit().isEmpty()) {
	        throw new IllegalArgumentException("There's missing data");
	    }

	    FieldModel fieldExists = fieldService.getFieldById(supplier.getField().getFieldId());
	    TaxConditionModel taxExists = taxCondService.getTaxConditionById(supplier.getTaxCond().getTaxId());
	    supplier.setField(fieldExists);
	    supplier.setTaxCond(taxExists);
	    supplierRepo.save(supplier);

	    Optional<SupplierModel> supplierExists = supplierRepo.findById(supplier.getSupId());
	    if (!supplierExists.isPresent()) {
	        throw new EntityNotFoundException("Couldn't find a Supplier with the id " + supplier.getSupId());
	    }

	    return supplierExists.get();
	}

	

	public String updateSupplier(int id, SupplierModel supplier){
		Optional<SupplierModel> supplierExists = supplierRepo.findById(id);
		if(!supplierExists.isPresent()) {
	        throw new EntityNotFoundException("Couldn't find a Supplier with the id " + id);
	    }
		else {
			if (supplier.getSupCode().isEmpty() || supplier.getSupBussinessName().isEmpty() || supplier.getSupImage().isEmpty() || supplier.getSupWebsite().isEmpty() || supplier.getSupEmail().isEmpty() || supplier.getSupTelephone().isEmpty() || supplier.getSupCuit().isEmpty()) {
		        throw new IllegalArgumentException("There's missing data");
		    }

			SupplierModel sup=supplierExists.get();
		    FieldModel fieldExists = fieldService.getFieldById(supplier.getField().getFieldId());
		    TaxConditionModel taxExists = taxCondService.getTaxConditionById(supplier.getTaxCond().getTaxId());
		    sup.setField(fieldExists);
		    sup.setTaxCond(taxExists);
		    supplierRepo.save(sup);

		    return "Supplier updated succesfully";
		}
	}
	
	public String deleteSupplier(int id){
		Optional<SupplierModel> supplierExists = supplierRepo.findById(id);
		if(!supplierExists.isPresent()) {
	        throw new EntityNotFoundException("Couldn't find a Supplier with the id " + id);
	    }else {
	    	SupplierModel sup=supplierExists.get();
	    	sup.setIsActive(false);
	    	supplierRepo.save(sup);
	    	return "Supplier deleted succesfully";
	    }
	}
}
