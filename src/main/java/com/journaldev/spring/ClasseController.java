package com.journaldev.spring;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.AnneeUniversitaire;
import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.ElementEnseignement;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.PlanEtude;
import com.journaldev.spring.model.SpecialHoliday;
import com.journaldev.spring.model.UniteEnseignement;
import com.journaldev.spring.service.AnneeUnEditor;
import com.journaldev.spring.service.MyService;
import com.journaldev.spring.service.PlanEtudeEditor;
import com.journaldev.spring.service.UEeditor;

@Controller
public class ClasseController {

private MyService myService;
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    binder.registerCustomEditor(UniteEnseignement.class, new UEeditor());
	    binder.registerCustomEditor(AnneeUniversitaire.class, new AnneeUnEditor());
	    binder.registerCustomEditor(PlanEtude.class, new PlanEtudeEditor());
	}
	
	@RequestMapping(value = "/classes", method = RequestMethod.GET)
	public String listElements(Model model,HttpSession session) {
		model.addAttribute("classe", new Classe());
		model.addAttribute("classes", this.myService.listEntities(Classe.class));
		model.addAttribute("listePlans", this.myService.listEntities(PlanEtude.class));
		model.addAttribute("annees",this.myService.listEntities(AnneeUniversitaire.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "classe";
	}
	
	//For add and update person both
	@RequestMapping(value= "/classe/add", method = RequestMethod.POST)
	public String addHoliday(@ModelAttribute("classe") Classe p){
		
		if(p.getId() == 0){
			//new person, add it
			setAnnee(p);
			setPlan(p);
			this.myService.addEntity(p);
		}else{
			//existing person, call update
			setAnnee(p);
			setPlan(p);
			this.myService.updateEntity(p);
		}
		
		return "redirect:/classes";
		
	}
	
	@RequestMapping("/removeClasse/{id}")
    public String removeElement(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, Classe.class);
        return "redirect:/classes";
    }
 
    @RequestMapping("/editClasse/{id}")
    public String editElement(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("classe", this.myService.getEntityById(id,Classe.class));
        model.addAttribute("classes", this.myService.listEntities(Classe.class));
        model.addAttribute("listePlans", this.myService.listEntities(PlanEtude.class));
		model.addAttribute("annees",this.myService.listEntities(AnneeUniversitaire.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "classe";
    }
    
    public void setAnnee(Classe p)
    {
    	List liste=null;
		if(p.getAnneeUniversitaire()!=null)
		   liste=myService.listEntitiesByAttribute(AnneeUniversitaire.class, "code", p.getAnneeUniversitaire().getCode());
    	if(liste!=null && !liste.isEmpty())
    	{
    		Object country = liste.get(0);
    		p.setAnneeUniversitaire((AnneeUniversitaire)country);
    	}
    }
    public void setPlan(Classe p)
    {
    	List liste=null;
		if(p.getPlanEtude()!=null)
		   liste=myService.listEntitiesByAttribute(PlanEtude.class, "code", p.getPlanEtude().getCode());
    	if(liste!=null && !liste.isEmpty())
    	{
    		Object country = liste.get(0);
    		p.setPlanEtude((PlanEtude)country);
    	}
    }
}
