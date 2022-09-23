package com.mylearning.springbootintegrationtestingmysql_crud_restapi;

//Create Integration Tests for Data Access or Repository Layer with MySQL database
// Let's first write Integration tests for EmployeeRepository without using Testcontainers and then later we will use Testcontainers.

// The Spring boot provides @DataJpaTest annotation. This annotation will disable full auto-configuration and instead apply
// only configuration relevant to JPA tests. By default, it will use an embedded, in-memory H2 database instead of the one declared in the configuration file, for faster test
// Note that we have disabled H2 in-memory database support using @AutoConfigureTestDatabase annotation because we want to run Integration tests with the MySQL database.

// What is the problem with the Integration test that we have written?
//A common problem when writing integration tests is the dependency on installed components (Ex: MySQL, RabbitMQ) where the integration tests are supposed to run.
//Basically, our integration tests depend on external services (installing MySQL, Rabbit MQ, Redis, etc) to run the integration tests right then how to reduce this dependency - what will be the solution.
// The solution is Testcontainers.
//  Testcontainers is a Java library that supports JUnit tests, providing lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container.
//  Testcontainer allows us to use Docker containers within our tests. As a result, we can write self-contained integration tests that depend on external resources.
import com.mylearning.springbootintegrationtestingmysql_crud_restapi.model.Employee;
import com.mylearning.springbootintegrationtestingmysql_crud_restapi.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles({"dev-mysql"})
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    // JUnit for save Employee operation - BDD style
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        // given - setup or precondition
        Employee employe = Employee.builder().firstName("Sonu")
                .lastName("Singh").email("sonu@gmail.com").build();

        // when - action or the testing
        Employee savedEmployee = employeeRepository.save(employe);

        // then - very output
        Assertions.assertNotNull(savedEmployee);
        Assertions.assertNotNull(savedEmployee.getId());

    }

    // JUnit for findById student operation - BDD style
    @Test
    public void givenStudentId_whenfindbyId_thenReturnSavedStudent(){

        // given - setup or precondition
        Employee employee = Employee.builder().firstName("Rambo")
                .lastName("Sharma").email("rambo@gmail.com").build();
        Employee savedStudent = employeeRepository.save(employee);

        // when - action or the testing
        Employee employedb = employeeRepository.findById(employee.getId()).get();

        // then - very output
        Assertions.assertNotNull(employedb);
    }
}
