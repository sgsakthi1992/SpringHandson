package com.handson1;

import com.handson1.model.Department;
import com.handson1.model.Employee;
import com.handson1.service.EmployeeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class EmployeeServiceTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
    EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
    @Test
    public void getEmployeesListTest(){
        assertEquals(employeeService.getEmployeeList().size(), 0);
    }

    @Test
    public void createEmployeesTest(){
        Department department = (Department) context.getBean("department");
        department.setDepartmentId(1);
        department.setDepartmentName("IT");
        Employee employee = (Employee) context.getBean("employee");
        employee.setEmployeeId(1);
        employee.setEmployeeName("Sakthi");
        employee.setDepartment(department);
        employeeService.createEmployees(employee);
        assertEquals(employeeService.getEmployeeList().size(), 1);
    }
}
