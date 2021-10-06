package com.journaldev.spring;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.service.MyService;
import com.journaldev.spring.service.PersonService;


@Controller
public class PersonController {
	
	private MyService myService;
	
	
	
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
//	@RequestMapping(value = "/persons", method = RequestMethod.GET)
//	public String listPersons(Model model) {
//		model.addAttribute("person", new Person());
//		model.addAttribute("listPersons", this.myService.listEntities(Person.class));
//		return "person";
//	}
//	
//	//For add and update person both
//	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
//	public String addPerson(@ModelAttribute("person") Person p){
//		
//		if(p.getId() == 0){
//			//new person, add it
//			Class<?> objectClass=Person.class;
//			if(p.getType().equals("cup"))
//				objectClass=Cup.class;
//			else if(p.getType().equals("enseignant"))
//				objectClass=Enseignant.class;
//			else if(p.getType().equals("scolarite"))
//				objectClass=Scolarite.class;
//			else if(p.getType().equals("admin"))
//				objectClass=Admin.class;
//			else if(p.getType().equals("coordinateurModule"))
//				objectClass=CoordinateurModule.class;
//			Object myObj;
//			try {
//				myObj = objectClass.newInstance();
//				BeanUtils.copyProperties(p, myObj);
//				this.myService.addEntity(myObj);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//		}else{
//			//existing person, call update
//			this.myService.updateEntity(p);
//		}
//		
//		return "redirect:/persons";
//		
//	}
//	
//	@RequestMapping("/remove/{id}")
//    public String removePerson(@PathVariable("id") int id){
//		
//        this.myService.removeEntity(id, Person.class);
//        return "redirect:/persons";
//    }
// 
//    @RequestMapping("/edit/{id}")
//    public String editPerson(@PathVariable("id") int id, Model model){
//        model.addAttribute("person", this.myService.getEntityById(id,Person.class));
//        model.addAttribute("listPersons", this.myService.listEntities(Person.class));
//        return "person";
//    }
	
}
