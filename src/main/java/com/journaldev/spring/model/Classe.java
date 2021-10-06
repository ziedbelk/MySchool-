package com.journaldev.spring.model;

import java.io.Serializable;
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
public class Classe implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "classe")
	private List<Etudiant> etudiants;
	
    
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="au_id")
	private AnneeUniversitaire anneeUniversitaire;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pe_id")
	private PlanEtude planEtude;
	
	@OneToMany(mappedBy = "classe")
	private List<ClasseEnseRela> classeEnsRelas;
	
	@OneToMany(mappedBy = "classe")
	private List<ObjectifClasseRel> objectifClasseRels;
	
	private String nomClasse;
	@OneToMany(mappedBy = "classe")
	private List<Emploie> emploies;
	@OneToMany(mappedBy = "classe")
	private List<Absence> absences;
	@OneToMany(mappedBy = "classe")
	private List<Retard> retards;
	@OneToMany(mappedBy = "classe")
	private List<Seance> seances;
	
	private int niveau;
	
	
	public List<ObjectifClasseRel> getObjectifClasseRels() {
		return objectifClasseRels;
	}
	public void setObjectifClasseRels(List<ObjectifClasseRel> objectifClasseRels) {
		this.objectifClasseRels = objectifClasseRels;
	}
	public List<Seance> getSeances() {
		return seances;
	}
	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}
	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	public List<ClasseEnseRela> getClasseEnsRelas() {
		return classeEnsRelas;
	}
	public void setClasseEnsRelas(List<ClasseEnseRela> cahierClasseEnsRelas) {
		this.classeEnsRelas = cahierClasseEnsRelas;
	}

	public List<Emploie> getEmploies() {
		return emploies;
	}
	public void setEmploies(List<Emploie> emploies) {
		this.emploies = emploies;
	}
	public List<Absence> getAbsences() {
		return absences;
	}
	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
	public List<Retard> getRetards() {
		return retards;
	}
	public void setRetards(List<Retard> retards) {
		this.retards = retards;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public AnneeUniversitaire getAnneeUniversitaire() {
		return anneeUniversitaire;
	}
	public void setAnneeUniversitaire(AnneeUniversitaire anneeUniversitaire) {
		this.anneeUniversitaire = anneeUniversitaire;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public PlanEtude getPlanEtude() {
		return planEtude;
	}
	public void setPlanEtude(PlanEtude planEtude) {
		this.planEtude = planEtude;
	}
	
	

}
