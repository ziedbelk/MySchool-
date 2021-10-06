package com.journaldev.spring.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "enseignantEeUeRel")
public class EnseignantEeUeRel {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Enseignant enseignant;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private EeUeRel eeUeRel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public EeUeRel getEeUeRel() {
		return eeUeRel;
	}

	public void setEeUeRel(EeUeRel eeUeRel) {
		this.eeUeRel = eeUeRel;
	}
	
	

}
