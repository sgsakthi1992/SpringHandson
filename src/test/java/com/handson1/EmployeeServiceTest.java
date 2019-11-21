package com.handson1;

import com.handson1.model.Department;
import com.handson1.model.Employee;
import com.handson1.service.EmployeeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class EmployeeServiceTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
    private EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

    @Test
    public void getEmployeesListTest() {
        assertEquals(0, employeeService.getEmployeeList().size());
    }

    @Test
    public void createEmployeesTest() {
        Department department = (Department) context.getBean("department");
        department.setDepartmentId(1);
        department.setDepartmentName("IT");
        Employee employee = (Employee) context.getBean("employee");
        employee.setEmployeeId(1);
        employee.setEmployeeName("Sakthi");
        employee.setDepartment(department);
        employeeService.createEmployees(employee);
        assertEquals(1, employeeService.getEmployeeList().size());
    }
}
