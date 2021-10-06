package com.journaldev.spring.service;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.journaldev.spring.model.AnneeUniversitaire;
import com.journaldev.spring.model.PlanEtude;

public class PlanEtudeEditor  extends PropertyEditorSupport {

	private MyService myService;
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	 @Override
	    public void setValue(Object value) {
	        super.setValue(value);
	    }

	    public String getAsText() {
	        if (getValue() == null) return null;
	        return ((PlanEtude) getValue()).getCode();
	    };

	    public void setAsText(String text) throws IllegalArgumentException {
	        if (text != null) {
	        	PlanEtude cc=(PlanEtude) myService.listEntitiesByAttribute(PlanEtude.class, "code", text);
	        	cc.setCode(text);
	 	            setValue(cc);
	        	}
	        }
}
