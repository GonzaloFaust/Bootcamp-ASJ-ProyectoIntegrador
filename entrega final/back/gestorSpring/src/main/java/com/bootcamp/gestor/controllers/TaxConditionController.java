package com.bootcamp.gestor.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.gestor.models.TaxConditionModel;
import com.bootcamp.gestor.services.TaxConditionService;

@RestController
@RequestMapping("/tax-condition")
@CrossOrigin(origins="http://localhost:4200")
public class TaxConditionController {

	@Autowired 
	TaxConditionService taxCondService;
	
	@GetMapping()
	public ResponseEntity<List<TaxConditionModel>> getTaxCondition()
	{
		return ResponseEntity.ok(taxCondService.getAllTaxConditions());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<TaxConditionModel>> getTaxConditionById(@PathVariable int id ){
		return ResponseEntity.ok(taxCondService.getTaxConditionById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<TaxConditionModel>> createTaxCondition( @RequestBody TaxConditionModel taxCond){
		return ResponseEntity.ok(taxCondService.createTaxCondition(taxCond));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<TaxConditionModel>> updateTaxCondition(@PathVariable int id, @RequestBody TaxConditionModel taxCond){
		return ResponseEntity.ok(taxCondService.updateTaxCondition(id, taxCond));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<TaxConditionModel>> deleteTaxCondition(@PathVariable int id) {
        return ResponseEntity.ok(taxCondService.deleteTaxCondition(id));
    }
}
