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

import com.bootcamp.gestor.models.FieldModel;
import com.bootcamp.gestor.services.FieldService;

@RestController
@RequestMapping("/field")
@CrossOrigin(origins="http://localhost:4200")
public class FieldController {

	@Autowired
	FieldService fieldService;
	@GetMapping()
	public ResponseEntity<List<FieldModel>> getFields()
	{
		return ResponseEntity.ok(fieldService.getFields());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<FieldModel>> getFieldById(@PathVariable int id ){
		return ResponseEntity.ok(fieldService.getFieldById(id));
	}
	
	@PostMapping()
	public ResponseEntity<List<FieldModel>> createField( @RequestBody FieldModel field){
		return ResponseEntity.ok(fieldService.createField(field));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<List<FieldModel>> updateField(@PathVariable int id, @RequestBody FieldModel field){
		return ResponseEntity.ok(fieldService.updateField(id, field));
	}
	
	
    @DeleteMapping("/{id}")
    public ResponseEntity<List<FieldModel>> deleteField(@PathVariable int id) {
        return ResponseEntity.ok(fieldService.deleteField(id));
    }
}
