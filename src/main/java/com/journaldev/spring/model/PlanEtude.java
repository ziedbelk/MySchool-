package com.journaldev.spring.model;

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
public class PlanEtude {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "planEtude")
	@Column(nullable = true)
	private List<UePlanRel> uePlanRels;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="au_id")
	private AnneeUniversitaire anneeUniversitaire;
	
	@OneToMany(mappedBy = "planEtude")
	private List<Classe> classes;
	
	@OneToMany(mappedBy = "planEtude")
	private List<EeUeRel> eeUeRels;
	
	
	public List<EeUeRel> getEeUeRels() {
		return eeUeRels;
	}


	public void setEeUeRels(List<EeUeRel> eeUeRels) {
		this.eeUeRels = eeUeRels;
	}


	private String code;

	public int getId() {
		return id;
	}


	public List<UePlanRel> getUePlanRels() {
		return uePlanRels;
	}


	public void setUePlanRels(List<UePlanRel> uePlanRels) {
		this.uePlanRels = uePlanRels;
	}


	public void setId(int id) {
		this.id = id;
	}


	public AnneeUniversitaire getAnneeUniversitaire() {
		return anneeUniversitaire;
	}

	public void setAnneeUniversitaire(AnneeUniversitaire anneeUniversitaire) {
		this.anneeUniversitaire = anneeUniversitaire;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public List<Classe> getClasses() {
		return classes;
	}


	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}



	
	

}
