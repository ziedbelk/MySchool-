package com.journaldev.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
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

import com.journaldev.spring.model.Absence;
import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.ElementEnseignement;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.Etudiant;
import com.journaldev.spring.service.CahierClassEditor;
import com.journaldev.spring.service.MyService;
import com.viewModels.AbsenceModel;


@Controller
public class AbsenceController {
	
	
	private MyService myService;
	
	
	 @Autowired
	 private AbsenceModel absenceModel;
	
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    binder.registerCustomEditor(Classe.class, new CahierClassEditor());
	}
	
	@RequestMapping(value = "/absences", method = RequestMethod.GET)
	public String listEtudiants(Model model,HttpSession session) {
		model.addAttribute("absenceModel", new AbsenceModel());
		model.addAttribute("listEtudiants", new ArrayList<Etudiant>());
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "absence";
	}
	
	//For add and update person both
	@Transactional
	@RequestMapping(value= "/absence/search", method = RequestMethod.POST)
	public String searchAbsences( @ModelAttribute("absenceModel") AbsenceModel p, Model model,BindingResult bindingResult,HttpSession session){
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("classe.id",p.getClasse()!=null ? p.getClasse().getId():null);
//		map.put("date",p.getDate());
		absenceModel=p;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		List <Etudiant> listEtudiants=new ArrayList<Etudiant>();
		for(Object etudiant:this.myService.listEntitiesByAttribute(Etudiant.class, "classe.nomClasse", p.getClasse()!=null ? p.getClasse().getNomClasse():null))
		{
			Etudiant etud=(Etudiant)etudiant;
			etud.setAbsent(false);
			Hibernate.initialize(etud.getAbsences());
			for(Absence absence:etud.getAbsences())
			{
				if( ((sf.format(absence.getDate()).equals(sf.format(absenceModel.getDate())))))
				{
					etud.setAbsent(true);
					break;
				}
			}
			listEtudiants.add(etud);
		}
		model.addAttribute("absenceModel", p);
		model.addAttribute("listEtudiants", listEtudiants);
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "absence";
	}
	

 
	@Transactional
    @RequestMapping(value="/markPresent/{id}")
    public String markPresent( @PathVariable("id") int id, Model model,HttpSession session){

    	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");	 
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("etudiant.id",id);
		map.put("date",sf.format(absenceModel.getDate()));
    	List <Object> listAb=this.myService.listEntitiesByAttributes(Absence.class, map);
    	if(listAb!=null && !listAb.isEmpty())
    		this.myService.removeEntity(((Absence)listAb.get(0)).getId(), Absence.class);
    	List <Etudiant> listEtudiants=new ArrayList<Etudiant>();
		for(Object etudiant:this.myService.listEntitiesByAttribute(Etudiant.class, "classe.nomClasse", absenceModel.getClasse()!=null ? absenceModel.getClasse().getNomClasse():null))
		{
			Etudiant etud=(Etudiant)etudiant;
			etud.setAbsent(false);
			if(etud.getId()!=id)
			{
				 Hibernate.initialize(etud.getAbsences());
					for(Absence absence:etud.getAbsences())
					{
						if( (sf.format(absence.getDate()).equals(sf.format(absenceModel.getDate()))))
						{
							etud.setAbsent(true);
							break;
						}
					}
			}
			listEtudiants.add(etud);
		}
		model.addAttribute("absenceModel", absenceModel);
		model.addAttribute("listEtudiants", listEtudiants);
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "absence";
    }
    
	@Transactional
    @RequestMapping(value="/markAbsent/{id}")
    public String markAbsent(@PathVariable("id") int id, Model model,HttpSession session){
    	
    	Etudiant etu=(Etudiant) this.myService.getEntityById(id,Etudiant.class);
    	Map<String,Object> map=new HashMap<String,Object>();
		map.put("etudiant.id",id);
		map.put("date",absenceModel.getDate());
    	List <Object> listAb=this.myService.listEntitiesByAttributes(Absence.class, map);
    	if((listAb==null || listAb.isEmpty()))
    	{
    		Absence abs=new Absence();
        	abs.setDate(absenceModel.getDate());
        	abs.setEtudiant(etu);
        	this.myService.addEntity(abs);
    	}
    	
    	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    	List <Etudiant> listEtudiants=new ArrayList<Etudiant>();
		for(Object etudiant:this.myService.listEntitiesByAttribute(Etudiant.class, "classe.nomClasse", absenceModel.getClasse()!=null ? absenceModel.getClasse().getNomClasse():null))
		{
			Etudiant etud=(Etudiant)etudiant;
			etud.setAbsent(false);
			Hibernate.initialize(etud.getAbsences());
			for(Absence absence:etud.getAbsences())
			{
				if( (sf.format(absence.getDate()).equals(sf.format(absenceModel.getDate()))))
				{
					etud.setAbsent(true);
					break;
				}
			}
			listEtudiants.add(etud);
		}
		model.addAttribute("absenceModel", absenceModel);
		model.addAttribute("listEtudiants", listEtudiants);
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "absence";
    }


}
