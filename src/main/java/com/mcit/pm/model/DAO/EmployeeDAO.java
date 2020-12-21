package com.mcit.pm.model.DAO;

import com.mcit.pm.model.entities.Employee;
import java.util.List;


public interface EmployeeDAO {
    public void addEmployee(Employee employee) throws Exception;   
    public List<Employee> getAllEmployees();  
    public List<Employee> getAllEmployeesByRole(char role);  
    public Employee getEmployeeById(Integer employeeId);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(Employee  employee) throws Exception;
    public List<Employee> getAllLeaders();
    public List<Employee> getAllMembers();

}
