package com.mcit.pm.model.DAOImpl;

import com.mcit.pm.model.DAO.EmployeeDAO;
import com.mcit.pm.model.DAO.ProjectDAO;
import com.mcit.pm.model.entities.Project;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private EmployeeDAO edao;
    
    @Override
    public void addProject(Project project) throws Exception{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        project.setEmployee(edao.getEmployeeById(project.getEmployee().getEmployeeId()));
        session.flush();
        session.save(project);
        session.getTransaction().commit();
    }

    @Override
    public List<Project> getAllProjects() {
        Session session = sessionFactory.openSession();
        return session.createCriteria(Project.class).list();
    }

    @Override
    public Project getProjectById(Integer projectId) {
        Session session = sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Project.class);  
   
        criteria.add(Restrictions.eq("projectId", projectId));
        if(criteria.list().isEmpty()){
            return null;
        }
        Project project ;
        project=(Project)criteria.list().get(0);
        session.close();
        return project;     
    }

    @Override
    public Project updateProject(Project project) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        project.setEmployee(edao.getEmployeeById(project.getEmployee().getEmployeeId()));
        session.update(project);
        session.getTransaction().commit();
        return project;    
    }

    @Override
    public void deleteProject(Project project) throws Exception{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        project.setEmployee(edao.getEmployeeById(project.getEmployee().getEmployeeId()));
        session.delete(project);
        session.getTransaction().commit();    
    }

    @Override
    public List<Project> getAllProjectsByLeaderId(Integer leaderId) {
        Session session = sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Project.class);         
        criteria.add(Restrictions.eq("employee.employeeId", leaderId));
        List<Project> projects= criteria.list();
        session.close();
        return projects;        
    }

    
}
