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
public class UePlanRel {
	

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pe_id")
	private PlanEtude planEtude;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ue_id")
	private UniteEnseignement uniteEnseignement;
	
	@DateTimeFormat(pattern = "mm/dd/yyyy") 
	private Date dateDeb;
	@DateTimeFormat(pattern = "mm/dd/yyyy") 
	private Date dateFin;
	
	private int semestre;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PlanEtude getPlanEtude() {
		return planEtude;
	}

	public void setPlanEtude(PlanEtude planEtude) {
		this.planEtude = planEtude;
	}

	public UniteEnseignement getUniteEnseignement() {
		return uniteEnseignement;
	}

	public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
		this.uniteEnseignement = uniteEnseignement;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDebut) {
		this.dateDeb = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	

}
