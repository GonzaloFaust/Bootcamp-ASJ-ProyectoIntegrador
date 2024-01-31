package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.FieldModel;
import com.bootcamp.gestor.repositories.FieldRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FieldService {

	@Autowired
	FieldRepository fieldRepo;

	public List<FieldModel> getFields() {
		return fieldRepo.findAll();
	}

	public FieldModel getFieldById(int id) {
		return fieldRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Couldn´t find a field with the id " + id));
	}

	public List<FieldModel> createField(FieldModel field) {
		if (field.getFieldName().isEmpty() || field.getFieldDetail().isEmpty())
			throw new IllegalArgumentException("There's missing data");
		for (FieldModel f : fieldRepo.findAll()) {
			if (f.getFieldName().toLowerCase().equals(field.getFieldName().toLowerCase()))
				throw new EntityExistsException("The field already exists");
		}
		fieldRepo.save(field);
		return fieldRepo.findAll();
	}

	public String updateField(int id, FieldModel field) {
		Optional<FieldModel> fieldExists = fieldRepo.findById(id);
		if (fieldExists.isPresent()) {

			if (field.getFieldName().isEmpty() || field.getFieldDetail().isEmpty())
				throw new IllegalArgumentException("There's missing data");
			for (FieldModel f : fieldRepo.findAll()) {
				if (f.getFieldName().toLowerCase().equals(field.getFieldName().toLowerCase()))
					throw new EntityExistsException("The field already exists");
			}
			FieldModel f = fieldExists.get();
			f.setFieldName(field.getFieldName());
			f.setFieldDetail(field.getFieldDetail());
			fieldRepo.save(field);
			return "field " + f.getFieldId() + " updated succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find a field with the id " + id);
		}
	}

	public String deleteField(int id) {
		Optional <FieldModel> fieldExists = fieldRepo.findById(id);
		if (fieldExists.isPresent()) {
			FieldModel f = fieldExists.get();
			//for(SupplierModel s:supplierRepo.findAll()) {
//			estaria muy bueno aca primero cambiar el tax id en todos los suppliers que tengan este tax
					
				//}
			fieldRepo.deleteById(id);
			return "field " + f.getFieldId() + " deleted succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find a Tax Condition with the id " + id);
		}
	}

}
