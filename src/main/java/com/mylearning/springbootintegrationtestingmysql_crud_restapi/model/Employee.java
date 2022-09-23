package com.mylearning.springbootintegrationtestingmysql_crud_restapi.model;

import lombok.*;

import javax.persistence.*;

//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@ToString
@Data
@Builder
@Entity
@Table(name = "employees")
public class Employee {

    private static final long SerialVersionUID=10l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    
}
