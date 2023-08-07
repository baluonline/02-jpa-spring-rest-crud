package com.balaji.springboot.crudpractice.Service;

import com.balaji.springboot.crudpractice.DAO.Employeerepository;
import com.balaji.springboot.crudpractice.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private Employeerepository employeerepository;

    public EmployeeServiceImpl() {
    }

    @Autowired
    public EmployeeServiceImpl(Employeerepository employeerepository) {
        this.employeerepository = employeerepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeerepository.findAll();
    }
    @Override
    public Employee findById(int id) {
        Optional<Employee> result =   employeerepository.findById(id);
        Employee theEmployee = null;
        if(result.isPresent()){
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Employee is not found - "+id);
        }
        return theEmployee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeerepository.save(employee);
    }
   @Override
    public void deleteByEmployeeId(int theId) {
        employeerepository.deleteById(theId);
    }
}
