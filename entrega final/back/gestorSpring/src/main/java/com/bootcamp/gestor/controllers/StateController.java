package com.bootcamp.gestor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.gestor.models.StateModel;
import com.bootcamp.gestor.services.StateService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/state")
@CrossOrigin(origins="http://localhost:4200")
public class StateController {
	@Autowired
	StateService stateService;
	
	@GetMapping()
	public ResponseEntity<List<StateModel>> getStates() {
		return ResponseEntity.ok(stateService.getAllStates());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getStateById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(stateService.getStateById(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<Object> createState(@RequestBody StateModel state) {
		try {
			return ResponseEntity.ok(stateService.createState(state));
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
	public ResponseEntity<Object> updateState(@PathVariable int id, @RequestBody StateModel state) {
		try {
			return ResponseEntity.ok(stateService.updateState(id, state));
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
	public ResponseEntity<String> deleteState(@PathVariable int id) {
		try {
			return ResponseEntity.ok(stateService.deleteState(id));
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/by")
	public List<StateModel> getStatesByCountry(@RequestParam("country") int id){
		System.out.println(id);
		return stateService.getStatesByCountry(id);
	}
//	@GetMapping()
//	public ResponseEntity<List<StateModel>> getStates()
//	{
//		return ResponseEntity.ok(stateService.getStates());
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Optional<StateModel>> getStateById(@PathVariable int id ){
//		return ResponseEntity.ok(stateService.getStateById(id));
//	}
//	
//	@PostMapping()
//	public ResponseEntity<List<StateModel>> createState( @RequestBody StateModel state){
//		return ResponseEntity.ok(stateService.createState(state));
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<List<StateModel>> updateState(@PathVariable int id, @RequestBody StateModel state){
//		return ResponseEntity.ok(stateService.updateState(id, state));
//	}
//	
//	
//    @DeleteMapping("/{id}")
//    public ResponseEntity<List<StateModel>> deleteState(@PathVariable int id) {
//        return ResponseEntity.ok(stateService.deleteState(id));
//    }
}
