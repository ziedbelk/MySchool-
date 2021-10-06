package com.journaldev.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "enseignant")
public class Enseignant {
	

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String code;
	
	private String name;
	
	private String country;
	
	private String login;
	
	private String password;
	
	private String email;
	
	private String type;
	
	private PersonType personType;
	
	@OneToMany(mappedBy = "enseignant")
	private List<ClasseEnseRela> classeEnsRelas;
	
	@OneToMany(mappedBy = "enseignant")
	private List<Seance> seances;
	
	@OneToMany(mappedBy = "enseignant")
	private List<EnseignantEeUeRel> enseignantEeUeRels;
	
	@OneToMany(mappedBy = "enseignant")
	private List<UnitePedagogiqueEnseignantRel> unitePedagogiqueEnseignatRels;
	

	public List<EnseignantEeUeRel> getEnseignantEeUeRels() {
		return enseignantEeUeRels;
	}

	public void setEnseignantEeUeRels(List<EnseignantEeUeRel> enseignantEeUeRels) {
		this.enseignantEeUeRels = enseignantEeUeRels;
	}

	public List<ClasseEnseRela> getClasseEnsRelas() {
		return classeEnsRelas;
	}

	public void setClasseEnsRelas(List<ClasseEnseRela> classeEnsRelas) {
		this.classeEnsRelas = classeEnsRelas;
	}

	public List<Seance> getSeances() {
		return seances;
	}

	public void setSeances(List<Seance> seances) {
		this.seances = seances;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public List<UnitePedagogiqueEnseignantRel> getUnitePedagogiqueEnseignatRels() {
		return unitePedagogiqueEnseignatRels;
	}

	public void setUnitePedagogiqueEnseignatRels(List<UnitePedagogiqueEnseignantRel> unitePedagogiqueEnseignatRels) {
		this.unitePedagogiqueEnseignatRels = unitePedagogiqueEnseignatRels;
	}
	
	
	
}
