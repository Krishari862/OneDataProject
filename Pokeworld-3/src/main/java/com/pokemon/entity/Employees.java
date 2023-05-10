package com.pokemon.entity;

import java.util.ArrayList;
import java.util.List;

public class Employees 
{
    private List<Pokemon> employeeList;
    
    public List<Pokemon> getEmployeeList() {
        if(employeeList == null) {
            employeeList = new ArrayList<>();
        }
        return employeeList;
    }
 
    public void setEmployeeList(List<Pokemon> employeeList) {
        this.employeeList = employeeList;
    }
}
