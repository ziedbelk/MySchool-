package com.journaldev.spring;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.Emploie;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.SpecialHoliday;
import com.journaldev.spring.service.MyService;
import com.journaldev.spring.service.PersonService;

@Controller
public class EmploieController {

	private MyService myService;
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@RequestMapping(value = "/specialHolidays", method = RequestMethod.GET)
	public String listHolidays(Model model,HttpSession session) {
		model.addAttribute("holiday", new Emploie());
		model.addAttribute("listHolidays", this.myService.listEntities(Emploie.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "specialHoliday";
	}
	
	//For add and update person both
	@RequestMapping(value= "/specialHoliday/add", method = RequestMethod.POST)
	public String addHoliday(@ModelAttribute("holiday") Emploie p){
		
		if(p.getId() == 0){
			//new person, add it
			this.myService.addEntity(p);
		}else{
			//existing person, call update
			this.myService.updateEntity(p);
		}
		
		return "redirect:/specialHolidays";
		
	}
	
	@RequestMapping("/removeSpecialHoliday/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, Emploie.class);
        return "redirect:/specialHolidays";
    }
 
    @RequestMapping("/editSpecialHoliday/{id}")
    public String editHoliday(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("holiday", this.myService.getEntityById(id,Emploie.class));
        model.addAttribute("listHolidays", this.myService.listEntities(Emploie.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "holiday";
    }
}
