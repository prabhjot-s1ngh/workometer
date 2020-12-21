package com.mcit.pm.model.Services;

import com.mcit.pm.model.DAO.ProjectDAO;
import com.mcit.pm.model.DAO.TaskDAO;
import com.mcit.pm.model.entities.Task;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private ProjectDAO daoP;
    @Autowired
    private TaskDAO dao;

    public List<Task> saveAndRetrieveTasks(Task task) {
        dao.addTask(task);
        return dao.getAllTasks();
    }

    public List<Task> retriveAllTasks() {
        return dao.getAllTasks();
    }
    
    public List<Task> retriveAllTasksByMemberId(Integer employeeId) {
        return dao.getAllTasksByMemberId(employeeId);
    }    

    public List<Task> retriveAllTasksByLeaderId(Integer leader_id) {
        return dao.getAllTasksByProjects(daoP.getAllProjectsByLeaderId(leader_id));
    }

    public List<Task> retriveAllTasksByProjectId(Integer projectId) {
        return dao.getAllTasksByProjectId(projectId);
    }
    
    
    public List retriveAllProjectIdsByMemberId(Integer employeeId){
        
        return dao.getAllProjectsIdsByMemberId(employeeId);
        
    }  
    
    
    

    public Task getTaskById(Integer taskId) {
        return dao.getTaskById(taskId);
    }

    public Task updateTask(Task task) {
        return dao.updateTask(task);
    }

    public void deleteTaskById(Integer taskId) {
        dao.deleteTask(dao.getTaskById(taskId));
    }

    public void updateTaskMember(Integer taskId, Integer employeeId) {
        dao.updateTaskMember(taskId, employeeId);
    }

    public List retriveAllMembersIdsByProjectId(Integer employeeId){
        return dao.getAllMembersIdsByProjectId(employeeId);
    }    
    
    public HashMap<String, Integer> retriveMemberStatsById(Integer id) {
        List<Task> tasks = dao.getAllTasksByMemberId(id);
        Integer inbound = 0, outbound = 0, completed = 0, pending = 0;
        HashMap hm=new HashMap<String,Integer>();
        for (Task task : tasks) {
            if (task.getEndDate() == null) {
                pending++;
            } else {
                if (task.getEndDate().before(task.getDeadline())) {
                    inbound++;

                } else {
                    outbound++;
                }
                completed++;
            }
        }
        hm.put("inbound", inbound);
        hm.put("outbound", outbound);
        hm.put("completed", completed);
        hm.put("pending", pending);
        return hm;
    }
}
