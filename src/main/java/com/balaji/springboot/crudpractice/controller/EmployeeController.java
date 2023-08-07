package com.balaji.springboot.crudpractice.controller;

import com.balaji.springboot.crudpractice.Entity.Employee;
import com.balaji.springboot.crudpractice.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@ResponseBody
public class EmployeeController {


    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> fetchEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee =  employeeService.findById(employeeId);
        if (employee == null){
            throw new RuntimeException("Employee is not found - "+ employeeId);
        }
        return employee;
    }

    // add new employee
    @PostMapping("/employee")
    public Employee addNewEmployee(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee employee = employeeService.saveEmployee(theEmployee);
        return employee;
    }

    @PutMapping ("/employee")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee employee = employeeService.saveEmployee(theEmployee);
        return employee;
    }

    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee employee =  employeeService.findById(employeeId);
        if (employee == null){
            throw new RuntimeException("Employee is not found - "+ employeeId);
        }
        employeeService.deleteByEmployeeId(employeeId);
        return "Successfully deleted employee - "+employeeId;
    }
}
