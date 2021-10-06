package com.journaldev.spring.model;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Seance {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date date ;
	private String remarque;
	private boolean confirmation;
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date DateConfirmation;
	
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Classe classe;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private ElementEnseignement elementEnseignement;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Enseignant enseignant;
	
	

	@OneToMany(mappedBy = "seance")
	private List<Absence> absences;

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public ElementEnseignement getElementEnseignement() {
		return elementEnseignement;
	}

	public void setElementEnseignement(ElementEnseignement elementEnseignement) {
		this.elementEnseignement = elementEnseignement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public boolean getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
	
	public Date getDateConfirmation() {
		return DateConfirmation;
	}

	public void setDateConfirmation(Date dateConfirmation) {
		DateConfirmation = dateConfirmation;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
	
	
}
