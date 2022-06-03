package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Buffet;
import com.example.demo.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;
	
	public boolean alreadyExists(Buffet buffet) {
		if(this.buffetRepository.findByNome(buffet.getNome()) != null) {
			return true;
		}
		return false;
	}
	
	public List<Buffet> findAll(){
		List<Buffet> elencoBuffet = new ArrayList<Buffet>();
		for (Buffet b : this.buffetRepository.findAll()) {
			elencoBuffet.add(b);
		}
		return elencoBuffet;
	}
		
	@Transactional
	public Buffet findById(Long id) {
		return this.buffetRepository.findById(id).get();
	}
	
	@Transactional
	public Buffet inserisci(Buffet buffet) {
		return this.buffetRepository.save(buffet);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.buffetRepository.deleteById(id);
	}
	
	@Transactional
	public void clear(Buffet buffet) {
		this.buffetRepository.deleteAll();
	}
	
	//metodi che richiamano la query nel repository
	/* @Transactional
    public void modificaNome(Buffet b) {
        //prima modifichi nome con setNome()
        this.buffetRepository.modificaNomeApp(b.getNome(), b.getId());
    }

    @Transactional
    public void modificaDescr(Buffet b) {
        //prima modifichi nome con setDescr()
        this.buffetRepository.modificaDescrApp(b.getDescr(), b.getId());
    } */
}
