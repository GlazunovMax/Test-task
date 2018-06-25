package by.testTask.testApp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.loader.plan.exec.internal.CollectionReferenceAliasesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.testTask.testApp.domain.MessageApp;
//import by.testTask.testApp.domain.Role;
import by.testTask.testApp.domain.Status;
//import by.testTask.testApp.domain.User;
import by.testTask.testApp.repository.MessageAppRepository;
//import by.testTask.testApp.repository.UserRepository;


@Controller
public class MainController {
	/*@Autowired
	private UserRepository userRepo;
	*/
	@Autowired
	private MessageAppRepository messageAppRepo;

    @GetMapping("/")
    public String startPage(Map<String, Object> model) {
        return "startPage";//home
    }
    
    @GetMapping("/mainUser")
    public String main(Map<String, Object> model){
    	Iterable<MessageApp> messagesApps = messageAppRepo.findAll();
    	
    	model.put("messagesApps", messagesApps);
    	
    	return "mainUser";
    }
    
    @PostMapping("/mainUser")
    public String addApp(@RequestParam String request, @RequestParam int bid, Map<String, Object> model){
    	
    	MessageApp messageApp = new MessageApp(request, bid, Status.PROCESSING);
    	messageAppRepo.save(messageApp);
    	
    	Iterable<MessageApp> messagesApps = messageAppRepo.findAll();
    	model.put("messagesApps", messagesApps);
    	
    	return "mainUser";
    }
    
    @GetMapping("/mainAdmin")
    public String mainAdmin(Map<String, Object> model) {
        return "mainAdmin";
    }
    
    
    @GetMapping("/showAllApps")
    public String showAllApps(Map<String, Object> model) {
    	model.put("applications", messageAppRepo.findAll());
        return "/showAllApps";
    }
    
    @GetMapping("/showAllApps/{messageApp}")
    public String appsEditForm(@PathVariable MessageApp messageApp, Model model){
    	model.addAttribute("messageApp", messageApp);
    	//model.addAttribute("messageApp", messageAppRepo.findById(messageApp));
    	model.addAttribute("statuses", Status.values());
    	
    	return "appsEdit";
    }
    
    @PostMapping("/showAllApps")
    public String appsSave(@RequestParam("id") MessageApp messageApp,
    					   @RequestParam String request, 
    					   @RequestParam Status status){
    	
    	
    	messageApp.setRequest(request);
    	
    	messageApp.setStatus(status);
    	
    	messageAppRepo.save(messageApp);
    	return "redirect:/showAllApps";
    }
    
    @GetMapping("/statistics")
    public String statistics(Map<String, Object> model) {
    	
    	int rejected = messageAppRepo.countByStatus(Status.REJECTED.ordinal());
        int accomplised = messageAppRepo.countByStatus(Status.ACCOMPLISHED.ordinal());
    		
    	model.put("rejected", rejected);
		model.put("accomplished", accomplised);
    	
        return "statistics";
    }
    
    
    /*
    @GetMapping("/registration")
    public String registration(){
    	return "registration";
    }
    
    @PostMapping("/registration")
    public String addUser(User user,  Map<String, Object> model){
    	 User userDB = userRepo.findByUsername(user.getUsername());
    	
    	 if(userDB != null){
    		 model.put("message", "user exists");
    		 return "registration";
    	 }
    	 
    	 user.setActive(true);
    	 user.setRoles(Collections.singleton(Role.USER));
    	 userRepo.save(user);
    	 
    	return "redirect:/login";
    }
    */

}
