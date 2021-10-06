package com.journaldev.spring.service;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.journaldev.spring.model.AnneeUniversitaire;

public class AnneeUnEditor extends PropertyEditorSupport {

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
	        return ((AnneeUniversitaire) getValue()).getCode();
	    };

	    public void setAsText(String text) throws IllegalArgumentException {
	        if (text != null) {
	        	AnneeUniversitaire cc=(AnneeUniversitaire) myService.listEntitiesByAttribute(AnneeUniversitaire.class, "code", text);
	        	cc.setCode(text);
	 	            setValue(cc);
	        	}
	        }
	    
}