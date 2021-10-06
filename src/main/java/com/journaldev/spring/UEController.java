package com.journaldev.spring;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.EeUeRel;
import com.journaldev.spring.model.ElementEnseignement;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.EnseignantEeUeRel;
import com.journaldev.spring.model.Etudiant;
import com.journaldev.spring.model.PersonType;
import com.journaldev.spring.model.PlanEtude;
import com.journaldev.spring.model.SpecialHoliday;
import com.journaldev.spring.model.UniteEnseignement;
import com.journaldev.spring.service.EEeditor;
import com.journaldev.spring.service.EnseignantEditor;
import com.journaldev.spring.service.MyService;
import com.journaldev.spring.service.PlanEtudeEditor;
import com.journaldev.spring.service.UEeditor;

@Controller
public class UEController {
	
	
	@Autowired
	private UniteEnseignement uniteEnseignement;
	
	@Autowired
	private EeUeRel eeUeRel;
	
	private MyService myService;
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    binder.registerCustomEditor(ElementEnseignement.class, new EEeditor());
	    binder.registerCustomEditor(Enseignant.class, new EnseignantEditor());
	    binder.registerCustomEditor(PlanEtude.class, new PlanEtudeEditor());
	}
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@RequestMapping(value = "/unites", method = RequestMethod.GET)
	public String listElements(Model model,HttpSession session) {
		model.addAttribute("unite", new UniteEnseignement());
		model.addAttribute("unites", this.myService.listEntities(UniteEnseignement.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "unite";
	}
	
	//For add and update person both
	@RequestMapping(value= "/unite/add", method = RequestMethod.POST)
	public String addHoliday(@ModelAttribute("unite") UniteEnseignement p){
		
		if(p.getId() == 0){
			//new person, add it
			this.myService.addEntity(p);
		}else{
			//existing person, call update
			this.myService.updateEntity(p);
		}
		
		return "redirect:/unites";
		
	}
	
	@RequestMapping("/removeUnite/{id}")
    public String removeElement(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, UniteEnseignement.class);
        return "redirect:/unites";
    }
 
    @RequestMapping("/editUnite/{id}")
    public String editElement(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("unite", this.myService.getEntityById(id,UniteEnseignement.class));
        model.addAttribute("unites", this.myService.listEntities(UniteEnseignement.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "unite";
    }
    
    @RequestMapping("/viewEeUe/{id}")
    public String addEeUeRel(@PathVariable("id") int id, Model model,HttpSession session){
    	uniteEnseignement=(UniteEnseignement) this.myService.getEntityById(id,UniteEnseignement.class);
        model.addAttribute("eeue",new EeUeRel());
        model.addAttribute("eeues", this.myService.listEntitiesByAttribute(EeUeRel.class, "uniteEnseignement.id", id));
        model.addAttribute("listElements",this.myService.listEntities(ElementEnseignement.class));
       // model.addAttribute("listEnseignants",this.myService.listEntities(Enseignant.class));
        model.addAttribute("listPlans",this.myService.listEntities(PlanEtude.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "eeue";
    }
    
	@RequestMapping(value= "/eeue/add", method = RequestMethod.POST)
	public String addEeUe(@ModelAttribute("eeue") EeUeRel p){
		if(p.getId() == 0){
			p.setUniteEnseignement(uniteEnseignement);
			setElementEnseignement(p);
			setPlanEtude(p);
			this.myService.addEntity(p);
		}else{
			//p.setUniteEnseignement(uniteEnseignement);
			setPlanEtude(p);
			setElementEnseignement(p);
			this.myService.updateEntity(p);
		}
		return "redirect:/viewEeUe/"+String.valueOf(uniteEnseignement.getId());
	}
	
	@RequestMapping("/removeEeue/{id}")
    public String removeEeue(@PathVariable("id") int id){
		//must be in same transaction
        this.myService.removeEntity(id, EeUeRel.class);
       // this.myService.deleteEntitiesByAttribute(EnseignantEeUeRel.class, "eeUeRel.id", id);
        return "redirect:/viewEeUe/"+String.valueOf(uniteEnseignement.getId());
    }
 
    @RequestMapping("/editEeue/{id}")
    public String editEeue(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("eeue", this.myService.getEntityById(id,EeUeRel.class));
        model.addAttribute("eeues", this.myService.listEntitiesByAttribute(EeUeRel.class, "uniteEnseignement.id", uniteEnseignement.getId()));
        model.addAttribute("listElements",this.myService.listEntities(ElementEnseignement.class));
       // model.addAttribute("listEnseignants",this.myService.listEntities(Enseignant.class));
        model.addAttribute("listPlans",this.myService.listEntities(PlanEtude.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "eeue";
    }

    //******EnseignantEeUeRel
    @Transactional
    @RequestMapping("/viewEnseignantEeUe/{id}")
    public String viewEnseignantEeUeRel(@PathVariable("id") int id, Model model,HttpSession session){
    	eeUeRel=(EeUeRel) this.myService.getEntityById(id,EeUeRel.class);
    	model.addAttribute("enseignantEeUe",new EnseignantEeUeRel());
        model.addAttribute("enseignantEeUes", this.myService.listEntitiesByAttribute(EnseignantEeUeRel.class, "eeUeRel.id", id));
        model.addAttribute("listEnseignants",this.myService.listEntitiesByAttributes(Enseignant.class,new HashMap<String,Object>(){{put("personType",PersonType.Enseignant.toString());}}));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "enseignantEeUeRel";
    }
    
	@RequestMapping("/removeEnseignantEeUe/{id}")
    public String removeEnseignantEeUe(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, EnseignantEeUeRel.class);
        return "redirect:/viewEnseignantEeUe/"+String.valueOf(eeUeRel.getId());
    }
	
	@RequestMapping(value= "/enseignantEeUe/add", method = RequestMethod.POST)
	public String addEnseignantEeUe(@ModelAttribute("enseignantEeUe") EnseignantEeUeRel p){
		if(p.getId() == 0){
			p.setEeUeRel(eeUeRel);
			setEnseignant( p);
			this.myService.addEntity(p);
		}else{
			//p.setUniteEnseignement(uniteEnseignement);
			setEnseignant( p);
			this.myService.updateEntity(p);
		}
		return "redirect:/viewEnseignantEeUe/"+String.valueOf(eeUeRel.getId());
	}
	
	@Transactional
    @RequestMapping("/editEnseignantEeUe/{id}")
    public String editEnseignantEeUe(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("enseignantEeUe", this.myService.getEntityById(id,EnseignantEeUeRel.class));
        model.addAttribute("enseignantEeUes", this.myService.listEntitiesByAttribute(EeUeRel.class, "eeUeRel.id", eeUeRel.getId()));
        model.addAttribute("listEnseignants",new HashMap<String,Object>(){{put("personType",PersonType.Enseignant.toString());}});
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
       // model.addAttribute("listEnseignants",this.myService.listEntities(Enseignant.class));
        return "enseignanEeUeRel";
    }
    
    //**********
    public void setElementEnseignement(EeUeRel p)
    {
    	List liste=null;
		if(p.getElementEnseignement()!=null)
		   liste=myService.listEntitiesByAttribute(ElementEnseignement.class, "code", p.getElementEnseignement().getCode());
    	if(liste!=null && !liste.isEmpty())
    	{
    		Object country = liste.get(0);
    		p.setElementEnseignement((ElementEnseignement)country);
    	}
    }
    
    public void setPlanEtude(EeUeRel p)
    {
    	List liste=null;
		if(p.getPlanEtude()!=null)
		   liste=myService.listEntitiesByAttribute(PlanEtude.class, "code", p.getPlanEtude().getCode());
    	if(liste!=null && !liste.isEmpty())
    	{
    		PlanEtude planEtude = (PlanEtude)liste.get(0);
    		if(p.getPlanEtude().getId()!=planEtude.getId())
    		   p.setPlanEtude(planEtude);
    	}
    }
    
    public void setEnseignant(EnseignantEeUeRel p)
    {
    	List liste=null;
		if(p.getEnseignant()!=null)
		   liste=myService.listEntitiesByAttribute(Enseignant.class, "code", p.getEnseignant().getCode());
    	if(liste!=null && !liste.isEmpty())
    	{
    		Enseignant enseignant = (Enseignant)liste.get(0);
    		if(p.getEnseignant().getId()!=enseignant.getId())
    		   p.setEnseignant(enseignant);
    	}
    }

}
