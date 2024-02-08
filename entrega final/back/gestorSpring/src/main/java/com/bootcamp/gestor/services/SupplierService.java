package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.bootcamp.gestor.models.AddressModel;
import com.bootcamp.gestor.models.FieldModel;
import com.bootcamp.gestor.models.SupplierContactModel;
//import com.bootcamp.gestor.models.SupplierContactModel;
import com.bootcamp.gestor.models.SupplierModel;
import com.bootcamp.gestor.models.TaxConditionModel;
import com.bootcamp.gestor.repositories.AddressRepository;
import com.bootcamp.gestor.repositories.FieldRepository;
import com.bootcamp.gestor.repositories.SupplierContactRepository;
import com.bootcamp.gestor.repositories.SupplierRepository;
import com.bootcamp.gestor.repositories.TaxConditionRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SupplierService {

	@Autowired
	SupplierRepository supplierRepo;

	@Autowired
	FieldRepository fieldRepo;

	@Autowired
	TaxConditionRepository taxCondRepo;

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	SupplierContactRepository supplierContactRepo;

	public List<SupplierModel> getSuppliers() {
		return supplierRepo.findAll();
	}

	public List<SupplierModel> getSuppliersSearch(String searchTerm) {

		if (searchTerm == null) {
			searchTerm = "";
		}
		return supplierRepo.findByTitleContaining(searchTerm);
	}

	public SupplierModel getSupplierById(int id) {
		return supplierRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Couldn´t find a supplier with the id " + id));
	}

	public SupplierModel createSupplier(SupplierModel supplier) {
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

		if (supplier.getSupCode().isEmpty() || supplier.getSupBussinessName().isEmpty()
				|| supplier.getSupImage().isEmpty() || supplier.getSupWebsite().isEmpty()
				|| supplier.getSupEmail().isEmpty() || supplier.getSupTelephone().isEmpty()
				|| supplier.getSupCuit().isEmpty()) {
			throw new IllegalArgumentException("There's missing data");
		}

		Optional<FieldModel> fieldExists = fieldRepo.findById(supplier.getField().getFieldId());
		Optional<TaxConditionModel> taxExists = taxCondRepo.findById(supplier.getTaxCond().getTaxId());
		if (fieldExists.isPresent() && taxExists.isPresent()) {

			supplier.setField(fieldExists.get());
			supplier.setTaxCond(taxExists.get());
			supplierRepo.save(supplier);

			Optional<SupplierModel> supplierExists = supplierRepo.findById(supplier.getSupId());
			if (!supplierExists.isPresent()) {
				throw new EntityNotFoundException("Couldn't find a Supplier with the id " + supplier.getSupId());
			}

			return supplierExists.get();

		} else {
			throw new EntityNotFoundException("Couldn´t find a field or tax condition for this supplier");
		}
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

			Optional<SupplierContactModel> cont= supplierContactRepo.findById(supplier.getSupContact().getSupContactId());
			SupplierModel sup=supplierExists.get();
Optional<FieldModel> fieldExists = fieldRepo.findById(supplier.getField().getFieldId());
Optional<TaxConditionModel> taxExists = taxCondRepo.findById(supplier.getTaxCond().getTaxId());
		    sup.setField(fieldExists.get());
		    sup.setTaxCond(taxExists.get());
		    sup.setSupEmail(supplier.getSupEmail());
		    sup.setSupWebsite(supplier.getSupWebsite());
		    sup.setSupImage(supplier.getSupImage());
		    sup.setSupTelephone(supplier.getSupTelephone());
		    sup.setSupContact(cont.get());
		    sup.setAddress(supplier.getAddress());
		    supplierRepo.save(sup);

		    return "Supplier updated succesfully";
		}
	}

	public SupplierModel makeAvailable(int id) {
		Optional<SupplierModel> supplierExists = supplierRepo.findById(id);
		if (supplierExists.isPresent()) {
			SupplierModel prod = supplierExists.get();
			if (prod.getIsActive() == true)
				throw new EntityExistsException("The supplier is active already");
			prod.setIsActive(true);
			supplierRepo.save(prod);
			return prod;
		} else {
			throw new EntityNotFoundException("Couldn´t find a supplier with the id " + id);
		}
	}

	public String deleteSupplier(int id) {
		Optional<SupplierModel> supplierExists = supplierRepo.findById(id);
		if (!supplierExists.isPresent()) {
			throw new EntityNotFoundException("Couldn't find a Supplier with the id " + id);
		} else {
			SupplierModel sup = supplierExists.get();
			sup.setIsActive(false);
			supplierRepo.save(sup);
			return "Supplier deleted succesfully";
		}
	}
}
