package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	private ChefRepository chefRepository;
	
	public boolean alreadyExists(Chef chef) {
		if(this.chefRepository.findByNomeCognome(chef.getNome(), chef.getCognome()) != null) {
			return true;
		}
		return false;
	}
}
