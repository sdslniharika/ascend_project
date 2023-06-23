package org.globaltrainings.frontend;

import org.globaltrainings.entity.Employee;
import org.globaltrainings.exceptions.EmployeeNotFoundException;
import org.globaltrainings.exceptions.InvalidArgumentException;
import org.globaltrainings.service.EmployeeServiceImpl;
import org.globaltrainings.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class FrontEnd {
    @Autowired
    private IEmployeeService service;

    public void runUI(){
        try {
            Employee employee1 = service.hireEmployee("Abc", 90000, LocalDate.parse("1995-08-17"), LocalDate.parse("2015-02-10"), "A-3", "Lane1", "ZZZ");
            display(employee1);
            Employee employee2 = service.hireEmployee("Bbc", 97000, LocalDate.parse("1995-08-17"), LocalDate.parse("2015-02-10"), "V-3", "Lane4", "YYY");
            display(employee2);
            Employee employee3 = service.hireEmployee("Cbc", 20000, LocalDate.parse("1995-08-17"), LocalDate.parse("2015-02-10"), "J-3", "Lane2", "ZZZ");
            display(employee3);
            Employee employee4 = service.hireEmployee("Dbc", 40000, LocalDate.parse("1995-08-17"), LocalDate.parse("2015-02-10"), "D-3", "Lane3", "YYY");
            display(employee4);

            System.out.println("----------------------------------------------------------------------------------------------");

        }catch (InvalidArgumentException e){
            System.out.println(e.getMessage());
        }
        catch (EmployeeNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println("something went wrong, Please try again");
        }
    }

    public List<Employee> findALlEmployees(){
        System.out.println("All Employee Details is as below : ");
        List<Employee> allEmployeeList = service.findAllEmployees();
        return allEmployeeList;
    }

    public List<Employee> sortedEmployeesBySalaryInAscending(){
        System.out.println("All Employee Details sorted by salary in Ascending Order are as below : ");
        List<Employee> salaryEmployeeList = service.sortEmployeesBySalary();
        return salaryEmployeeList;
    }


    public void display(Employee employee){
        System.out.println("Hired a new Employee with Details : "+employee);
    }
    public void displayList(List<Employee> employeeList){
        System.out.println(employeeList);
        System.out.println("---------------------------------------------------------------------------------------------------");
    }

}
