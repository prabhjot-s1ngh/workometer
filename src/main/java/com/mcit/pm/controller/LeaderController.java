
package com.mcit.pm.controller;

import com.mcit.pm.model.Services.EmployeeService;
import com.mcit.pm.model.Services.ProjectService;
import com.mcit.pm.model.Services.TaskService;
import com.mcit.pm.model.entities.Project;
import com.mcit.pm.model.entities.Task;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/leader")
public class LeaderController {

    @Autowired
    private ProjectService serviceP;    
    
    @Autowired
    private EmployeeService serviceE;      
    
    @Autowired
    private TaskService serviceT;
    
    private Integer getUserName(){
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName();
      return Integer.parseInt(name);
    }
    
    @RequestMapping(path ="", method = RequestMethod.GET)
    public String showleaderPage(Model model) { 
        List<Project> projects = serviceP.retriveAllProjectsByLeaderId(this.getUserName());
        
        model.addAttribute("role","leader");        
        model.addAttribute("projects", projects);        
        model.addAttribute("name",serviceE.getEmployeeById(this.getUserName()).getEmployeeName().toUpperCase());
      return "/leader/leader";
    }
    
    @RequestMapping(path = "/taskForm", method = RequestMethod.GET)
    public String showDeleteUpdateForm() {
        return "forward:/task/tForm";
    }    
    
    @RequestMapping(path = "/taskAdd", method = RequestMethod.POST)
    public String addTask(@ModelAttribute @Valid Task task, BindingResult binding) {
        if (binding.hasErrors()) {
            return "forward:/task/tForm";
        }
        serviceT.saveAndRetrieveTasks(task);
        return "forward:/task/showAllTasks/leader";
    }
    
    @RequestMapping(path = "/taskViewEdit", method = {RequestMethod.GET,RequestMethod.POST})
    public String showAllTasks(Model model) {
        return "forward:/task/showAllTasks/leader";
    }    
    @RequestMapping(path = "/projectViewEdit", method = {RequestMethod.GET,RequestMethod.POST})
    public String showAllProjects(Model model) {
        return "forward:/project/showAllProjects/"+this.getUserName();
    }
    @RequestMapping(path = "/taskViewEditByProjectId/{projectId}", method = {RequestMethod.GET,RequestMethod.POST})
    public String showAllTasksByProjectId(@PathVariable Integer projectId) {
        return "forward:/task/showAllTasksByProjectId/"+projectId;
    }
    
    @RequestMapping(path = "/taskUpdate/{id}", method = RequestMethod.GET)
    public String updateTask(@PathVariable Integer id) {
        return "forward:/task/updateTask/"+id+"/leader";
    }
    
    @RequestMapping(path = "/taskUpdate", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute @Valid Task task, BindingResult binding) {
        if (binding.hasErrors()) {
            return "forward:/task/tForm";
           
        }
        serviceT.updateTask(task);
        return "forward:/task/showAllTasks/leader";
    }
    
    @RequestMapping(path = "/viewMembers", method = RequestMethod.GET)
    public String showAllMembers(Model model) {
        return "forward:/employee/showAllMembers";
    }
    
    @RequestMapping(path = "/statsView/{id}", method = RequestMethod.GET)
    public String showAllMemberStats(@PathVariable Integer id) {
        return "forward:/task/showMemberStats/"+id;
    }
    
    @RequestMapping(path = "/taskDelete/{id}", method = RequestMethod.GET)
    public String deleteProject(@PathVariable Integer id) {
        return "forward:/task/deleteTask/"+id;
    }    
    
    @RequestMapping(path = "/listProjectMembers/{id}", method = RequestMethod.GET)
    public String showAllProjectMembers(@PathVariable Integer id) {
        return "forward:/employee/showAllProjectMembers/"+id+"/leader";
    }     
    
    @RequestMapping(path = "/AllTasksByMember/{id}", method = RequestMethod.GET)
    public String AllTasksByMember(@PathVariable Integer id) {
        return "forward:/task/showAllTasksByMember/"+id;
    }    
}
