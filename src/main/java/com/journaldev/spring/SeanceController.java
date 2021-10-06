package com.journaldev.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.Absence;
import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.EeUeRel;
import com.journaldev.spring.model.ElementEnseignement;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.Etudiant;
import com.journaldev.spring.model.Objectif;
import com.journaldev.spring.model.ObjectifClasseRel;
import com.journaldev.spring.model.PersonType;
import com.journaldev.spring.model.PlanEtude;
import com.journaldev.spring.model.Seance;
import com.journaldev.spring.model.UniteEnseignement;
import com.journaldev.spring.service.CahierClassEditor;
import com.journaldev.spring.service.EEeditor;
import com.journaldev.spring.service.EnseignantEditor;
import com.journaldev.spring.service.MyService;
import com.journaldev.spring.service.PlanEtudeEditor;
import com.viewModels.AbsenceModel;

@Controller
public class SeanceController {
	
	private MyService myService;
	
	@Autowired
	private Seance seance;
	
	@Autowired
	private AbsenceModel absenceModel1;
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	   // binder.registerCustomEditor(Classe.class, new CahierClassEditor());
	    binder.registerCustomEditor(ElementEnseignement.class, new EEeditor());
	    binder.registerCustomEditor(Enseignant.class, new EnseignantEditor());
	    binder.registerCustomEditor(PlanEtude.class, new PlanEtudeEditor());
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@Transactional
	@RequestMapping(value = "/seances", method = RequestMethod.GET)
	public String listSeances(Model model,HttpSession session) {
		model.addAttribute("seance", new Seance());
		model.addAttribute("listSeances", this.myService.listEntities(Seance.class));
		model.addAttribute("listeElements",this.myService.listEntities(ElementEnseignement.class));
		model.addAttribute("listecahiers",this.myService.listEntities(Classe.class));
		//model.addAttribute("listePlans", this.myService.listEntities(PlanEtude.class));
		model.addAttribute("listeEnseignants",this.myService.listEntitiesByAttributes(Enseignant.class,new HashMap<String,Object>(){{put("personType",PersonType.Enseignant.toString());}}));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "seance";
	}
	
	//For add and update person both
	@Transactional
	@RequestMapping(value= "/seance/add", method = RequestMethod.POST)
	public String addSeance( @ModelAttribute("seance") Seance p, BindingResult bindingResult){
		 if(p.getId() == 0){
			setClasse( p);
			setEnseignant(p);
			ElementEnseignement ee=setElement(p);
			Hibernate.initialize(ee.getObjectifs());
			this.myService.addEntity(p);
			for(Objectif objectif:ee.getObjectifs())
			{
				ObjectifClasseRel ocrl=new ObjectifClasseRel();
				ocrl.setObjectif(objectif);
				ocrl.setClasse(p.getClasse());
				ocrl.setElementEnseignement(p.getElementEnseignement());
				ocrl.setConfirmation(false);
				this.myService.addEntity(ocrl);
			}
		}else{
			//existing person, call update
			setClasse( p);
			setElement(p);
			this.myService.updateEntity(p);
		}
		return "redirect:/seances";
		
	}
	
	@RequestMapping("/removeSeance/{id}")
    public String removeSeance(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, Seance.class);
        return "redirect:/seances";
    }
 
	@Transactional
    @RequestMapping("/editSeance/{id}")
    public String editSeance(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("seance", this.myService.getEntityById(id,Seance.class));
        model.addAttribute("listSeances", this.myService.listEntities(Seance.class));
        model.addAttribute("listeElements",this.myService.listEntities(ElementEnseignement.class));
        model.addAttribute("listecahiers",this.myService.listEntities(Classe.class));
		//model.addAttribute("plans", this.myService.listEntities(PlanEtude.class));
        model.addAttribute("listeEnseignants",this.myService.listEntitiesByAttributes(Enseignant.class,new HashMap<String,Object>(){{put("personType",PersonType.Enseignant.toString());}}));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "seance";
    }
    
  	@Transactional
    @RequestMapping("/viewAbsences/{id}")
    public String addEeUeRel(@PathVariable("id") int id, Model model,HttpSession session){
    	seance=(Seance) this.myService.getEntityById(id,Seance.class);
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		List <Etudiant> listEtudiants=new ArrayList<Etudiant>();
		for(Object etudiant:this.myService.listEntitiesByAttribute(Etudiant.class, "classe.nomClasse", seance.getClasse()!=null ? seance.getClasse().getNomClasse():null))
		{
			Etudiant etud=(Etudiant)etudiant;
			etud.setAbsent(false);
			Hibernate.initialize(etud.getAbsences());
			for(Absence absence:etud.getAbsences())
			{
				if( ((sf.format(absence.getDate()).equals(sf.format(seance.getDate())))))
				{
					etud.setAbsent(true);
					break;
				}
			}
			listEtudiants.add(etud);
		}
		model.addAttribute("listEtudiants", listEtudiants);
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "absenceFromSeance";
    }
    
    @Transactional
    @RequestMapping(value="/markPresentFromSeance/{id}")
    public String markPresent( @PathVariable("id") int id, Model model,HttpSession session){

    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("etudiant.id",id);
		map.put("seance.id",seance.getId());
    	List <Object> listAb=this.myService.listEntitiesByAttributes(Absence.class, map);
    	if(listAb!=null && !listAb.isEmpty())
    		this.myService.removeEntity(((Absence)listAb.get(0)).getId(), Absence.class);
    	List <Etudiant> listEtudiants=new ArrayList<Etudiant>();
		for(Object etudiant:this.myService.listEntitiesByAttribute(Etudiant.class, "classe.nomClasse", seance.getClasse()!=null ? seance.getClasse().getNomClasse():null))
		{
			Etudiant etud=(Etudiant)etudiant;
			etud.setAbsent(false);
			if(etud.getId()!=id)
			{
				 Hibernate.initialize(etud.getAbsences());
					for(Absence absence:etud.getAbsences())
					{
						//if( (sf.format(absence.getSeance().getId()).compareTo(sf.format(seance.getId()))==0) )
						if (absence.getSeance().getId()==seance.getId())
						{
							etud.setAbsent(true);
							break;
						}
					}
			}
			listEtudiants.add(etud);
		}
		model.addAttribute("listEtudiants", listEtudiants);
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "absenceFromSeance";
    }
    
	@Transactional
    @RequestMapping(value="/markAbsentFromSeance/{id}")
    public String markAbsent(@PathVariable("id") int id, Model model,HttpSession session){
    	
		Etudiant etu=(Etudiant) this.myService.getEntityById(id,Etudiant.class);
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("etudiant.id",id);
		map.put("seance.id",seance.getId());
    	List <Object> listAb=this.myService.listEntitiesByAttributes(Absence.class, map);
    	if((listAb==null || listAb.isEmpty()))
    	{
    		Absence abs=new Absence();
        	abs.setDate(seance.getDate());
        	abs.setEtudiant(etu);
        	abs.setSeance(seance);
        	this.myService.addEntity(abs);
    	}
    	
    	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    	List <Etudiant> listEtudiants=new ArrayList<Etudiant>();
		for(Object etudiant:this.myService.listEntitiesByAttribute(Etudiant.class, "classe.nomClasse", seance.getClasse()!=null ? seance.getClasse().getNomClasse():null))
		{
			Etudiant etud=(Etudiant)etudiant;
			etud.setAbsent(false);
			Hibernate.initialize(etud.getAbsences());
			for(Absence absence:etud.getAbsences())
			{
				if( (sf.format(absence.getSeance().getId()).equals(sf.format(seance.getId()))))
				{
					etud.setAbsent(true);
					break;
				}
			}
			listEtudiants.add(etud);
		}
		model.addAttribute("listEtudiants", listEtudiants);
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "absenceFromSeance";
    }
	
	@Transactional
	@RequestMapping(value= "/seanceSearch", method = RequestMethod.POST)
	public String searchAbsences( @ModelAttribute("absenceModel") AbsenceModel p, Model model,BindingResult bindingResult,HttpSession session){
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("classe.id",p.getClasse()!=null ? p.getClasse().getId():null);
//		map.put("date",p.getDate());
		absenceModel1=p;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		List <Seance> listSeances=new ArrayList<Seance>();
		for(Object seance:this.myService.listEntitiesByAttribute(Seance.class, "classe.nomClasse", p.getClasse()!=null ? p.getClasse().getNomClasse():null))
		{ 
			Seance etud=(Seance)seance;
			if(etud.getDate()!=null && sf.format(etud.getDate()).equals(sf.format(absenceModel1.getDate())))
				listSeances.add(etud);
		}
		model.addAttribute("absenceModel", p);
		model.addAttribute("listSeances", listSeances);
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "searchSeance";
	}
	
	@RequestMapping(value = "/searchSeance", method = RequestMethod.GET)
	public String listEtudiants(Model model,HttpSession session) {
		model.addAttribute("absenceModel", new AbsenceModel());
		model.addAttribute("listSeances", new ArrayList<Seance>());
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "searchSeance";
	}
	
	@Transactional
	@RequestMapping("/confirmerSeance/{id}")
    public String confirmerSeance(@PathVariable("id") int id){
		Seance rel=(Seance) this.myService.getEntityById(id,Seance.class);
		rel.setConfirmation(rel.getConfirmation()==false?true:false);
		rel.setDateConfirmation(new Date());
		this.myService.updateEntity(rel);
        return "redirect:/seances";
    }
	
	@Transactional
	@RequestMapping("/confirmerSeancefc/{id}")
    public String confirmerSeancefc(@PathVariable("id") int id){
		Seance rel=(Seance) this.myService.getEntityById(id,Seance.class);
		rel.setConfirmation(rel.getConfirmation()==false?true:false);
		rel.setDateConfirmation(new Date());
		this.myService.updateEntity(rel);
        return "redirect:/searchSeance";
    }
	
	//*************************************** objectifs:
	@Transactional
	@RequestMapping("/viewObjectifClasseRel/{id}")
	public String viewObjectifs(@PathVariable("id") int id, Model model,HttpSession session){
		    seance=(Seance) this.myService.getEntityById(id,Seance.class);
		    Map<String,Object> parameters=new HashMap<String,Object>(2);
		    parameters.put("elementEnseignement.id", seance.getElementEnseignement()!=null?seance.getElementEnseignement().getId():null);
		    parameters.put("classe.id", seance.getClasse()!=null?seance.getClasse().getId():null);
	        model.addAttribute("objectifClasses", this.myService.listEntitiesByAttributes(ObjectifClasseRel.class, parameters));
	        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
	        return "objectifClasse";
    }
	
	@Transactional
	@RequestMapping("/confirmer/{id}")
    public String confirmer(@PathVariable("id") int id){
		ObjectifClasseRel rel=(ObjectifClasseRel) this.myService.getEntityById(id,ObjectifClasseRel.class);
		rel.setConfirmation(rel.getConfirmation()==false?true:false);
		rel.setDateConfirmation(new Date());
		this.myService.updateEntity(rel);
        return "redirect:/viewObjectifClasseRel/"+seance.getId();
    }
	    
    
    public Classe setClasse(Seance p)
    {
    	List liste=null;
    	Classe ee=null;
		if(p.getClasse()!=null)
		   liste=myService.listEntitiesByAttribute(Classe.class, "nomClasse", p.getClasse().getNomClasse());
    	if(liste!=null && !liste.isEmpty())
    	{
    		ee = (Classe)liste.get(0);
    		p.setClasse(ee);
    	}
    	return ee;
    }
    
    public ElementEnseignement setElement(Seance p)
    {
    	ElementEnseignement ee=null;
    	List liste=null;
		if(p.getClasse()!=null)
		   liste=myService.listEntitiesByAttribute(ElementEnseignement.class, "code", p.getElementEnseignement().getCode());
    	if(liste!=null && !liste.isEmpty())
    	{
    		ee = (ElementEnseignement)liste.get(0);
    		p.setElementEnseignement(ee);
    	}
    	return ee;
    }
    
    public Enseignant setEnseignant(Seance p)
    {
    	Enseignant ee=null;
    	List liste=null;
		if(p.getClasse()!=null)
		   liste=myService.listEntitiesByAttribute(Enseignant.class, "code", p.getEnseignant().getCode());
    	if(liste!=null && !liste.isEmpty())
    	{
    		ee = (Enseignant)liste.get(0);
    		p.setEnseignant(ee);
    	}
    	return ee;
    }

}
