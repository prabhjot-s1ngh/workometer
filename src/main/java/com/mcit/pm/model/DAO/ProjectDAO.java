package com.mcit.pm.model.DAO;

import com.mcit.pm.model.entities.Project;
import java.util.List;


public interface ProjectDAO {
    public void addProject(Project project) throws Exception;   
    public List<Project> getAllProjects();  
    public List<Project> getAllProjectsByLeaderId(Integer leaderId);  
    public Project getProjectById(Integer projectId);
    public Project updateProject(Project project);
    public void deleteProject(Project  project) throws Exception;
}
