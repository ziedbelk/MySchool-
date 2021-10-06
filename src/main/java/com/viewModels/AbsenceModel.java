package com.viewModels;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.journaldev.spring.model.AnneeUniversitaire;
import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.ElementEnseignement;

public class AbsenceModel {
	
	private ElementEnseignement elementEnseignement;
	@DateTimeFormat(pattern = "mm/dd/yyyy") 
	private Date date;
	private Classe classe;
	private AnneeUniversitaire  anneeUniversitaire;
	public ElementEnseignement getElementEnseignement() {
		return elementEnseignement;
	}
	public void setElementEnseignement(ElementEnseignement elementEnseignement) {
		this.elementEnseignement = elementEnseignement;
	}
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
	public AnneeUniversitaire getAnneeUniversitaire() {
		return anneeUniversitaire;
	}
	public void setAnneeUniversitaire(AnneeUniversitaire anneeUniversitaire) {
		this.anneeUniversitaire = anneeUniversitaire;
	}
	
	

}
