package com.mcit.pm.controller;

import com.mcit.pm.model.Services.EmployeeService;
import com.mcit.pm.model.Services.TaskService;
import com.mcit.pm.model.entities.Task;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private EmployeeService serviceE;

    @Autowired
    private TaskService serviceT;

    private Integer getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return Integer.parseInt(name);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String showMemberDefaultPage(Model model) {
        List<Task> tasks;
        model.addAttribute("name", serviceE.getEmployeeById(this.getUserName()).getEmployeeName().toUpperCase());
        tasks = serviceT.retriveAllTasksByMemberId(this.getUserName());
        model.addAttribute("tasks", tasks);
        return "member/member";
    }

    @RequestMapping(path = "/viewTasks", method = RequestMethod.GET)
    public String viewAllTasksByMember() {
        return "forward:/task/showAllTasksByMember";
    }

    @RequestMapping(path = "/taskFinish/{taskId}", method = RequestMethod.GET)
    public String taskFinish(@PathVariable Integer taskId) {
        return "forward:/task/finishTaskUpdate/" + taskId;
    }

    @RequestMapping(path = "/statsView", method = RequestMethod.GET)
    public String showMemberStats() {
        return "forward:/task/showMemberStats/" + this.getUserName();
    }

    @RequestMapping(path = "/projectViewEdit", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllProjects() {
        return "forward:/project/showAllProjects/member";
    }

    @RequestMapping(path = "/listProjectMembers/{id}", method = RequestMethod.GET)
    public String showAllProjectMembers(@PathVariable Integer id) {
        return "forward:/employee/showAllProjectMembers/" + id + "/member";
    }
}
