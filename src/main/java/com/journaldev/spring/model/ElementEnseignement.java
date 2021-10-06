package com.journaldev.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ElementEnseignement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String code;
	private int nbrHcours;
	private int nbrHtp;
	private String version;
	@OneToMany(mappedBy = "elementEnseignement")
	private List<EeUeRel>  eeUeRels;
	@OneToMany(mappedBy = "elementEnseignement")
	private List<Absence> absences;
	
	@OneToMany(mappedBy = "elementEnseignement")
	private List<Chapitre> chapitres;
	@OneToMany(mappedBy = "elementEnseignement")
	private List<Seance> seances;
	@OneToMany(mappedBy = "elementEnseignement")
	private List<Objectif> objectifs;
	
	@OneToMany(mappedBy = "elementEnseignement")
	private List<ObjectifClasseRel> objectifClasseRels;
	
	public List<Objectif> getObjectifs() {
		return objectifs;
	}
	public void setObjectifs(List<Objectif> objectifs) {
		this.objectifs = objectifs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getNbrHcours() {
		return nbrHcours;
	}
	public void setNbrHcours(int nbrHcours) {
		this.nbrHcours = nbrHcours;
	}
	public int getNbrHtp() {
		return nbrHtp;
	}
	public void setNbrHtp(int nbrHtp) {
		this.nbrHtp = nbrHtp;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<EeUeRel> getEeUeRels() {
		return eeUeRels;
	}
	public void setEeUeRels(List<EeUeRel> eeUeRels) {
		this.eeUeRels = eeUeRels;
	}
	public List<Absence> getAbsences() {
		return absences;
	}
	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}
	public List<Chapitre> getChapitres() {
		return chapitres;
	}
	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}
	
	
}
