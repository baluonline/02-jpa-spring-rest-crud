package com.balaji.springboot.crudpractice.DAO;

import com.balaji.springboot.crudpractice.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface Employeerepository extends JpaRepository<Employee, Integer> {

}
