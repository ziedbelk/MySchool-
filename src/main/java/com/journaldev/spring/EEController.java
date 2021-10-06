package com.journaldev.spring;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.journaldev.spring.model.Chapitre;
import com.journaldev.spring.model.EeUeRel;
import com.journaldev.spring.model.ElementEnseignement;
import com.journaldev.spring.model.Enseignant;
import com.journaldev.spring.model.Objectif;
import com.journaldev.spring.model.ObjectifClasseRel;
import com.journaldev.spring.model.Seance;
import com.journaldev.spring.model.SpecialHoliday;
import com.journaldev.spring.model.UniteEnseignement;
import com.journaldev.spring.service.MyService;
import com.journaldev.spring.service.UEeditor;

@Controller
public class EEController {

private MyService myService;
	
    @Autowired
    private ElementEnseignement elementEnseignement;
    
	@Autowired(required=true)
	@Qualifier(value="myService")
	public void setMyServiceService(MyService ps){
		this.myService = ps;
	}
	
	@InitBinder 
	public void initBinder (WebDataBinder binder) {
	    binder.registerCustomEditor(UniteEnseignement.class, new UEeditor());
	}
	
	@RequestMapping(value = "/elements", method = RequestMethod.GET)
	public String listElements(Model model,HttpSession session) {
		model.addAttribute("element", new ElementEnseignement());
		model.addAttribute("elements", this.myService.listEntities(ElementEnseignement.class));
		model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
		return "element";
	}
	
	//For add and update person both
	@RequestMapping(value= "/element/add", method = RequestMethod.POST)
	public String addHoliday(@ModelAttribute("element") ElementEnseignement p){
		
		if(p.getId() == 0){
			//new person, add it
			this.myService.addEntity(p);
		}else{
			//existing person, call update
			this.myService.updateEntity(p);
		}
		
		return "redirect:/elements";
		
	}
	
	@RequestMapping("/removeElement/{id}")
    public String removeElement(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, ElementEnseignement.class);
        return "redirect:/elements";
    }
 
    @RequestMapping("/editElement/{id}")
    public String editElement(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("element", this.myService.getEntityById(id,ElementEnseignement.class));
        model.addAttribute("elements", this.myService.listEntities(ElementEnseignement.class));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "element";
    }
    
    //******************************************chapitres:
    @RequestMapping("/viewChapitre/{id}")
    public String addEeUeRel(@PathVariable("id") int id, Model model,HttpSession session){
    	elementEnseignement=(ElementEnseignement) this.myService.getEntityById(id,ElementEnseignement.class);
        model.addAttribute("chapitre",new Chapitre());
        model.addAttribute("chapitres", this.myService.listEntitiesByAttribute(Chapitre.class, "elementEnseignement.id", id));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "chapitre";
    }
    
	@RequestMapping(value= "/chapitre/add", method = RequestMethod.POST)
	public String addEeUe(@ModelAttribute("chapitre") Chapitre p){
		if(p.getId() == 0){
			p.setElementEnseignement(elementEnseignement);
			this.myService.addEntity(p);
		}else{
			p.setElementEnseignement(elementEnseignement);
			this.myService.updateEntity(p);
		}
		return "redirect:/viewChapitre/"+String.valueOf(elementEnseignement.getId());
	}
	
	@RequestMapping("/removeChapitre/{id}")
    public String removeEeue(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, Chapitre.class);
        return "redirect:/viewChapitre/"+String.valueOf(elementEnseignement.getId());
    }
 
    @RequestMapping("/editChapitre/{id}")
    public String editEeue(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("chapitre", this.myService.getEntityById(id,Chapitre.class));
        model.addAttribute("chapitres", this.myService.listEntitiesByAttribute(Chapitre.class, "elementEnseignement.id", elementEnseignement.getId()));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "chapitre";
    }
    
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E://";
    
    @Transactional
    @RequestMapping(value = "/upload/{id}", method = RequestMethod.POST)
    public String singleFileUpload(@PathVariable("id") int id,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

//        if (file.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//            return "redirect:uploadStatus";
//        }
    	Chapitre chapitre=(Chapitre)this.myService.getEntityById(id,Chapitre.class);
        if(!file.isEmpty())
        try {
        	
        	
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            String stringPath=UPLOADED_FOLDER + file.getOriginalFilename()+chapitre.getId();
            Path path = Paths.get(stringPath);
            Files.write(path, bytes);
            chapitre.setPath(stringPath);
            this.myService.updateEntity(chapitre);
//            redirectAttributes.addFlashAttribute("message", 
//                        "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

//        return "redirect:/uploadStatus";
        return "redirect:/viewChapitre/"+String.valueOf(elementEnseignement.getId());
    }
    
    @RequestMapping(value="/download/{id}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("id") int id) throws IOException {
     
    	Chapitre chapitre=(Chapitre)this.myService.getEntityById(id,Chapitre.class); 
    	if(chapitre==null)
    		return;
        File file = new File(chapitre.getPath());
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
         
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
         
        System.out.println("mimetype : "+mimeType);
         
        response.setContentType(mimeType);
         
        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
 
         
        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
         
        response.setContentLength((int)file.length());
 
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
 
        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
    
    //****************************************** objectifs:
    @RequestMapping("/viewObjectif/{id}")
    public String viewObjectif(@PathVariable("id") int id, Model model,HttpSession session){
    	elementEnseignement=(ElementEnseignement) this.myService.getEntityById(id,ElementEnseignement.class);
        model.addAttribute("objectif",new Objectif());
        model.addAttribute("objectifs", this.myService.listEntitiesByAttribute(Objectif.class, "elementEnseignement.id", id));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "objectif";
    }
    
    
    @RequestMapping(value= "/objectif/add", method = RequestMethod.POST)
	public String addObjectif(@ModelAttribute("objectif") Objectif p){
		if(p.getId() == 0){
			p.setElementEnseignement(elementEnseignement);
			this.myService.addEntity(p);
			List<Seance> listSeance=(List<Seance>)(Object)this.myService.listEntitiesByAttribute(Seance.class, "elementEnseignement.id", elementEnseignement.getId());
			for(Seance seance:listSeance)
			{
				ObjectifClasseRel ocrl=new ObjectifClasseRel();
				ocrl.setObjectif(p);
				ocrl.setClasse(seance.getClasse());
				ocrl.setElementEnseignement(p.getElementEnseignement());
				ocrl.setConfirmation(false);
				this.myService.addEntity(ocrl);
			}
		}else{
			p.setElementEnseignement(elementEnseignement);
			this.myService.updateEntity(p);
		}
		return "redirect:/viewObjectif/"+String.valueOf(elementEnseignement.getId());
	}
    
	@RequestMapping("/removeObjectif/{id}")
    public String removeObjectif(@PathVariable("id") int id){
		
        this.myService.removeEntity(id, Objectif.class);
        return "redirect:/viewObjectif/"+String.valueOf(elementEnseignement.getId());
    }
 
    @RequestMapping("/editObjectif/{id}")
    public String editObjectif(@PathVariable("id") int id, Model model,HttpSession session){
        model.addAttribute("objectif", this.myService.getEntityById(id,Objectif.class));
        model.addAttribute("objectifs", this.myService.listEntitiesByAttribute(Objectif.class, "elementEnseignement.id", elementEnseignement.getId()));
        model.addAttribute("login", session.getAttribute("user")!=null?((Enseignant)session.getAttribute("user")).getLogin():null);
        return "objectif";
    }
    
}
