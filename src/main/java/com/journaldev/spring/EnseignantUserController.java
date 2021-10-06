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
import com.journaldev.spring.model.ElementEnseignement;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.Etudiant;
import com.journaldev.spring.model.ObjectifClasseRel;
import com.journaldev.spring.model.PlanEtude;
import com.journaldev.spring.model.Seance;
import com.journaldev.spring.service.EEeditor;
import com.journaldev.spring.service.EnseignantEditor;
import com.journaldev.spring.service.MyService;
import com.journaldev.spring.service.PlanEtudeEditor;
import com.viewModels.AbsenceModel;

@Controller
public class EnseignantUserController {
	
private MyService myService;
	
	@Autowired
	private Seance seance;
	
	@Autowired
	private AbsenceModel absenceModel1;
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@Transactional
	@RequestMapping(value = "/seanceEnseignantUser", method = RequestMethod.GET)
	public String listEtudiants(Model model,HttpSession session) {
		model.addAttribute("absenceModel", new AbsenceModel());
		model.addAttribute("listSeances", new ArrayList<Seance>());
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "seanceEnseignantUser";
	}
	
	@Transactional
	@RequestMapping(value= "/searchSeanceEnseignantUser", method = RequestMethod.POST)
	public String searchAbsences( @ModelAttribute("absenceModel") AbsenceModel p, Model model,BindingResult bindingResult,HttpSession session){
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("classe.id",p.getClasse()!=null ? p.getClasse().getId():null);
//		map.put("date",p.getDate());
		absenceModel1=p;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		List <Seance> listSeances=new ArrayList<Seance>();
		Enseignant enseignant=(Enseignant)session.getAttribute("user");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("classe.nomClasse", p.getClasse()!=null ? p.getClasse().getNomClasse():null);
		map.put("enseignant.code", enseignant.getCode());
		for(Seance etud:(List<Seance>)(Object)this.myService.listEntitiesByAttributes(Seance.class, map))
		{ 
			if(etud.getDate()!=null && sf.format(etud.getDate()).equals(sf.format(absenceModel1.getDate())))
				listSeances.add(etud);
		}
		model.addAttribute("absenceModel", p);
		model.addAttribute("listSeances", listSeances);
		model.addAttribute("classes",this.myService.listEntities(Classe.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "seanceEnseignantUser";
	}
	
	@Transactional
	@RequestMapping("/confirmerSeancefcEnseignantUser/{id}")
    public String confirmerSeancefc(@PathVariable("id") int id){
		Seance rel=(Seance) this.myService.getEntityById(id,Seance.class);
		rel.setConfirmation(rel.getConfirmation()==false?true:false);
		rel.setDateConfirmation(new Date());
		this.myService.updateEntity(rel);
        return "redirect:/seanceEnseignantUser";
    }
		
		//*************************************** objectifs:
		@Transactional
		@RequestMapping("/viewObjectifClasseRelEnseignantUser/{id}")
		public String viewObjectifs(@PathVariable("id") int id, Model model,HttpSession session){
			    seance=(Seance) this.myService.getEntityById(id,Seance.class);
			    Map<String,Object> parameters=new HashMap<String,Object>(2);
			    parameters.put("elementEnseignement.id", seance.getElementEnseignement()!=null?seance.getElementEnseignement().getId():null);
			    parameters.put("classe.id", seance.getClasse()!=null?seance.getClasse().getId():null);
		        model.addAttribute("objectifClasses", this.myService.listEntitiesByAttributes(ObjectifClasseRel.class, parameters));
		        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		        return "objectifClasseEnseignantUser";
	    }
		
		@Transactional
		@RequestMapping("/confirmerEnseignantUser/{id}")
	    public String confirmer(@PathVariable("id") int id){
			ObjectifClasseRel rel=(ObjectifClasseRel) this.myService.getEntityById(id,ObjectifClasseRel.class);
			rel.setConfirmation(rel.getConfirmation()==false?true:false);
			rel.setDateConfirmation(new Date());
			this.myService.updateEntity(rel);
	        return "redirect:/viewObjectifClasseRelEnseignantUser/"+seance.getId();
	    }
		
		//*************************************** absences:
		@Transactional
	    @RequestMapping("/viewAbsencesEnseignantUser/{id}")
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
			return "absenceFromSeanceEnseignantUser";
	    }
		
		@Transactional
	    @RequestMapping(value="/markPresentFromSeanceEnseignantUser/{id}")
	    public String markPresent( @PathVariable("id") int id, Model model,HttpSession session){

	    	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");	 
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
	        return "absenceFromSeanceEnseignantUser";
	    }
	    
		@Transactional
	    @RequestMapping(value="/markAbsentFromSeanceEnseignantUser/{id}")
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
	        return "absenceFromSeanceEnseignantUser";
	    }

}
