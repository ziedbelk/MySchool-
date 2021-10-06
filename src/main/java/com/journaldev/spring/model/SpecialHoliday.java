package com.journaldev.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class SpecialHoliday {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	@DateTimeFormat(pattern = "mm/dd/yyyy") 
	private Date datBegin;
	@DateTimeFormat(pattern = "mm/dd/yyyy") 
	private Date datEnd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDatBegin() {
		return datBegin;
	}
	public void setDatBegin(Date datBegin) {
		this.datBegin = datBegin;
	}
	public Date getDatEnd() {
		return datEnd;
	}
	public void setDatEnd(Date datEnd) {
		this.datEnd = datEnd;
	}

	
}
