package com.journaldev.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.AnneeUniversitaire;
import com.journaldev.spring.model.ElementEnseignement;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.PlanEtude;
import com.journaldev.spring.service.EEeditor;
import com.journaldev.spring.service.MyService;

@Controller
public class AnneeUniversitaireController {

private MyService myService;
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(AnneeUniversitaire.class, new EEeditor());
	    //binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@RequestMapping(value = "/annees", method = RequestMethod.GET)
	public String listElements(Model model,HttpSession session) {
		model.addAttribute("annee", new AnneeUniversitaire());
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		model.addAttribute("annees", this.myService.listEntities(AnneeUniversitaire.class));
		return "annee";
	}
	
	//For add and update person both
	@RequestMapping(value= "/annee/add", method = RequestMethod.POST)
	public String addHoliday(@ModelAttribute("annee") AnneeUniversitaire p){
		
		if(p.getId() == 0){
			//new person, add it
			this.myService.addEntity(p);
		}else{
			//existing person, call update
			this.myService.updateEntity(p);
		}
		
		return "redirect:/annees";
		
	}
	
	@RequestMapping("/removeAnnee/{id}")
    public String removeElement(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, AnneeUniversitaire.class);
        return "redirect:/annees";
    }
 
    @RequestMapping("/editAnnee/{id}")
    public String editElement(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("annee", this.myService.getEntityById(id,AnneeUniversitaire.class));
        model.addAttribute("annees", this.myService.listEntities(AnneeUniversitaire.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "annee";
    }
}
