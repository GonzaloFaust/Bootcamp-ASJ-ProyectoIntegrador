package com.bootcamp.gestor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.gestor.ErrorHandler;
import com.bootcamp.gestor.models.TaxConditionModel;
import com.bootcamp.gestor.services.TaxConditionService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tax-condition")
@CrossOrigin(origins = "http://localhost:4200")
public class TaxConditionController {

	@Autowired
	TaxConditionService taxCondService;

	@GetMapping()
	public ResponseEntity<List<TaxConditionModel>> getTaxCondition() {
		return ResponseEntity.ok(taxCondService.getAllTaxConditions());
	}
	
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Object> getCoso(@PathVariable String id) {
//		if (id == null || id =="")return new ResponseEntity<>("la concha de tu madre sos gracioso", HttpStatus.NOT_FOUND);
//		else return getTaxConditionById(0);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getTaxConditionById(@PathVariable int id ) {
		//if(Integer.parseInt(id) == null) return new ResponseEntity<String>("Invalid ID number provided", HttpStatus.BAD_REQUEST);
		try {
			return ResponseEntity.ok(taxCondService.getTaxConditionById(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<Object> createTaxCondition(@Valid @RequestBody TaxConditionModel taxCond, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(taxCondService.createTaxCondition(taxCond));
			}
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (EntityExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTaxCondition(@PathVariable int id, @Valid @RequestBody TaxConditionModel taxCond, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()){
				String errors = new ErrorHandler().inputValidate(bindingResult);
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			} else {
				return ResponseEntity.ok(taxCondService.updateTaxCondition(id, taxCond));
			}
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (EntityExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTaxCondition(@PathVariable int id) {
		try {
			return ResponseEntity.ok(taxCondService.deleteTaxCondition(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
