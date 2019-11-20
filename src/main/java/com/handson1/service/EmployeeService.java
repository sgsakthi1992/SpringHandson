package com.handson1.service;

import com.handson1.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {
    Map<Integer,Employee> employeeList = new HashMap<Integer,Employee>();

    public Map<Integer,Employee> getEmployeeList() {
        return employeeList;
    }

    public void createEmployees(Employee employee){
        employeeList.put(employee.getEmployeeId(),employee);
    }

    public void updateEmployee(Employee employee){
        Employee updateEmployee = employeeList.get(employee.getEmployeeId());
        updateEmployee.setEmployeeName("SakthiNew");
    }
}
