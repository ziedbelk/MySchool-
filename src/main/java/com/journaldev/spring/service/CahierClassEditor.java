package com.journaldev.spring.service;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.journaldev.spring.model.Classe;

public class CahierClassEditor extends PropertyEditorSupport {

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
	        return ((Classe) getValue()).getNomClasse();
	    };

	    public void setAsText(String text) throws IllegalArgumentException {
	        if (text != null) {
	        	Classe cc=(Classe) myService.listEntitiesByAttribute(Classe.class, "nomClasse", text);
	        	cc.setNomClasse(text);
	 	            setValue(cc);
	        	}
	        }
	    
}
