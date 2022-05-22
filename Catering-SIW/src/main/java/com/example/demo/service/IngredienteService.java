package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Ingrediente;
import com.example.demo.repository.IngredienteRepository;

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
	
	@Transactional
	public Ingrediente inserisci(Ingrediente ingrediente) {
		return this.ingredienteRepository.save(ingrediente);
	}
	
	@Transactional
	public void rimuovi(Ingrediente ingrediente) {
		this.ingredienteRepository.delete(ingrediente);
	}
	
	@Transactional
	public void clear(Ingrediente ingrediente) {
		this.ingredienteRepository.deleteAll();
	}
}
