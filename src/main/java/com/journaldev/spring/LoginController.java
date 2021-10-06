package com.journaldev.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.PersonType;
import com.journaldev.spring.service.MyService;

@Controller
public class LoginController {

	private MyService myService;
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("error", "");
		model.addAttribute("enseignant", new Enseignant());
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		model.addAttribute("error", "");
		model.addAttribute("enseignant", new Enseignant());
		return "login";
	}
	
	
	
	//For add and update person both
	@Transactional
	@RequestMapping(value= "/login/send", method = RequestMethod.POST)
	public String addEtudiant( @ModelAttribute("enseignant") Enseignant p, BindingResult bindingResult,HttpSession session){
		List<Object> liste=new ArrayList<Object>();
		Map<String,Object> mapCond=new HashMap<String,Object>(2);
		mapCond.put("password", p.getPassword());
		mapCond.put("login", p.getLogin());
		liste=myService.listEntitiesByAttributes(Enseignant.class, mapCond);
        if(!liste.isEmpty()){
        	session.setAttribute("user", (Enseignant)liste.get(0));
    		if(((Enseignant)liste.get(0)).getPersonType().equals(PersonType.Admin))
    		  return "redirect:/plans";
    		else if(((Enseignant)liste.get(0)).getPersonType().equals(PersonType.Enseignant))
    		  return "redirect:/seanceEnseignantUser";
    		else if(((Enseignant)liste.get(0)).getPersonType().equals(PersonType.Cup))
      		  return "redirect:/uepedagogique";
        }
		return "redirect:/login";
	}
}
