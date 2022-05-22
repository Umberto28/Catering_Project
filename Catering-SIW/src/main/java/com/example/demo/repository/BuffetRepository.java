package com.example.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long>{

	public Buffet findByNome(String nome);
	
	//funzioni con query particolari
	@Modifying
    @Query(value = "update Buffet b set b.nome = :nome where b.id == :id", nativeQuery = true)
	@Transactional
    public void modificaNomeApp(@Param("nome") String nome, @Param("id") Long id);


    @Modifying
    @Query(value = "update Buffet b set b.descr = :descr where b.id == :id", nativeQuery = true)
    @Transactional
    public void modificaDescrApp(@Param("descr") String descr, @Param("id") Long id);
}
