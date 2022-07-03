package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

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
			if(this.ingredienteRepository.findByNome(ingrediente.getNome()).getId() != ingrediente.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Ingrediente> findAll(){
		List<Ingrediente> elencoIngredienti = new ArrayList<Ingrediente>();
		for (Ingrediente i : this.ingredienteRepository.findAll()) {
			elencoIngredienti.add(i);
		}
		return elencoIngredienti;
	}
		
	@Transactional
	public Ingrediente findById(Long id) {
		return this.ingredienteRepository.findById(id).get();
	}
	
	@Transactional
	public Ingrediente inserisci(Ingrediente ingrediente) {
		return this.ingredienteRepository.save(ingrediente);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.ingredienteRepository.deleteById(id);
	}
	
	@Transactional
	public void clear(Ingrediente ingrediente) {
		this.ingredienteRepository.deleteAll();
	}
}
