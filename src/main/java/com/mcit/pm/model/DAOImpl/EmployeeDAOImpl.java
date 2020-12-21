package com.mcit.pm.model.DAOImpl;

import com.mcit.pm.model.DAO.EmployeeDAO;
import com.mcit.pm.model.entities.Employee;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEmployee(Employee employee) throws Exception{
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        return session.createCriteria(Employee.class).list();
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        Employee employee;
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("employeeId", employeeId));
            if (criteria.list().isEmpty()) {
                return null;
            }   
            employee = (Employee) criteria.list().get(0);
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(employee);
        session.getTransaction().commit();
        return employee;
    }


    @Override
    public void deleteEmployee(Employee employee) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
    }

    @Override
    public List<Employee> getAllLeaders() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("role", 'L'));
        criteria.add(Restrictions.eq("enabled", 'Y'));
        return criteria.list();

    }

    @Override
    public List<Employee> getAllMembers() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("role", 'M'));
        criteria.add(Restrictions.eq("enabled", 'Y'));
        return criteria.list();
    }

    @Override
    public List<Employee> getAllEmployeesByRole(char role) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("role", role));
        criteria.add(Restrictions.eq("enabled", 'Y'));
        return criteria.list();    }

}
