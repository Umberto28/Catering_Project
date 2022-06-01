package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long>{

	public Buffet findByNome(String nome);
	
	//funzioni con query particolari
	/* @Modifying
    @Query(value = "update Buffet b set b.nome = :nome where b.id == :id", nativeQuery = true)
	@Transactional
    public void modificaNomeApp(@Param("nome") String nome, @Param("id") Long id);


    @Modifying
    @Query(value = "update Buffet b set b.descrizione = :descrizione where b.id == :id", nativeQuery = true)
    @Transactional
    public void modificaDescrApp(@Param("descrizione") String descrizione, @Param("id") Long id); */
}
