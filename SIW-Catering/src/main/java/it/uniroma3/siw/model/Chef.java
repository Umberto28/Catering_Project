package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Chef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String nome;
	private String cognome;
	private String nazionalita;
	
	@OneToMany(mappedBy="chefDelBuffet")
	private List<Buffet> buffetDelloChef;
	
	
	public List<Buffet> getBuffetDelloChef() {
		return buffetDelloChef;
	}
	
	public void setBuffetDelloChef(List<Buffet> buffetDelloChef) {
		this.buffetDelloChef = buffetDelloChef;
	}
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		Id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getNazionalita() {
		return nazionalita;
	}
	
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
}
