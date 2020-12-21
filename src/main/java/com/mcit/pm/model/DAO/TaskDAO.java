package com.mcit.pm.model.DAO;

import com.mcit.pm.model.entities.Project;
import com.mcit.pm.model.entities.Task;
import java.util.List;


public interface TaskDAO {
    public void addTask(Task task);   
    public List<Task> getAllTasks(); 
    public List<Task> getAllTasksByMemberId(Integer memberId);  
    public List<Task> getAllTasksByProjectId(Integer projectId);  
    public List<Task> getAllTasksByProjects(List<Project> projects);   
    public Task getTaskById(Integer taskId);
    public Task updateTask(Task task);
    public void deleteTask(Task task);
    public void updateTaskMember(Integer taskId,Integer employeeId);
    public List getAllProjectsIdsByMemberId(Integer memberId) ;
    public List getAllMembersIdsByProjectId(Integer memberId) ;

    
}
