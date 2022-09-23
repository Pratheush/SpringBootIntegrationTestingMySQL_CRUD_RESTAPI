package com.mylearning.springbootintegrationtestingmysql_crud_restapi.repository;

import com.mylearning.springbootintegrationtestingmysql_crud_restapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    
    Optional<Employee> findByFirstName(String firstName).get();

    Optional<Employee> findByFirstNameContainingIgnoreCase(String firstName);
}
