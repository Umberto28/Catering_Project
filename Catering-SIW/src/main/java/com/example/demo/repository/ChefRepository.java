package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long>{

	public Chef findByNomeAndCognome(String nome, String cognome);

}
