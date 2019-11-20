package com.handson1;

import com.handson1.model.Department;
import com.handson1.model.Employee;
import com.handson1.service.DepartmentService;
import com.handson1.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");

        Department department = (Department) context.getBean("department");
        department.setDepartmentId(1);
        department.setDepartmentName("IT");

        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");

        for(int i = 0; i<5; i++) {
            Employee employee =(Employee) context.getBean("employee");
            employee.setEmployeeId(1+i);
            employee.setEmployeeName("Sakthi"+i);
            employee.setDepartment(department);

            employeeService.createEmployees(employee);
        }
        System.out.println(employeeService.getEmployeeList());

        DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");
        System.out.println(departmentService.getEmployeesByDepartment(context, "IT"));
        Department newDepartment = (Department) context.getBean("department");
        newDepartment.setDepartmentId(2);
        newDepartment.setDepartmentName("CSE");
        departmentService.assigningDepartmentToEmployee(context,1,newDepartment);
        System.out.println(departmentService.getEmployeesByDepartment(context, "CSE"));
    }
}
