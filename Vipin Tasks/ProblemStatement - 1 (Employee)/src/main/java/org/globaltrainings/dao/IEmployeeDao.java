package org.globaltrainings.dao;

import org.globaltrainings.entity.Employee;

import java.util.List;

public interface IEmployeeDao {
    Employee hireEmployee(Employee employee);
    Employee findEmployeeById(int id);
    List<Employee> findAllEmployees();
}
