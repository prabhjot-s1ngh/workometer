package com.mcit.pm.controller;

import com.mcit.pm.model.DAO.EmployeeDAO;
import com.mcit.pm.model.DAO.TaskDAO;
import com.mcit.pm.model.Services.EmployeeService;
import com.mcit.pm.model.Services.ProjectService;
import com.mcit.pm.model.Services.TaskService;
import com.mcit.pm.model.entities.Employee;
import com.mcit.pm.model.entities.Project;
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
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    private Project project;
    
    @Autowired
    private EmployeeDAO edao;
    
    
    @Autowired
    private TaskDAO tdao;

    @Autowired
    private TaskService serviceT;    
    
    @Autowired
    private EmployeeService serviceE;
    
    @Autowired
    private ProjectService service;
    
    private Integer getUserName(){
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName();
      return Integer.parseInt(name);
    } 
    
    @RequestMapping(path = "/pForm", method = {RequestMethod.GET,RequestMethod.POST})
    public String showForm(Model model){
        List<Employee> employees = serviceE.retriveAllLeaders();
        model.addAttribute("employees", employees);
        return "projectForm";
    }
    
    @ModelAttribute("project")
    public Project getProject(){
        return project;
    }
     
    @RequestMapping(path = "/showAllProjects", method = {RequestMethod.POST,RequestMethod.GET})
    public String showAllProjects(Model model) {
        List<Project> projects = service.retriveAllProjects();
        model.addAttribute("projects", projects);
        return "deleteUpdateProjects";
    }
    
    @RequestMapping(path = "/showAllProjects/{username}", method = {RequestMethod.POST,RequestMethod.GET})
    public String showAllProjectsByProjectId(@PathVariable Integer username,Model model) {
        List<Project> projects = service.retriveAllProjectsByLeaderId(username);
        model.addAttribute("projects", projects);
        model.addAttribute("role","leader");
        return "showProjectsByRole";
    }  
    
    @RequestMapping(path = "showAllProjects/member", method = {RequestMethod.POST,RequestMethod.GET})
    public String showAllProjectsByMemberId(Model model) {
        List<Project> projects = service.retriveAllProjectsByMemberId(this.getUserName());
        model.addAttribute("role","member");
        model.addAttribute("projects", projects);
        return "showProjectsByRole";
    }
    
    @RequestMapping(path = {"/updateProject","/updateProject/{id}"}, method = RequestMethod.GET)
    public String updateEmployee(@PathVariable(required = false) Integer id, Model model) {
        project = service.getProjectById(id);
        
        List<Employee> employees = serviceE.retriveAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute(project);
        return "updateProjectForm";
    }
    
        @RequestMapping(path = "/deleteProject/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable Integer id, Model model) {
        try{
            service.deleteProjectById(id);
            model.addAttribute("message",id+" "+"deleted");
            
        }catch(Exception e){
            model.addAttribute("message","PROJECT "+id+" IS ASSIGNED TO SOME TASKS.");
        }
        List<Project> projects = service.retriveAllProjects( );
        model.addAttribute("projects", projects);
        return "deleteUpdateProjects";
    } 
    
    
        @RequestMapping(path = "/updatProject", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute @Valid Project project, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "projectForm";
        }

        project = service.updateProject(project);
        List<Project> projects = service.retriveAllProjects( );
        model.addAttribute("message", project.getProjectId() + " updated.");
        model.addAttribute("projects", projects);
        return "deleteUpdateProjects";
    }
}
