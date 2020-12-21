package com.mcit.pm.model.entities;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmployeeDetails implements UserDetails {

    @Autowired
    private Employee employee;

    public EmployeeDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (employee.getRole()) {
            case 'M':
                return Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"));
            case 'A':
                return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case 'L':
                return Arrays.asList(new SimpleGrantedAuthority("ROLE_LEADER"));
            default:
                break;
        }
        return Arrays.asList(new SimpleGrantedAuthority(null));
    }

    @Override
    public String getPassword() {
        return employee.getePassword();
    }

    @Override
    public String getUsername() {
        return employee.getEmployeeId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (employee.getEnabled() == 'Y') {
            return true;
        }
        return false;
    }

}
