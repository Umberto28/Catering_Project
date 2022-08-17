package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

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
			if(this.chefRepository.findByNomeAndCognome(chef.getNome(), chef.getCognome()).getId() != chef.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Chef> findAll(){
		List<Chef> elencoChef = new ArrayList<Chef>();
		for (Chef c : this.chefRepository.findAll()) {
			elencoChef.add(c);
		}
		return elencoChef;
	}
	
	@Transactional
	public Chef findById(Long id) {
		return this.chefRepository.findById(id).get();
	}
	
	@Transactional
	public Chef inserisci(Chef chef) {
		return this.chefRepository.save(chef);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.chefRepository.deleteById(id);
	}
	
	@Transactional
	public void clear(Chef chef) {
		this.chefRepository.deleteAll();
	}
	
	public long contaChef() {
		return this.chefRepository.count();
	}
}
