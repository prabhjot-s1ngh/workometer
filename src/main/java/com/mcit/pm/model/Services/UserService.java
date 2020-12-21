package com.mcit.pm.model.Services;

import com.mcit.pm.model.entities.Employee;
import com.mcit.pm.model.entities.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService{

    @Autowired
    private EmployeeService service;
    
    @Autowired
    private Employee employee;
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        employee=service.getEmployeeById(Integer.parseInt(string));
        return new EmployeeDetails(employee);
    }
    
}
