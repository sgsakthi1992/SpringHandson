package com.handson1;

import com.handson1.model.Department;
import com.handson1.model.Employee;
import com.handson1.service.DepartmentService;
import com.handson1.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:SpringBeans.xml"})
public class DepartmentServiceTest {

    @Autowired
    private
    ApplicationContext context;
    private Map<Integer, Employee> mockedEmployeeList = new HashMap<>();
    private Department department;
    private Department department1;
    @Before
    public void setMockedEmployeeList() {
        department = (Department) context.getBean("department");
        department.setDepartmentId(1);
        department.setDepartmentName("IT");
        department1 = (Department) context.getBean("department");
        department1.setDepartmentId(2);
        department1.setDepartmentName("CSE");
        for (int i = 0; i < 5; i++) {
            Employee employee = (Employee) context.getBean("employee");
            employee.setEmployeeId(1 + i);
            employee.setEmployeeName("Sakthi" + i);
            if (i % 2 == 0)
                employee.setDepartment(department);
            else
                employee.setDepartment(department1);
            mockedEmployeeList.put(employee.getEmployeeId(), employee);
        }
    }

    @Test
    public void getEmployeesByDepartmentTest() {
        DepartmentService departmentService = mock(DepartmentService.class);
        when(departmentService.getEmployeesList(context)).thenReturn(mockedEmployeeList);
        when(departmentService.getEmployeesByDepartment(context, "IT")).thenCallRealMethod();
        when(departmentService.getEmployeesByDepartment(context, "CSE")).thenCallRealMethod();

        assertEquals(3, departmentService.getEmployeesByDepartment(context, "IT").size());
        assertEquals(2, departmentService.getEmployeesByDepartment(context, "CSE").size());
    }

    @Test
    public void assigningDepartmentToEmployeeTest() {
        DepartmentService departmentService = mock(DepartmentService.class);
        when(departmentService.getEmployeesList(context)).thenReturn(mockedEmployeeList);
        doCallRealMethod().when(departmentService).assigningDepartmentToEmployee(context, 2, department);
        when(departmentService.getEmployeesByDepartment(context, "IT")).thenCallRealMethod();
        departmentService.assigningDepartmentToEmployee(context, 2, department);
        assertEquals(4, departmentService.getEmployeesByDepartment(context, "IT").size());
    }
}
