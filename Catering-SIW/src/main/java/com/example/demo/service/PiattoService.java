package com.example.demo.service;

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
	
	@Transactional
	public Piatto inserisci(Piatto piatto) {
		return this.piattoRepository.save(piatto);
	}
	
	@Transactional
	public void rimuovi(Piatto piatto) {
		this.piattoRepository.delete(piatto);
	}
	
	@Transactional
	public void clear(Piatto piatto) {
		this.piattoRepository.deleteAll();
	}
}
