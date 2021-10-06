package com.journaldev.spring;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
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
import com.journaldev.spring.model.EeUeRel;
import com.journaldev.spring.model.ElementEnseignement;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.Etudiant;
import com.journaldev.spring.model.PlanEtude;
import com.journaldev.spring.model.UePlanRel;
import com.journaldev.spring.model.UniteEnseignement;
import com.journaldev.spring.service.AnneeUnEditor;
import com.journaldev.spring.service.EEeditor;
import com.journaldev.spring.service.MyService;

@Controller
public class PlanEtudeController {

	@Autowired
	private PlanEtude planEtude;
	
	
	private MyService myService;
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    binder.registerCustomEditor(ElementEnseignement.class, new EEeditor());
	    binder.registerCustomEditor(AnneeUniversitaire.class, new AnneeUnEditor());
	}
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@Transactional
	@RequestMapping(value = "/plans", method = RequestMethod.GET)
	public String listElements(Model model,HttpSession session) {
		model.addAttribute("plan", new PlanEtude());
		List<PlanEtude> listPl=(List<PlanEtude>)(Object)this.myService.listEntities(PlanEtude.class);
		preparePlans(listPl);
		model.addAttribute("plans", listPl);
		model.addAttribute("annees",this.myService.listEntities(AnneeUniversitaire.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "plan";
	}
	
	//For add and update person both
	@RequestMapping(value= "/plan/add", method = RequestMethod.POST)
	public String addHoliday(@ModelAttribute("plan") PlanEtude p){
		
		if(p.getId() == 0){
			//new person, add it
			setAnnee(p);
			this.myService.addEntity(p);
		}else{
			//existing person, call update
			setAnnee(p);
			this.myService.updateEntity(p);
		}
		
		return "redirect:/plans";
		
	}
	
	@RequestMapping("/removePlan/{id}")
    public String removeElement(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, PlanEtude.class);
        return "redirect:/plans";
    }
 
    @RequestMapping("/editPlan/{id}")
    public String editElement(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("plan", this.myService.getEntityById(id,PlanEtude.class));
        List<PlanEtude> listPl=(List<PlanEtude>)(Object)this.myService.listEntities(PlanEtude.class);
		preparePlans(listPl);
		model.addAttribute("plans", listPl);
		model.addAttribute("annees",this.myService.listEntities(AnneeUniversitaire.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "plan";
    }
    
    public void setAnnee(PlanEtude p)
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
    
    @RequestMapping("/viewUePl/{id}")
    public String addEeUeRel(@PathVariable("id") int id, Model model,HttpSession session){
    	planEtude=(PlanEtude) this.myService.getEntityById(id,PlanEtude.class);
        model.addAttribute("uepl",new UePlanRel());
        model.addAttribute("uepls", this.myService.listEntitiesByAttribute(UePlanRel.class, "planEtude.id", id));
        model.addAttribute("listUnites",this.myService.listEntities(UniteEnseignement.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "uepl";
    }
    
	@RequestMapping(value= "/uepl/add", method = RequestMethod.POST)
	public String addEeUe(@ModelAttribute("uepl") UePlanRel p){
		if(p.getId() == 0){
			p.setPlanEtude(planEtude);
			setUniteEnseignement( p);
			this.myService.addEntity(p);
		}else{
			//p.setUniteEnseignement(uniteEnseignement);
			setUniteEnseignement( p);
			this.myService.updateEntity(p);
		}
		return "redirect:/viewUePl/"+String.valueOf(planEtude.getId());
	}
	
	@RequestMapping("/removeUePl/{id}")
    public String removeEeue(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, UePlanRel.class);
        return "redirect:/viewUePl/"+String.valueOf(planEtude.getId());
    }
 
    @RequestMapping("/editUePl/{id}")
    public String editEeue(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("uepl", this.myService.getEntityById(id,UePlanRel.class));
        model.addAttribute("uepls", this.myService.listEntitiesByAttribute(UePlanRel.class, "planEtude.id", planEtude.getId()));
        model.addAttribute("listUnites",this.myService.listEntities(UniteEnseignement.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "uepl";
    }
    
    public void setUniteEnseignement(UePlanRel p)
    {
    	List liste=null;
		if(p.getUniteEnseignement()!=null)
		   liste=myService.listEntitiesByAttribute(UniteEnseignement.class, "code", p.getUniteEnseignement().getCode());
    	if(liste!=null && !liste.isEmpty())
    	{
    		Object country = liste.get(0);
    		p.setUniteEnseignement((UniteEnseignement)country);
    	}
    }
    
    public void preparePlans(List<PlanEtude> plans)
    {
    	for(PlanEtude plan:plans)
    	{
    		Hibernate.initialize(plan.getUePlanRels());
    		for(UePlanRel uePlanRel:plan.getUePlanRels())
    		{
    			Hibernate.initialize(uePlanRel.getUniteEnseignement().getEeUeRels());
    			for(EeUeRel eeUeRel:uePlanRel.getUniteEnseignement().getEeUeRels())
        		{
        			Hibernate.initialize(eeUeRel.getEnseignantEeUeRels());
        		}
    		}
    	}
    }

}
