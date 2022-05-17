package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;
	
	public boolean alreadyExists(Piatto piatto) {
		if(this.piattoRepository.findByNome(piatto.getNome()) != null) {
			return true;
		}
		return false;
	}
}
