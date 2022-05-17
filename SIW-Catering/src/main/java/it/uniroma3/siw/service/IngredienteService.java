package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.repository.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public boolean alreadyExists(Ingrediente ingrediente) {
		if(this.ingredienteRepository.findByNome(ingrediente.getNome()) != null) {
			return true;
		}
		return false;
	}
}
