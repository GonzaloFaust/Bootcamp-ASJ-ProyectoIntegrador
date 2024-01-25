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

import com.bootcamp.gestor.models.StateModel;
import com.bootcamp.gestor.services.StateService;

@RestController
@RequestMapping("/state")
@CrossOrigin(origins="http://localhost:4200")
public class StateController {
	@Autowired
	StateService stateService;
	
	@GetMapping()
	public ResponseEntity<List<StateModel>> getStates()
	{
		return ResponseEntity.ok(stateService.getStates());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StateModel>> getStateById(@PathVariable int id ){
		return ResponseEntity.ok(stateService.getStateById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<StateModel>> createState( @RequestBody StateModel state){
		return ResponseEntity.ok(stateService.createState(state));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<StateModel>> updateState(@PathVariable int id, @RequestBody StateModel state){
		return ResponseEntity.ok(stateService.updateState(id, state));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<StateModel>> deleteState(@PathVariable int id) {
        return ResponseEntity.ok(stateService.deleteState(id));
    }
}
