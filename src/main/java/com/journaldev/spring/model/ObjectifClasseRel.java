package com.journaldev.spring.model;

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
public class ObjectifClasseRel {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="classe_id")
	private Classe classe ;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="objectif_id")
	private Objectif objectif;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ee_id")
	private ElementEnseignement elementEnseignement;
	
	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean confirmation=false;
	@DateTimeFormat(pattern = "mm/dd/yyyy") 
	private Date dateConfirmation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public Objectif getObjectif() {
		return objectif;
	}
	public void setObjectif(Objectif objectif) {
		this.objectif = objectif;
	}
	public Boolean getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(Boolean confirmation) {
		this.confirmation = confirmation;
	}
	public Date getDateConfirmation() {
		return dateConfirmation;
	}
	public void setDateConfirmation(Date dateConfirmation) {
		this.dateConfirmation = dateConfirmation;
	}
	public ElementEnseignement getElementEnseignement() {
		return elementEnseignement;
	}
	public void setElementEnseignement(ElementEnseignement elementEnseignement) {
		this.elementEnseignement = elementEnseignement;
	}
	
	

}
