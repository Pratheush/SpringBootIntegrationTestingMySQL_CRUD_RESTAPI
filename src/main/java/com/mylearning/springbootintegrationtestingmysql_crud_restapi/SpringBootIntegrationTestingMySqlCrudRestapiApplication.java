package com.mylearning.springbootintegrationtestingmysql_crud_restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringBootIntegrationTestingMySqlCrudRestapiApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootIntegrationTestingMySqlCrudRestapiApplication.class);
        application.setAdditionalProfiles("dev-mysql");
        application.run(args);
    }

}
