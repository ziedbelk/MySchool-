package com.journaldev.spring;

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

import com.journaldev.spring.model.EeUeRel;
import com.journaldev.spring.model.ElementEnseignement;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.PlanEtude;
import com.journaldev.spring.model.UniteEnseignement;
import com.journaldev.spring.model.UnitePedagogique;
import com.journaldev.spring.model.UnitePedagogiqueEnseignantRel;
import com.journaldev.spring.service.EEeditor;
import com.journaldev.spring.service.EnseignantEditor;
import com.journaldev.spring.service.MyService;
import com.journaldev.spring.service.PlanEtudeEditor;
import com.journaldev.spring.service.UEeditor;


@Controller
public class UnitePedagogiqueController {
	
	@Autowired
	private UnitePedagogique unitePedagogique;
	
	@Autowired
	private EeUeRel eeUeRel;
	
	private MyService myService;
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    binder.registerCustomEditor(Enseignant.class, new EnseignantEditor());
	    binder.registerCustomEditor(UniteEnseignement.class, new UEeditor());
	}
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	
	@RequestMapping(value= "/unitePedagogique/add", method = RequestMethod.POST)
	public String addHoliday(@ModelAttribute("unite") UnitePedagogique p){
		
		if(p.getId() == 0){
			//new person, add it
			setUniteEnseignement(p);
			this.myService.addEntity(p);
		}else{
			//existing person, call update
			setUniteEnseignement(p);
			this.myService.updateEntity(p);
		}
		
		return "redirect:/uepedagogique";
		
	}
	
	@Transactional
	@RequestMapping("/removeUnitePedagogique/{id}")
    public String removeElement(@PathVariable("id") int id){
		List<Object> listUPER=myService.listEntitiesByAttribute(UnitePedagogiqueEnseignantRel.class, "unitePedagogique.id", id);
		for(Object obj:listUPER){
			this.myService.removeEntity(((UnitePedagogiqueEnseignantRel)obj).getId(), UnitePedagogiqueEnseignantRel.class);
		}
        this.myService.removeEntity(id, UnitePedagogique.class);
        return "redirect:/uepedagogique";
    }

	@RequestMapping(value = "/uepedagogique", method = RequestMethod.GET)
	public String listElements(Model model,HttpSession session) {
		model.addAttribute("unite", new UnitePedagogique());
		model.addAttribute("unites", this.myService.listEntities(UnitePedagogique.class));
		model.addAttribute("listElements", this.myService.listEntities(UniteEnseignement.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "uepedagogique";
	}
	
	 @RequestMapping("/viewUp/{id}")
	 public String addEeUeRel(@PathVariable("id") int id, Model model,HttpSession session){
		    unitePedagogique=(UnitePedagogique) this.myService.getEntityById(id,UnitePedagogique.class);
	        model.addAttribute("upEnseignantRel",new UnitePedagogiqueEnseignantRel());
	        model.addAttribute("ups", this.myService.listEntitiesByAttribute(UnitePedagogiqueEnseignantRel.class, "unitePedagogique.id", id));
	        model.addAttribute("listElements",this.myService.listEntities(Enseignant.class));
	       // model.addAttribute("listEnseignants",this.myService.listEntities(Enseignant.class));
	        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
	        return "unitepedagogique";
	 }
	 
		@RequestMapping(value= "/upEnseignantRel/add", method = RequestMethod.POST)
		public String addEeUe(@ModelAttribute("eeue") UnitePedagogiqueEnseignantRel p){
			if(p.getId() == 0){
				p.setUnitePedagogique(unitePedagogique);
				setEnseignant( p);
				this.myService.addEntity(p);
			}else{
				setEnseignant( p);
				this.myService.updateEntity(p);
			}
			return "redirect:/viewUp/"+String.valueOf(unitePedagogique.getId());
		}
		
		@RequestMapping("/removeEnseignantRel/{id}")
	    public String removeEeue(@PathVariable("id") int id){
			//must be in same transaction
	        this.myService.removeEntity(id, UnitePedagogiqueEnseignantRel.class);
	       // this.myService.deleteEntitiesByAttribute(EnseignantEeUeRel.class, "eeUeRel.id", id);
	        return "redirect:/viewUp/"+String.valueOf(unitePedagogique.getId());
	    }
		
		  //**********
	    public void setEnseignant(UnitePedagogiqueEnseignantRel p)
	    {
	    	List liste=null;
			if(p.getEnseignant()!=null)
			   liste=myService.listEntitiesByAttribute(Enseignant.class, "code", p.getEnseignant().getCode());
	    	if(liste!=null && !liste.isEmpty())
	    	{
	    		Object country = liste.get(0);
	    		p.setEnseignant((Enseignant)country);
	    	}
	    }
	    
	    public void setUniteEnseignement(UnitePedagogique p)
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
	    
}
