package com.journaldev.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AnneeUniversitaire {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String code;
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date dateDeb;
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date dateFin;
	@OneToMany(mappedBy = "anneeUniversitaire")
	private List<PlanEtude> planEtudes;
	@OneToMany(mappedBy = "anneeUniversitaire")
	private List<Classe> classes;
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
	public Date getDateDeb() {
		return dateDeb;
	}
	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public List<PlanEtude> getPlanEtudes() {
		return planEtudes;
	}
	public void setPlanEtudes(List<PlanEtude> planEtudes) {
		this.planEtudes = planEtudes;
	}
	
	
	
}
