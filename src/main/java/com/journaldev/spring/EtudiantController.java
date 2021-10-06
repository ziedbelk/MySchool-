package com.journaldev.spring;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
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
import com.journaldev.spring.service.CahierClassEditor;
import com.journaldev.spring.service.MyService;

@Controller
public class EtudiantController {

private MyService myService;
	
	
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    binder.registerCustomEditor(Classe.class, new CahierClassEditor());
	}
	
	@RequestMapping(value = "/etudiants", method = RequestMethod.GET)
	public String listEtudiants(Model model,HttpSession session) {
		model.addAttribute("etudiant", new Etudiant());
		model.addAttribute("listEtudiants", this.myService.listEntities(Etudiant.class));
		model.addAttribute("listecahiers",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "etudiant";
	}
	
	//For add and update person both
	@RequestMapping(value= "/etudiant/add", method = RequestMethod.POST)
	public String addEtudiant( @ModelAttribute("etudiant") Etudiant p, BindingResult bindingResult){
		 if(p.getId() == 0){
			setClasse( p);
			this.myService.addEntity(p);
		}else{
			//existing person, call update
			setClasse( p);
			this.myService.updateEntity(p);
		}
		return "redirect:/etudiants";
		
	}
	
	@RequestMapping("/removeEtudiant/{id}")
    public String removeEtudiant(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, Etudiant.class);
        return "redirect:/etudiants";
    }
 
    @RequestMapping("/editEtudiant/{id}")
    public String editEtudiant(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("etudiant", this.myService.getEntityById(id,Etudiant.class));
        model.addAttribute("listEtudiants", this.myService.listEntities(Etudiant.class));
        model.addAttribute("listecahiers",this.myService.listEntities(Classe.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "etudiant";
    }
    
    public void setClasse(Etudiant p)
    {
    	List liste=null;
		if(p.getClasse()!=null)
		   liste=myService.listEntitiesByAttribute(Classe.class, "nomClasse", p.getClasse().getNomClasse());
    	if(liste!=null && !liste.isEmpty())
    	{
    		Object country = liste.get(0);
    		p.setClasse((Classe)country);
    	}
    }
}
