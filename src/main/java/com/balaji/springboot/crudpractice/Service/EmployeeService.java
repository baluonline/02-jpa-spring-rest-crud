package com.balaji.springboot.crudpractice.Service;

import com.balaji.springboot.crudpractice.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee saveEmployee(Employee employee);

    void deleteByEmployeeId(int theId);
}
