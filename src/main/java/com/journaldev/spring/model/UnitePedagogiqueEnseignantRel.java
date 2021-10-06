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

@Entity
public class UnitePedagogiqueEnseignantRel {
	

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="enseignant_id")
	private Enseignant enseignant;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="up_id")
	private UnitePedagogique unitePedagogique;
	
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UnitePedagogique getUnitePedagogique() {
		return unitePedagogique;
	}
	public void setUnitePedagogique(UnitePedagogique unitePedagogique) {
		this.unitePedagogique = unitePedagogique;
	}

}
