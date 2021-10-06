package com.journaldev.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ClasseEnseRela implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="enseignant_id")
	private Enseignant enseignant;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="classe_id")
	private Classe classe;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public Classe getCahierClasse() {
		return classe;
	}
	public void setCahierClasse(Classe cahierClasse) {
		this.classe = cahierClasse;
	}
	

}
