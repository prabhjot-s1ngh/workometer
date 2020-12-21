package com.mcit.pm.model.DAOImpl;

import com.mcit.pm.model.DAO.EmployeeDAO;
import com.mcit.pm.model.DAO.ProjectDAO;
import com.mcit.pm.model.DAO.TaskDAO;
import com.mcit.pm.model.entities.Project;
import com.mcit.pm.model.entities.Task;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EmployeeDAO edao;

    @Autowired
    private ProjectDAO pdao;

    @Override
    public void addTask(Task task) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        task.setEmployee(edao.getEmployeeById(task.getEmployee().getEmployeeId()));
        task.setProject(pdao.getProjectById(task.getProject().getProjectId()));
        session.save(task);
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        Session session = sessionFactory.openSession();
        return session.createCriteria(Task.class).list();
    }

    @Override
    public List<Task> getAllTasksByProjects(List<Project> projects) {
        List<Task> tasks = new ArrayList<Task>();
        if (projects.isEmpty()) {
            return null;
        } else {
            for (Project project : projects) {
                Session session = sessionFactory.openSession();
                Criteria criteria = session.createCriteria(Task.class);

                criteria.add(Restrictions.eq("project.projectId", project.getProjectId()));
                tasks.addAll(criteria.list());
            }
            return tasks;
        }
    }

    @Override
    public List<Task> getAllTasksByMemberId(Integer memberId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Task.class);

        criteria.add(Restrictions.eq("employee.employeeId", memberId));
        return criteria.list();
    }

    @Override
    public Task getTaskById(Integer taskId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Task.class);

        criteria.add(Restrictions.eq("taskId", taskId));
        if (criteria.list().isEmpty()) {
            return null;
        }
        Task task;
        task = (Task) criteria.list().get(0);
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        task.setEmployee(edao.getEmployeeById(task.getEmployee().getEmployeeId()));
        task.setProject(pdao.getProjectById(task.getProject().getProjectId()));
        session.update(task);
        session.getTransaction().commit();
        return task;
    }

    @Override
    public void deleteTask(Task task) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            task.setEmployee(edao.getEmployeeById(task.getEmployee().getEmployeeId()));
            task.setProject(pdao.getProjectById(task.getProject().getProjectId()));

            session.delete(task);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void updateTaskMember(Integer taskId, Integer employeeId) {
        Session session = sessionFactory.openSession();
        String hql = "update task set employeeId=: eid where taskId= :tid";
        Query query = session.createQuery(hql);
        query.setInteger("eid", employeeId);
        query.setInteger("tid", taskId);
        int rowCount = query.executeUpdate();
    }

    @Override
    public List<Task> getAllTasksByProjectId(Integer projectId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Task.class);
        criteria.add(Restrictions.eq("project.projectId", projectId));
        return criteria.list();
    }

    @Override
    public List getAllProjectsIdsByMemberId(Integer memberId) {
        List projectIds;
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Task.class);
            criteria.add(Restrictions.eq("employee.employeeId", memberId));
            criteria.setProjection(Projections.distinct(Projections.property("project.projectId").as("projectId")));
            projectIds = criteria.list();
        }
        return projectIds;
    }

    @Override
    public List getAllMembersIdsByProjectId(Integer projectId) {
        List projectIds;
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Task.class);
            criteria.add(Restrictions.eq("project.projectId", projectId));
            criteria.setProjection(Projections.distinct(Projections.property("employee.employeeId").as("employeeId")));
            projectIds = criteria.list();
        }
        return projectIds;
    }
}
