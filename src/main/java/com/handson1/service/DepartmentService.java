package com.handson1.service;

import com.handson1.model.Department;
import com.handson1.model.Employee;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentService {

    public Map<Integer, Employee> getEmployeesList(ApplicationContext context) {
        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
        return employeeService.getEmployeeList();
    }

    public List<Employee> getEmployeesByDepartment(ApplicationContext context, String departmentName) {
        Map<Integer, Employee> employeeList = getEmployeesList(context);
        List<Employee> employeesByDepartment = employeeList.entrySet().stream()
                .filter(e -> (e.getValue().getDepartment().getDepartmentName()).equals(departmentName))
                .map(e -> e.getValue()).collect(Collectors.toList());
        return employeesByDepartment;
    }

    public void assigningDepartmentToEmployee(ApplicationContext context, Integer employeeId, Department department) {
        Map<Integer, Employee> employeeList = getEmployeesList(context);
        Employee employee = employeeList.get(employeeId);
        employee.setDepartment(department);
    }
}