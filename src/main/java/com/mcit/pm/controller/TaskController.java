package com.mcit.pm.controller;

import com.mcit.pm.model.DAO.TaskDAO;
import com.mcit.pm.model.Services.EmployeeService;
import com.mcit.pm.model.Services.ProjectService;
import com.mcit.pm.model.Services.TaskService;
import com.mcit.pm.model.entities.Employee;
import com.mcit.pm.model.entities.Project;
import com.mcit.pm.model.entities.Task;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private Project project;

    @Autowired
    private ProjectService serviceP;

    @Autowired
    private EmployeeService serviceE;

    @Autowired
    private Task task;

    @Autowired
    private TaskDAO tdao;

    @Autowired
    private TaskService service;

    private Integer getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return Integer.parseInt(name);
    }

    @RequestMapping(path = "/tForm", method = RequestMethod.GET)
    public String showForm(Model model) {
        List<Employee> employees = serviceE.retriveAllMembers();
        model.addAttribute("employees", employees);
//        List<Project> projects = serviceP.retriveAllProjects();

        List<Project> projects = serviceP.retriveAllProjectsByLeaderId(this.getUserName());
        model.addAttribute("projects", projects);
        return "taskForm";
    }

    @RequestMapping(path = "/updateForm", method = RequestMethod.GET)
    public String showDeleteUpdateForm() {
        return "taskForm";
    }

    @RequestMapping(path = "/showAllTasks", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllTasks(Model model) {
        List<Task> tasks = service.retriveAllTasks();
        model.addAttribute("tasks", tasks);
        return "deleteUpdateTasks";
    }

    @RequestMapping(path = "/showAllTasks/leader", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllTasksByLeader(Model model) {
        List<Task> tasks = service.retriveAllTasksByLeaderId(this.getUserName());
        model.addAttribute("tasks", tasks);
        return "deleteUpdateTasks";
    }

    @RequestMapping(path = {"/showAllTasksByMember/{id}", "/showAllTasksByMember"}, method = RequestMethod.GET)
    public String showAllTasksByMember(@PathVariable(required = false) Integer id, Model model) {
        List<Task> tasks;
        if (id == null) {
            tasks = service.retriveAllTasksByMemberId(this.getUserName());
            model.addAttribute("tasks", tasks);
            return "memberAllTasks";
        } 
        tasks = service.retriveAllTasksByMemberId(id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("caption"," OF "+serviceE.getEmployeeById(id).getEmployeeName().toUpperCase());
        return "deleteUpdateTasks";        
    }

    @RequestMapping(path = "/showAllTasksByProjectId/{projectId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllTasksByProjectId(@PathVariable Integer projectId, Model model) {
        List<Task> tasks = service.retriveAllTasksByProjectId(projectId);
        model.addAttribute("tasks", tasks);
        return "deleteUpdateTasks";
    }
    @RequestMapping(path = "/showAllTasksAdmin", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllTasksAdmin(Model model) {
        List<Task> tasks = service.retriveAllTasks();
        model.addAttribute("tasks", tasks);

        return "addRemoveMembers";
    }

    @RequestMapping(path = "/updateTask/{id}/{role}", method = RequestMethod.GET)
    public String updateTask(@PathVariable Integer id, @PathVariable String role, Model model) {
        task = service.getTaskById(id);
        List<Employee> employees = serviceE.retriveAllMembers();
        model.addAttribute("employees", employees);
        List<Project> projects = serviceP.retriveAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute(task);
        model.addAttribute("role", role);
        return "updateTaskForm";
    }

    @RequestMapping(path = "/updateTaskAdmin/{id}", method = RequestMethod.GET)
    public String updateTaskMemberAdminForm(@PathVariable Integer id, Model model) {
        List<Employee> employees = serviceE.retriveAllMembers();
        model.addAttribute("employees", employees);
        task = service.getTaskById(id);
        model.addAttribute(task);
        return "addRemoveMembersFrom";
    }
    
    @RequestMapping(path = "/deleteTask/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable Integer id, Model model) {
        model.addAttribute("message", id + " " + "deleted");
        service.deleteTaskById(id);
        return "forward:/task/showAllTasks/leader";
    }

    @ModelAttribute("task")
    public Task getTask() {
        return task;
    }

    @RequestMapping(path = "/addTask", method = RequestMethod.POST)
    public String addTask(@ModelAttribute @Valid Task task, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "taskForm";
        }

        List<Task> tasks = service.saveAndRetrieveTasks(task);
        model.addAttribute("tasks", tasks);
        model.addAttribute("t", task);
        return "showTasks";
    }

    @RequestMapping(path = "/updatTask", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute @Valid Task task, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "taskForm";
        }

        task = service.updateTask(task);
        List<Task> tasks = service.retriveAllTasks();
        model.addAttribute("message", task.getTaskId() + " updated.");
        model.addAttribute("tasks", tasks);
        return "deleteUpdateTasks";
    }

    @RequestMapping(path = "/showMemberStats/{id}", method = RequestMethod.GET)
    public String showAllMemberStats(@PathVariable Integer id, Model model) {
        HashMap<String, Integer> hashMap = service.retriveMemberStatsById(id);
        Set set = hashMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mEntry = (Map.Entry) iterator.next();
            model.addAttribute((String) mEntry.getKey(), mEntry.getValue());
        }
        model.addAttribute("id", id);
        model.addAttribute("name", serviceE.getEmployeeById(id).getEmployeeName());
        return "showMemberStats";
    }

    @RequestMapping(path = "/finishTaskUpdate/{taskId}", method = RequestMethod.GET)
    public String updateTask(@PathVariable Integer taskId) {

        Task task = service.getTaskById(taskId);
        long time = System.currentTimeMillis();

        task.setEndDate(new Date(time));
        service.updateTask(task);
        return "forward:/task/showAllTasksByMember";
    }

}
