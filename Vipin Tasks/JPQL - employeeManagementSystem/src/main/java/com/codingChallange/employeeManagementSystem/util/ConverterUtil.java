package com.codingChallange.employeeManagementSystem.util;

import com.codingChallange.employeeManagementSystem.dto.DepartmentResponce;
import com.codingChallange.employeeManagementSystem.dto.EmployeeResponce;
import com.codingChallange.employeeManagementSystem.dto.ProjectResponce;
import com.codingChallange.employeeManagementSystem.entity.Department;
import com.codingChallange.employeeManagementSystem.entity.Employee;
import com.codingChallange.employeeManagementSystem.entity.Project;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterUtil {
    public List<ProjectResponce> toProjectResponceList(List<Project> projects){
        List<ProjectResponce> projectResponces = new ArrayList<>();
        for(Project project : projects){
            ProjectResponce projectResponce = new ProjectResponce();
            projectResponce.setProjectId(project.getProjectId());
            projectResponce.setProjectName(project.getProjectName());
            projectResponce.setProjectLocation(project.getProjectLocation());
            projectResponce.setEmployeeList(project.getEmployeeList());
            projectResponces.add(projectResponce);
        }
        return projectResponces;
    }

    public List<EmployeeResponce> toEmployeeResponceList(List<Employee> employees){
        List<EmployeeResponce> employeeResponces = new ArrayList<>();
        for(Employee employee : employees){
            EmployeeResponce employeeResponce = new EmployeeResponce();
            employeeResponce.setEmployeeId(employee.getEmployeeId());
            employeeResponce.setFirstName(employee.getFirstName());
            employeeResponce.setLastName(employee.getLastName());
            employeeResponce.setBirthDate(employee.getBirthDate());
            employeeResponce.setContactNumber(employee.getContactNumber());
            employeeResponce.setDepartment(employee.getDepartment());
            employeeResponce.setProjectList(employee.getProjectList());

            employeeResponces.add(employeeResponce);
        }
        return employeeResponces;
    }

    public List<DepartmentResponce> toDepartmentResponce(List<Department> departments){
        List<DepartmentResponce> departmentResponces = new ArrayList<>();
        for(Department department: departments){
            DepartmentResponce departmentResponce = new DepartmentResponce();
            departmentResponce.setDeptId(department.getDeptId());
            departmentResponce.setDeptName(department.getDeptName());
            departmentResponce.setEmployeeList(department.getEmployeeList());

            departmentResponces.add(departmentResponce);
        }
        return departmentResponces;
    }
}
