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
public class UnitePedagogique {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String code;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="up_id")
	private UniteEnseignement uniteEnseignement;
	
	@OneToMany(mappedBy = "unitePedagogique")
	private List<UnitePedagogiqueEnseignantRel> unitePedagogiqueEnseignantRels;
	
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
	public UniteEnseignement getUniteEnseignement() {
		return uniteEnseignement;
	}
	public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
		this.uniteEnseignement = uniteEnseignement;
	}
	public List<UnitePedagogiqueEnseignantRel> getUnitePedagogiqueEnseignantRels() {
		return unitePedagogiqueEnseignantRels;
	}
	public void setUnitePedagogiqueEnseignantRels(List<UnitePedagogiqueEnseignantRel> unitePedagogiqueEnseignantRels) {
		this.unitePedagogiqueEnseignantRels = unitePedagogiqueEnseignantRels;
	}
	
	

}
