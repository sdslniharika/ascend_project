package org.globaltrainings;

import org.globaltrainings.entity.Address;
import org.globaltrainings.entity.Employee;
import org.globaltrainings.frontend.FrontEnd;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ApplicationLaunch {
    public static void main(String args[]){
        ApplicationContext context=new AnnotationConfigApplicationContext(JavaConfig.class);

        Employee employee = context.getBean(Employee.class);
        Address address = context.getBean((Address.class));

        FrontEnd frontEnd=context.getBean(FrontEnd.class);
        frontEnd.runUI();

        List<Employee> employeeList = frontEnd.findALlEmployees();
        frontEnd.displayList(employeeList);

        List<Employee> employeeListSortedBySalary = frontEnd.sortedEmployeesBySalaryInAscending();
        frontEnd.displayList(employeeListSortedBySalary);


    }
}
