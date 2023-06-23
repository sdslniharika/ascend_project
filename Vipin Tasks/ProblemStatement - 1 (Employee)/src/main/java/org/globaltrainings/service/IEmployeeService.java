package org.globaltrainings.service;


import org.globaltrainings.entity.Employee;

import java.time.LocalDate;
import java.util.List;

public interface IEmployeeService {
    Employee hireEmployee(String name, double salary, LocalDate dob, LocalDate doj, String houseNo, String street, String city);
    Employee findEmployeeById(int id);
    List<Employee> findAllEmployees();
    List<Employee> sortEmployeesBySalary();
}
