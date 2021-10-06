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
public class UniteEnseignement implements Serializable{

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
	private int nbrCredit;
	private String version;
	@OneToMany(mappedBy = "uniteEnseignement")
	private List<EeUeRel>  eeUeRels;
	
	@OneToMany(mappedBy = "uniteEnseignement")
	private List<Objectif> objectifs;
	
	@OneToMany(mappedBy = "uniteEnseignement")
	private List<UePlanRel> uePlanRels;
	
	@OneToMany(mappedBy = "uniteEnseignement")
	private List<UnitePedagogique> unitePedagogiques;

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

	public int getNbrCredit() {
		return nbrCredit;
	}

	public void setNbrCredit(int nbrCredit) {
		this.nbrCredit = nbrCredit;
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

	public List<Objectif> getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(List<Objectif> objectifs) {
		this.objectifs = objectifs;
	}

	public List<UePlanRel> getUePlanRels() {
		return uePlanRels;
	}

	public void setUePlanRels(List<UePlanRel> uePlanRels) {
		this.uePlanRels = uePlanRels;
	}

	public List<UnitePedagogique> getUnitePedagogiques() {
		return unitePedagogiques;
	}

	public void setUnitePedagogiques(List<UnitePedagogique> unitePedagogiques) {
		this.unitePedagogiques = unitePedagogiques;
	}

	

	
	
	

}
