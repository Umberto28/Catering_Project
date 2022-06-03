package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Piatto;
import com.example.demo.repository.PiattoRepository;

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
	
	public List<Piatto> findAll(){
		List<Piatto> elencoPiatti = new ArrayList<Piatto>();
		for (Piatto p : this.piattoRepository.findAll()) {
			elencoPiatti.add(p);
		}
		return elencoPiatti;
	}
		
	@Transactional
	public Piatto findById(Long id) {
		return this.piattoRepository.findById(id).get();
	}
	
	@Transactional
	public Piatto inserisci(Piatto piatto) {
		return this.piattoRepository.save(piatto);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.piattoRepository.deleteById(id);
	}
	
	@Transactional
	public void clear(Piatto piatto) {
		this.piattoRepository.deleteAll();
	}
}
