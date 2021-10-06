package com.journaldev.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Objectif implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="chapitre_id")
	private Chapitre chapitre;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ue_id")
	private UniteEnseignement uniteEnseignement;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ee_id")
	private ElementEnseignement elementEnseignement;
	
	@OneToMany(mappedBy = "objectif")
	private List<ObjectifClasseRel> objectifClasseRels;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String objectif) {
		this.description = objectif;
	}

	public Chapitre getChapitre() {
		return chapitre;
	}

	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}

	public UniteEnseignement getUniteEnseignement() {
		return uniteEnseignement;
	}

	public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
		this.uniteEnseignement = uniteEnseignement;
	}

	public ElementEnseignement getElementEnseignement() {
		return elementEnseignement;
	}

	public void setElementEnseignement(ElementEnseignement elementEnseignement) {
		this.elementEnseignement = elementEnseignement;
	}

	
	

}
