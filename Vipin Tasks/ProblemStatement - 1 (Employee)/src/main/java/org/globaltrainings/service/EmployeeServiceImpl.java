package org.globaltrainings.service;


import org.globaltrainings.dao.EmployeeDaoImpl;
import org.globaltrainings.dao.IEmployeeDao;
import org.globaltrainings.entity.Address;
import org.globaltrainings.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private IEmployeeDao employeeDao;
    private static int id;

    @Override
    public Employee hireEmployee(String name, double salary, LocalDate dob, LocalDate doj, String houseNo, String street, String city) {
        Address address = new Address(houseNo, street, city);
        Employee employee = new Employee(++id, name, salary, dob, doj, address);
        employeeDao.hireEmployee(employee);
        return employee;
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeDao.findEmployeeById(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    @Override
    public List<Employee> sortEmployeesBySalary() {
        List<Employee> employeeList = findAllEmployees();
        Collections.sort(employeeList, Comparator.comparingDouble(Employee::getSalary));
        return employeeList;
    }
}
