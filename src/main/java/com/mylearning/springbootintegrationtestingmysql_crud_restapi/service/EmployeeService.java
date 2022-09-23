package com.mylearning.springbootintegrationtestingmysql_crud_restapi.service;

import com.mylearning.springbootintegrationtestingmysql_crud_restapi.exception.EmployeeNotFoundException;
import com.mylearning.springbootintegrationtestingmysql_crud_restapi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee) throws EmployeeNotFoundException;
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Employee updateEmployee(Employee updatedEmployee);
    void deleteEmployee(long id);
}
