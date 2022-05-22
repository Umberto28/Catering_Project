package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Chef;
import com.example.demo.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	private ChefRepository chefRepository;
	
	public boolean alreadyExists(Chef chef) {
		if(this.chefRepository.findByNomeAndCognome(chef.getNome(), chef.getCognome()) != null) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public Chef inserisci(Chef chef) {
		return this.chefRepository.save(chef);
	}
	
	@Transactional
	public void rimuovi(Chef chef) {
		this.chefRepository.delete(chef);
	}
	
	@Transactional
	public void clear(Chef chef) {
		this.chefRepository.deleteAll();
	}
}
