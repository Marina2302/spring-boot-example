package com.bystrik.springboot.lesson;

import com.bystrik.springboot.lesson.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@Sql("/sql/companyData.sql")
@Sql("/sql/employeeData.sql")
@ActiveProfiles("test")
@SpringBootTest
@Transactional
@ContextConfiguration(initializers = {
        Postgres.Initializer.class
})
public abstract class IntegrationTestBase {

    @BeforeAll
    static void init(){
        Postgres.container.start();
    }
}
