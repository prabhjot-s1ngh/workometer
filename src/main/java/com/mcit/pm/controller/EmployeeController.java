package com.mcit.pm.controller;

import com.mcit.pm.model.Services.EmployeeService;
import com.mcit.pm.model.entities.Employee;
import com.mcit.pm.model.entities.Task;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private Employee employee;

    @Autowired
    private EmployeeService service;

    @RequestMapping(path = "/eForm", method = {RequestMethod.GET,RequestMethod.POST})
    public String showForm(Model model) {
        model.addAttribute("heading", "Add");
        model.addAttribute("isReadble", "false");
        return "employeeForm";
    }

    @ModelAttribute("employee")
    public Employee getEmployee() {
        return employee;
    }

    @RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute @Valid Employee employee, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "employeeForm";
        }
        try{
            List<Employee> employees = service.saveAndRetrieveEmployees(employee);
            model.addAttribute("employees", employees);
            return "deleteUpdateEmployees";            
        }
        catch(Exception e){
            
        model.addAttribute("heading", "Add");
        model.addAttribute("isReadble", "false");
        return "employeeForm";            
        }
    }

    @RequestMapping(path = "/showAllEmployees", method = {RequestMethod.POST, RequestMethod.GET})
    public String showAllEmployees(Model model) {
        List<Employee> employees = service.retriveAllEmployees();
        model.addAttribute("employees", employees);
        return "deleteUpdateEmployees";
    }

    @RequestMapping(path = {"/updateEmployee/{id}"}, method = RequestMethod.GET)
    public String updateEmployee(@PathVariable(required = false) Integer id, Model model) {
        model.addAttribute("heading", "Update");
        model.addAttribute("isReadble", "true");
        employee = service.getEmployeeById(id);
        model.addAttribute(employee);

        return "updateEmployeeForm";
    }

    @RequestMapping(path = "/deleteEmployee/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable Integer id, Model model) {
        String message;
        try {
            service.deleteEmployeeById(id);
            message = "USER " + id + " IS DELETED.";
        } catch (Exception ex) {
            System.out.println("error: " + ex);
            message = "USER IS ASSIGNED TO PROJECT OR TASK.";
        }
        List<Employee> employees = service.retriveAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("message", message);
        return "deleteUpdateEmployees";
    }

    @RequestMapping(path = "/updatEmployee", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute @Valid Employee employee, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "EmployeeForm";
        }

        employee = service.updateEmployee(employee);
        List<Employee> employees = service.retriveAllEmployees();
        model.addAttribute("message", employee.getEmployeeId() + " updated.");
        model.addAttribute("employees", employees);
        return "deleteUpdateEmployees";
    }

    @RequestMapping(path = "/showAllMembers", method = RequestMethod.GET)
    public String showAllMembers(Model model) {
        List<Employee> employees = service.retriveAllByRole('M');
        model.addAttribute("employees", employees);
        return "showMembers";
    }

    @RequestMapping(path = "/showAllProjectMembers/{projectId}/{role}", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllMembersByProjectId(@PathVariable Integer projectId,@PathVariable String role, Model model) {
        List<Employee> employees = service.retriveAllMembersIdsByProjectId(projectId);
        model.addAttribute("employees", employees);
        model.addAttribute("projectId", projectId);
        model.addAttribute("role",role);
        return "showMemberByProject";
    }
}
