package com.mylearning.springbootintegrationtestingmysql_crud_restapi;

import com.mylearning.springbootintegrationtestingmysql_crud_restapi.model.Employee;
import com.mylearning.springbootintegrationtestingmysql_crud_restapi.repository.EmployeeRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

// Write Integration Tests using Testcontainers . We gonna use the Singleton containers pattern to use Testcontainers.
// Singleton containers pattern is useful to define a container that is only started once for several test classes.

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ActiveProfiles({"dev-mysql"})
public class EmployeeRepositoryTest1 extends AbstractContainerBaseTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

   // given/when/then format - BDD style
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        // given - setup or precondition
        Employee employe = Employee.builder().firstName("Monu")
                .lastName("Singh").email("monu@gmail.com").build();

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
        Employee employee = Employee.builder().firstName("Ramu")
                .lastName("Sharma").email("ramu@gmail.com").build();
        Employee savedStudent = employeeRepository.save(employee);

        // when - action or the testing
        Employee employedb = employeeRepository.findById(employee.getId()).get();

        // then - very output
        Assertions.assertNotNull(employedb);
    }

    @Test
    public void givenStudents_whenGetAllStudents_thenListOfStudents() throws Exception {
        // given - setup or precondition
        List<Employee> employees =
                List.of(Employee.builder().firstName("Ramesh").lastName("faadatare").email("ramesh@gmail.com").build(),
                        Employee.builder().firstName("tony").lastName("stark").email("tony@gmail.com").build());
        employeeRepository.saveAll(employees);

        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(employees.size())));
    }
}
