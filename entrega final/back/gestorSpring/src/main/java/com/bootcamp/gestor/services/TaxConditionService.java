package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.bootcamp.gestor.models.SupplierModel;
import com.bootcamp.gestor.models.TaxConditionModel;
import com.bootcamp.gestor.repositories.SupplierRepository;
import com.bootcamp.gestor.repositories.TaxConditionRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TaxConditionService {

	@Autowired
	TaxConditionRepository taxCondRepo;
	
	@Autowired 
	SupplierRepository supplierRepo;
	
	//PREGUNTAS:
	//-------es lo mismo hacer repo.save(objeto) que llamar al metodo que hicimos para hacerlo desde el controler? osea this.createObjeto()
	//serviria para que las validaciones de un metodo tmbien sirvan para otro
	
	//-------consultar si es necesario el metodo delete y update en las entidades que no se va a poder hacer desde el front
	
	
	public List<TaxConditionModel> getAllTaxConditions(){
	    return taxCondRepo.findAll();
	}

	
	
//	public TareaModel getTareabyId(int id) {
//		// findById retorna un Optional, si viene vacio arroja excepcion, sino devuelve
//		// el recurso
//		return tareaRepo.findById(id).orElseThrow(() -> notFound(id));
//	}
	
	public TaxConditionModel getTaxConditionById(int id) {
		return taxCondRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Couldn´t find a Tax Condition with the id " + id));
	}

	
//	public TareaModel newTarea(TareaModel tarea) {
//		if (tarea.getNombre().isEmpty() || tarea.getDescripcion().isEmpty()) {
//			throw new IllegalArgumentException("El nombre y la descripción de la tarea no pueden estar vacíos");
//		}
//		try {
//			return tareaRepo.save(tarea);
//		} catch (Exception e) {
//			throw new RuntimeException("Error al guardar la nueva tarea", e);
//		}
//	}
	
	public List<TaxConditionModel> createTaxCondition(TaxConditionModel taxCond){
		if(taxCond.getTaxCondTitle().isEmpty()) throw new IllegalArgumentException("Name of tax condition is empty");
		for(TaxConditionModel t:taxCondRepo.findAll()) {
			if(t.getTaxCondTitle().toLowerCase().equals(taxCond.getTaxCondTitle().toLowerCase()))
				throw new EntityExistsException("The value already exists");
		}
		try {			
			taxCondRepo.save(taxCond);
			return taxCondRepo.findAll();
		}
		catch(Exception e) {
			throw new RuntimeException("Error creating tax condition", e);
		}
	}
	
	
	
//	// eliminar tarea
//		public String deleteTarea(int id) {
//			Optional<TareaModel> taskExists = tareaRepo.findById(id);
//			if (taskExists.isPresent()) {
//				TareaModel t = taskExists.get();
//				t.setEliminado(true);
//				tareaRepo.save(t);
//				return "Tarea " + t.getId() + " eliminada correctamente";
//			} else {
//				throw notFound(id);
//			}
//	    }

	public String deleteTaxCondition(int id){
		Optional<TaxConditionModel> taxExists = taxCondRepo.findById(id);//------------------consultar si es necesario el metod en las entidades que no se va a poder hacer desde el front
		if (taxExists.isPresent()) {
			TaxConditionModel t = taxExists.get();
			//for(SupplierModel s:supplierRepo.findAll()) {
//		estaria muy bueno aca primero cambiar el tax id en todos los suppliers que tengan este tax
				
			//}
			taxCondRepo.deleteById(id);;
			return "Tax condition " + t.getTaxId() + " deleted succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find a Tax Condition with the id " + id);
		}
		
	}
	
	
	

//	// modificar tarea
//		public String updateTarea(int id, TareaModel tarea) {
//			Optional<TareaModel> taskExists = tareaRepo.findById(id);
//			if(tarea.getNombre().isEmpty() || tarea.getDescripcion().isEmpty()) {
//	            throw new IllegalArgumentException("El nombre y la descripción de la tarea no pueden estar vacíos");
//	        }
//			if (taskExists.isPresent()) {
//				TareaModel t = taskExists.get();
//				t.setNombre(tarea.getNombre());
//				t.setDescripcion(tarea.getDescripcion());
//				t.setFinalizado(tarea.isFinalizado());
//				tareaRepo.save(t);
//				return "Tarea " + t.getId() + " modificada correctamente";
//			} else {
//				throw notFound(id);
//			}
//		}
	

	public String updateTaxCondition(int id, TaxConditionModel taxCond){
		Optional<TaxConditionModel> taxExists = taxCondRepo.findById(id);//------------------consultar si esto es necesario
		
		if (taxExists.isPresent()) {
			
			if(taxCond.getTaxCondTitle().isEmpty()) throw new IllegalArgumentException("Name of tax condition is empty");
			for(TaxConditionModel t:taxCondRepo.findAll()) {
				if(t.getTaxCondTitle().toLowerCase().equals(taxCond.getTaxCondTitle().toLowerCase()))
					throw new EntityExistsException("The value already exists");
			}

			TaxConditionModel t = taxExists.get();
			t.setTaxCondTitle(taxCond.getTaxCondTitle());
			taxCondRepo.save(t);
			return "Tax condition " + t.getTaxId() + " updated succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find a Tax Condition with the id " + id);
		}
	
	}
}
