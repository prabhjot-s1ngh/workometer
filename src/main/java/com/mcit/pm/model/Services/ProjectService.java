package com.mcit.pm.model.Services;

import com.mcit.pm.model.DAO.ProjectDAO;
import com.mcit.pm.model.entities.Project;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectDAO dao;
    
    @Autowired
    private TaskService serviceT; 
    
    public List<Project> saveAndRetrieveProjects(Project project) throws Exception{
        dao.addProject(project);
        return dao.getAllProjects();
    }  
    public List<Project> retriveAllProjects(){
        return dao.getAllProjects();
    }
    public List<Project> retriveAllProjectsByLeaderId(Integer employeeId){
        return dao.getAllProjectsByLeaderId(employeeId);
    }
     
    public List<Project> retriveAllProjectsByMemberId(Integer employeeId){
       List projectIds = serviceT.retriveAllProjectIdsByMemberId(employeeId);
       List<Project> projects= new ArrayList<Project>();
       for(int i=0;i<projectIds.toArray().length;i++){
           projects.add(dao.getProjectById((int)projectIds.toArray()[i]));
       }
        return projects;
    }    
    
    public Project updateProject(Project project){
        return dao.updateProject(project);
    }
    
    public Project getProjectById(Integer projectId){
        return dao.getProjectById(projectId);
    }
    
    public void deleteProjectById(Integer projectId) throws Exception{
        dao.deleteProject(dao.getProjectById(projectId));
    }    
}
