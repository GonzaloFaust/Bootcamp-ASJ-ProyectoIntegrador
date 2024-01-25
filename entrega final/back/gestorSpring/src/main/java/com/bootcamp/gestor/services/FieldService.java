package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.FieldModel;
import com.bootcamp.gestor.repositories.FieldRepository;

@Service
public class FieldService {

	@Autowired 
	FieldRepository fieldRepo;
	
	public List<FieldModel> getFields(){
	    return null;
	}

	public Optional<FieldModel> getFieldById(int id) {
		return null;
	}

	public List<FieldModel> createField(FieldModel field){
		return null;
	}

	public List<FieldModel> deleteField(int id){
		return null;
	}

	public List<FieldModel> updateField(int id, FieldModel field){
		return null;
	}
}
