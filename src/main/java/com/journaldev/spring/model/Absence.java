package com.journaldev.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Absence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="classe_id")
	private Classe classe;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="seance_id")
	private Seance seance;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="etudiant_id")
	private Etudiant etudiant;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date date;
	
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ee_id")
	private ElementEnseignement elementEnseignement;

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe cahierClasse) {
		this.classe = cahierClasse;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ElementEnseignement getElementEnseignement() {
		return elementEnseignement;
	}

	public void setElementEnseignement(ElementEnseignement elementEnseignement) {
		this.elementEnseignement = elementEnseignement;
	}
	
	
	
}
