package org.globaltrainings.dao;


import org.globaltrainings.datastore.EmployeeDB;
import org.globaltrainings.entity.Employee;
import org.globaltrainings.exceptions.EmployeeNotFoundException;
import org.globaltrainings.exceptions.InvalidArgumentException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

    @Override
    public Employee hireEmployee(Employee employee) {
        EmployeeDB.map.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Employee findEmployeeById(int id) {
        if(EmployeeDB.map.containsKey(id)){
            return EmployeeDB.map.get(id);
        }
        else {
            throw new EmployeeNotFoundException("No Employee found with the mentioned ID");
        }
    }

    @Override
    public List<Employee> findAllEmployees() {
        List<Employee> employeeList = EmployeeDB.map.values().stream().collect(Collectors.toList());
        if(employeeList.isEmpty()){
            throw new EmployeeNotFoundException("No Employees present in the Organization");
        }
        else {
            return employeeList;
        }
    }
}
