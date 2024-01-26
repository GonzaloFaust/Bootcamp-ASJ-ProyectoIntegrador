package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.StateModel;
import com.bootcamp.gestor.repositories.StateRepository;

@Service
public class StateService {

	@Autowired 
	StateRepository stateRepo;
	
	public List<StateModel> getStates(){
	    return null;
	}

	public Optional<StateModel> getStateById(int id) {
		return null;
	}

	public List<StateModel> createState(StateModel state){
		return null;
	}

	public List<StateModel> deleteState(int id){
		return null;
	}

	public List<StateModel> updateState(int id, StateModel state){
		return null;
	}
	
}
