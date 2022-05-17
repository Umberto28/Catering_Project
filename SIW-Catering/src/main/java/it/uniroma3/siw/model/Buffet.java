package it.uniroma3.siw.model; 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Buffet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String nome;
	private String descr;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name = "piattiDelBuffet")
	private List<Piatto> listaPiatti;
	
	@ManyToOne
	private Chef chefDelBuffet;
	
	public List<Piatto> getListaPiatti() {
		return listaPiatti;
	}
	
	public void setListaPiatti(List<Piatto> listaPiatti) {
		this.listaPiatti = listaPiatti;
	}
	
	public Chef getChefDelBuffet() {
		return chefDelBuffet;
	}
	
	public void setChefDelBuffet(Chef chefDelBuffet) {
		this.chefDelBuffet = chefDelBuffet;
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
	
	public String getDescr() {
		return descr;
	}
	
	public void setDescr(String descr) {
		this.descr = descr;
	}
}
