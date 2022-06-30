package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Piatto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@OneToMany(mappedBy = "piatto", cascade = {CascadeType.ALL})
	private List<Ingrediente> ingredientiDelPiatto;
	
	@ManyToOne
	private Buffet buffet;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescr() {
		return descrizione;
	}
	
	public void setDescr(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Ingrediente> getIngredientiDelPiatto() {
		return ingredientiDelPiatto;
	}

	public void setIngredientiDelPiatto(List<Ingrediente> ingredientiDelPiatto) {
		this.ingredientiDelPiatto = ingredientiDelPiatto;
	}

	public Buffet getBuffet() {
		return buffet;
	}

	public void setBuffet(Buffet buffet) {
		this.buffet = buffet;
	}
}
