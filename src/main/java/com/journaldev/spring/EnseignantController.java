package com.journaldev.spring;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.Etudiant;
import com.journaldev.spring.model.ObjectifClasseRel;
import com.journaldev.spring.model.PersonType;
import com.journaldev.spring.model.Seance;
import com.journaldev.spring.service.CahierClassEditor;
import com.journaldev.spring.service.EnseignantEditor;
import com.journaldev.spring.service.MyService;


@Controller
public class EnseignantController {
	
	
    private MyService myService;

    @Autowired
    private Seance seance;
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    binder.registerCustomEditor(Classe.class, new CahierClassEditor());
	}
	
	@Transactional
	@RequestMapping(value = "/enseignants", method = RequestMethod.GET)
	public String listEnseignants(Model model,HttpSession session) {
		model.addAttribute("enseignant", new Enseignant());
		model.addAttribute("listEnseignants", this.myService.listEntities(Enseignant.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "enseignant";
	}
	
	//For add and update person both
	@RequestMapping(value= "/enseignant/add", method = RequestMethod.POST)
	public String addEtudiant( @ModelAttribute("enseignant") Enseignant p, BindingResult bindingResult){
		 if(p.getId() == 0){
			this.myService.addEntity(p);
		}else{
			//existing person, call update
			this.myService.updateEntity(p);
		}
		return "redirect:/enseignants";
		
	}
	
	@RequestMapping("/removeEnseignant/{id}")
    public String removeEtudiant(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, Enseignant.class);
        return "redirect:/enseignants";
    }
 
	@Transactional
    @RequestMapping("/editEnseignant/{id}")
    public String editEtudiant(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("enseignant", this.myService.getEntityById(id,Enseignant.class));
        model.addAttribute("listEnseignants", this.myService.listEntities(Enseignant.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "enseignant";
    }
	
	

}
