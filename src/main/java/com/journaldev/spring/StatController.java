package com.journaldev.spring;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.Absence;
import com.journaldev.spring.model.AnneeUniversitaire;
import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.Etudiant;
import com.journaldev.spring.model.Seance;
import com.journaldev.spring.service.AnneeUnEditor;
import com.journaldev.spring.service.CahierClassEditor;
import com.journaldev.spring.service.MyService;
import com.viewModels.AbsenceModel;

@Controller
public class StatController {

	 @Autowired
	 private AbsenceModel absenceModel;
	 
	private MyService myService;
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    binder.registerCustomEditor(AnneeUniversitaire.class, new AnneeUnEditor());
	}
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String listPersonsm(Model model,HttpSession session) {
		model.addAttribute("absenceModel", new AbsenceModel());
		model.addAttribute("annees",this.myService.listEntities(AnneeUniversitaire.class));
		int level1=0;
		int level2=0;
		int level3=0;
		int level4=0;
		int level5=0;
		Map<String,Long> stats=myService.levelStat(null);
		for(Map.Entry<String, Long> entry : stats.entrySet())
		{
			if(entry.getKey().equals("1"))
				level1=entry.getValue().intValue();
			if(entry.getKey().equals("2"))
				level2=entry.getValue().intValue();
			if(entry.getKey().equals("3"))
				level3=entry.getValue().intValue();
			if(entry.getKey().equals("4"))
				level4=entry.getValue().intValue();
			if(entry.getKey().equals("5"))
				level5=entry.getValue().intValue();
		}
		model.addAttribute("level1", level1);
		model.addAttribute("level2", level2);
		model.addAttribute("level3", level3);
		model.addAttribute("level4", level4);
		model.addAttribute("level5", level5);
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "stats";
	}
	
	@Transactional
	@RequestMapping(value= "/searchStat", method = RequestMethod.POST)
	public String searchAbsences( @ModelAttribute("absenceModel") AbsenceModel p, Model model,BindingResult bindingResult,HttpSession session){
		absenceModel=p;
		model.addAttribute("annees",this.myService.listEntities(AnneeUniversitaire.class));
		int level1=0;
		int level2=0;
		int level3=0;
		int level4=0;
		int level5=0;
		Map<String,Long> stats=myService.levelStat(p.getAnneeUniversitaire().getCode());
		for(Map.Entry<String, Long> entry : stats.entrySet())
		{
			if(entry.getKey().equals("1"))
				level1=entry.getValue().intValue();
			if(entry.getKey().equals("2"))
				level2=entry.getValue().intValue();
			if(entry.getKey().equals("3"))
				level3=entry.getValue().intValue();
			if(entry.getKey().equals("4"))
				level4=entry.getValue().intValue();
			if(entry.getKey().equals("5"))
				level5=entry.getValue().intValue();
		}
		model.addAttribute("level1", level1);
		model.addAttribute("level2", level2);
		model.addAttribute("level3", level3);
		model.addAttribute("level4", level4);
		model.addAttribute("level5", level5);
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "stats";
	}
	
	@RequestMapping(value = "/statsAbsences", method = RequestMethod.GET)
	public String listStatsAbsences(Model model,HttpSession session) {
		model.addAttribute("absenceModel", new AbsenceModel());
		model.addAttribute("annees",this.myService.listEntities(AnneeUniversitaire.class));
		int level1A=0;
		int level2A=0;
		int level3A=0;
		int level4A=0;
		int level5A=0;
		model.addAttribute("level1", level1A);
		model.addAttribute("level2", level2A);
		model.addAttribute("level3", level3A);
		model.addAttribute("level4", level4A);
		model.addAttribute("level5", level5A);
		int level1B=0;
		int level2B=0;
		int level3B=0;
		int level4B=0;
		int level5B=0;
		model.addAttribute("level1", level1B);
		model.addAttribute("level2", level2B);
		model.addAttribute("level3", level3B);
		model.addAttribute("level4", level4B);
		model.addAttribute("level5", level5B);
		
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "statsAbsences";
	}
	
	@Transactional
	@RequestMapping(value= "/searchStatsAbsences", method = RequestMethod.POST)
	public String searchStatsAbsences( @ModelAttribute("absenceModel") AbsenceModel p, Model model,BindingResult bindingResult,HttpSession session){
		absenceModel=p;
		model.addAttribute("annees",this.myService.listEntities(AnneeUniversitaire.class));
		int level1A=0;
		int level2A=0;
		int level3A=0;
		int level4A=0;
		int level5A=0;

		int level1B=0;
		int level2B=0;
		int level3B=0;
		int level4B=0;
		int level5B=0;
		List<Seance> listSeance=(List<Seance>)(Object)myService.listEntitiesByAttribute(Seance.class, "confirmation", true);
		for(Seance seance:listSeance){
			if(seance.getConfirmation()){
				Hibernate.initialize(seance.getAbsences());
				Hibernate.initialize(seance.getClasse().getEtudiants());
				if(seance.getClasse().getNiveau()==1){
					level1A+=seance.getAbsences().size();
					level1B+=seance.getClasse().getEtudiants().size()-seance.getAbsences().size();
				}
				else if(seance.getClasse().getNiveau()==2){
					level2A+=seance.getAbsences().size();
					level2B+=seance.getClasse().getEtudiants().size()-seance.getAbsences().size();
				}
				else if(seance.getClasse().getNiveau()==3){
					level3A+=seance.getAbsences().size();
					level3B+=seance.getClasse().getEtudiants().size()-seance.getAbsences().size();
				}
				else if(seance.getClasse().getNiveau()==4){
					level4A+=seance.getAbsences().size();
					level4B+=seance.getClasse().getEtudiants().size()-seance.getAbsences().size();
				}
				else if(seance.getClasse().getNiveau()==5){
					level5A+=seance.getAbsences().size();
					level5B+=seance.getClasse().getEtudiants().size()-seance.getAbsences().size();
				}
			}
		}
		model.addAttribute("level1", level1A);
		model.addAttribute("level2", level2A);
		model.addAttribute("level3", level3A);
		model.addAttribute("level4", level4A);
		model.addAttribute("level5", level5A);
		model.addAttribute("level1B", level1B);
		model.addAttribute("level2B", level2B);
		model.addAttribute("level3B", level3B);
		model.addAttribute("level4B", level4B);
		model.addAttribute("level5B", level5B);
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "statsAbsences";
	}
	
	//Stat absences

}
