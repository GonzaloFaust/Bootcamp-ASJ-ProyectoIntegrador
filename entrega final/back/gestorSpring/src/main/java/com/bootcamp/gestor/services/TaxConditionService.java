package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.TaxConditionModel;
import com.bootcamp.gestor.repositories.TaxConditionRepository;

@Service
public class TaxConditionService {

	@Autowired
	TaxConditionRepository taxCondRepo;
	
	public List<TaxConditionModel> getAllTaxConditions(){
	    return null;
	}

	public Optional<TaxConditionModel> getTaxConditionById(int id) {
		return null;
	}

	public List<TaxConditionModel> createTaxCondition(TaxConditionModel taxCond){
		return null;
	}

	public List<TaxConditionModel> deleteTaxCondition(int id){
		return null;
	}

	public List<TaxConditionModel> updateTaxCondition(int id, TaxConditionModel taxCond){
		return null;
	}
}
