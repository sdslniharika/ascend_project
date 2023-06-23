package org.globaltrainings.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private LocalDate dob;
    private LocalDate doj;
    private Address address;

    public Employee(){}
    public Employee(int id, String name, double salary, LocalDate dob, LocalDate doj, Address address){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dob = dob;
        this.doj = doj;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public Address getAddress() {
        return address;
    }

    @Autowired
    @Qualifier("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", dob=" + dob +
                ", doj=" + doj +
                ", address=" + address +
                '}';
    }
}
