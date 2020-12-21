package com.mcit.pm.controller;

import com.mcit.pm.model.Services.EmployeeService;
import com.mcit.pm.model.Services.ProjectService;
import com.mcit.pm.model.Services.TaskService;
import com.mcit.pm.model.entities.Employee;
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
@RequestMapping(value = "/admin")
public class AdminController {
    
    
    @Autowired
    private Task task;

    @Autowired
    private TaskService serviceT;    
    
    @Autowired
    private Employee employee;

    @Autowired
    private EmployeeService serviceE;    
    
    
    @Autowired
    private ProjectService serviceP;
    
    @Autowired
    private Project project;
    
    @ModelAttribute("employee")
    public Employee getEmployee() {
        return employee;
    }

    @ModelAttribute("project")
    public Project getProject() {
        return project;
    }

    @ModelAttribute("task")
    public Task getTask() {
        return task;
    }    
    
    private Integer getUserName(){
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName();
      return Integer.parseInt(name);
    }
    
    @RequestMapping(path ="", method = RequestMethod.GET)
    public String showAdminPage(Model model) {
        List<Project> projects = serviceP.retriveAllProjects();
        model.addAttribute("projects", projects);        
        model.addAttribute("name",serviceE.getEmployeeById(this.getUserName()).getEmployeeName().toUpperCase());
        return "admin/admin";
    }
    
   
    @RequestMapping(path = "/employeeAdd", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute @Valid Employee employee, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "forward:/employee/eForm";
        }
        
        try{
            List<Employee> employees = serviceE.saveAndRetrieveEmployees(employee);
            model.addAttribute("employees", employees);
            return "forward:/employee/showAllEmployees";            
        }
        catch(Exception e){
        model.addAttribute("message","ID IS ALREADY TAKEN.");
        model.addAttribute("heading", "Add");
        model.addAttribute("isReadble", "false");
        return "forward:/employee/eForm";            
        }        
    }    
    

    @RequestMapping(path = "/employeeAddForm", method = {RequestMethod.GET,RequestMethod.POST})
    public String showEmployeeForm() {
        return "forward:/employee/eForm";
    }
   
    
    @RequestMapping(path = "/employeeViewEdit", method = RequestMethod.GET)
    public String showEmployees() {
        return "forward:/employee/showAllEmployees";
    }

    @RequestMapping(path = {"/employeeUpdate","/employeeUpdate/{id}"}, method = RequestMethod.GET)
    public String updateEmployeeForm(@PathVariable Integer id) {
        return "forward:/employee/updateEmployee/" + id;
    }
    
    @RequestMapping(path = "/employeeUpdate", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute @Valid Employee employee, BindingResult binding) {
        if (binding.hasErrors()) {
            return "EmployeeForm";
        }
        employee = serviceE.updateEmployee(employee);
        return "forward:/employee/showAllEmployees";
    }
    
    
    @RequestMapping(path = "/employeeDelete/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable Integer id) {
        return "forward:/employee/deleteEmployee/"+id;
    }    

    @RequestMapping(path = "/projectAddForm", method = RequestMethod.GET)
    public String showProjectForm() {
        return "forward:/project/pForm";
    }
    
    @RequestMapping(path = "/projectAdd", method = RequestMethod.POST)
    public String addProject(@ModelAttribute @Valid Project project, BindingResult binding, Model model) {
        
        if (binding.hasErrors()) {
            return "projectForm";
        }
        try{
        List<Project> projects = serviceP.saveAndRetrieveProjects(project);
        model.addAttribute("projects", projects);
        model.addAttribute("p", project);
        return "forward:/project/showAllProjects";
        }catch(Exception e){
            System.out.println("Error: "+e);
            model.addAttribute("message", "ID ALREADY TAKEN.");
            return "forward:/project/pForm";
        }
    }

    @RequestMapping(path = "/projectViewEdit", method = RequestMethod.GET)
    public String showProjects() {
        return "forward:/project/showAllProjects";
    }

    @RequestMapping(path = "/projectUpdate/{id}", method = RequestMethod.GET)
    public String updateProject(@PathVariable Integer id) {
        return "forward:/project/updateProject/"+id;
    }
    
    @RequestMapping(path = "/projectUpdate", method = RequestMethod.POST)
    public String updateProject(@ModelAttribute @Valid Project project, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "projectForm";
        }

        project = serviceP.updateProject(project);
        List<Project> projects = serviceP.retriveAllProjects( );
        model.addAttribute("message", project.getProjectId() + " updated.");
        model.addAttribute("projects", projects);
        return "forward:/project/showAllProjects";
    }    
    
    
    @RequestMapping(path = "/projectDelete/{id}", method = RequestMethod.GET)
    public String deleteProject(@PathVariable Integer id) {
        return "forward:/project/deleteProject/"+id;
    }
    
    @RequestMapping(path = "/memberViewEdit", method = RequestMethod.GET)
    public String showTaskMembers() {
        return "forward:/task/showAllTasksAdmin";
    }
    
    @RequestMapping(path = "/taskUpdate/{id}", method = RequestMethod.GET)
    public String updateTask(@PathVariable Integer id, Model model) {
        
        return "forward:/task/updateTaskAdmin/"+id;
    }    
    
    @RequestMapping(path = "/taskUpdate", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute @Valid Task task, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "taskForm";
        }

        task = serviceT.updateTask(task);
        List<Task> tasks = serviceT.retriveAllTasks( );
        model.addAttribute("message", task.getTaskId() + " updated.");
        model.addAttribute("tasks", tasks);
        return "forward:/task/showAllTasksAdmin";
    }
}
