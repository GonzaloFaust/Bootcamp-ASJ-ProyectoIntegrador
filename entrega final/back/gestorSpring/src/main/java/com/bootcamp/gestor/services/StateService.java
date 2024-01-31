package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.CountryModel;
import com.bootcamp.gestor.models.StateModel;
import com.bootcamp.gestor.repositories.CountryRepository;
import com.bootcamp.gestor.repositories.StateRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StateService {

	@Autowired
	StateRepository stateRepo;

	@Autowired
	CountryRepository countryRepo;

	public List<StateModel> getAllStates() {
		return stateRepo.findAll();
	}

	public StateModel getStateById(int id) {
		return stateRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Couldn´t find a State with the id " + id));
	}

	public List<StateModel> createState(StateModel state) {
//		try {
		if (state.getStateName().isEmpty())
			throw new IllegalArgumentException("Name of state is empty");
		Optional<CountryModel> c = countryRepo.findById(state.getCountry().getCounId());
		if (c.isPresent()) {
			CountryModel country = c.get();
			for (StateModel s : stateRepo.findAll()) {
				if (s.getStateName().toLowerCase().equals(state.getStateName().toLowerCase())
						&& s.getCountry().getCounId() == country.getCounId())
					throw new EntityExistsException("The value already exists");
			}
			state.setCountry(country);
		} else {
			throw new EntityNotFoundException();
		}

			stateRepo.save(state);
			return stateRepo.findAll();

//		} catch (
//
//		Exception e) {
//			throw new RuntimeException("Error creating state", e);
//		}
	}

	public String deleteState(int id) {
		Optional<StateModel> stateExists = stateRepo.findById(id);
		if (stateExists.isPresent()) {
			StateModel s = stateExists.get();
			stateRepo.deleteById(id);
			return "State " + s.getStateId() + " deleted succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find a State with the id " + id);
		}

	}

	public String updateState(int id, StateModel state) {
		Optional<StateModel> stateExists = stateRepo.findById(id);

		if (stateExists.isPresent()) {

			if (state.getStateName().isEmpty())
				throw new IllegalArgumentException("Name of state is empty");
			for (StateModel s : stateRepo.findAll()) {
				if (s.getStateName().toLowerCase().equals(state.getStateName().toLowerCase()))
					throw new EntityExistsException("The value already exists");
			}

			StateModel s = stateExists.get();
			s.setStateName(state.getStateName());
			stateRepo.save(s);
			return "State " + s.getStateId() + " updated succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find a State with the id " + id);
		}

	}
	
	public List<StateModel> getStatesByCountry(int id){
		System.out.println("entra al service");
		return stateRepo.getStatesByCountry(id);
	}

//	public List<StateModel> getStates(){
//	    return null;
//	}
//
//	public Optional<StateModel> getStateById(int id) {
//		return null;
//	}
//
//	public List<StateModel> createState(StateModel state){
//		return null;
//	}
//
//	public List<StateModel> deleteState(int id){
//		return null;
//	}
//
//	public List<StateModel> updateState(int id, StateModel state){
//		return null;
//	}

}
