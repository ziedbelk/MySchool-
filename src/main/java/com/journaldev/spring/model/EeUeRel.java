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
public class EeUeRel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ee_id")
	private ElementEnseignement elementEnseignement;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ue_id")
	private UniteEnseignement uniteEnseignement;
	private double coef;
	

	@OneToMany(mappedBy = "eeUeRel")
	private List<EnseignantEeUeRel> enseignantEeUeRels;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pe_id")
	private PlanEtude planEtude;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ElementEnseignement getElementEnseignement() {
		return elementEnseignement;
	}
	public void setElementEnseignement(ElementEnseignement elementEnseignement) {
		this.elementEnseignement = elementEnseignement;
	}
	public UniteEnseignement getUniteEnseignement() {
		return uniteEnseignement;
	}
	public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
		this.uniteEnseignement = uniteEnseignement;
	}
	public double getCoef() {
		return coef;
	}
	public void setCoef(double coef) {
		this.coef = coef;
	}
	
	public PlanEtude getPlanEtude() {
		return planEtude;
	}
	public void setPlanEtude(PlanEtude planEtude) {
		this.planEtude = planEtude;
	}
	public List<EnseignantEeUeRel> getEnseignantEeUeRels() {
		return enseignantEeUeRels;
	}
	public void setEnseignantEeUeRels(List<EnseignantEeUeRel> enseignantEeUeRels) {
		this.enseignantEeUeRels = enseignantEeUeRels;
	}
	
	

}
