package com.mcit.pm.model.Services;

import com.mcit.pm.model.DAO.EmployeeDAO;
import com.mcit.pm.model.entities.Employee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
     @Autowired
    private EmployeeDAO dao;

    @Autowired
    private TaskService serviceT;      
    
    public List<Employee> saveAndRetrieveEmployees (Employee employee)throws Exception{
        dao.addEmployee(employee);
        return dao.getAllEmployees();
    }
       public Employee updateEmployee(Employee employee){
        return dao.updateEmployee(employee);
    }
    
    public List<Employee> retriveAllEmployees(){
        return dao.getAllEmployees();
    } 
    
    public List<Employee> retriveAllByRole(char role){
        return dao.getAllEmployeesByRole(role);
    }     
    
       
    public Employee getEmployeeById(Integer employeeId){
        return dao.getEmployeeById(employeeId);
    }
    
    public void deleteEmployeeById (Integer employeeId) throws Exception{
        dao.deleteEmployee(dao.getEmployeeById(employeeId));
    }
    
    public List<Employee> retriveAllLeaders(){
        return dao.getAllLeaders();
    }
    
    public List<Employee> retriveAllMembers(){
        return dao.getAllMembers();
    }
    
    public List<Employee> retriveAllMembersIdsByProjectId(Integer employeeId){
       List memberIds = serviceT.retriveAllMembersIdsByProjectId(employeeId);
       List<Employee> employees= new ArrayList<Employee>();
       for(int i=0;i<memberIds.toArray().length;i++){
           employees.add(dao.getEmployeeById((int)memberIds.toArray()[i]));
       }
        return employees;
    } 
}
