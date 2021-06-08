package com.bystrik.springboot.lesson.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;

import com.bystrik.springboot.lesson.IntegrationTestBase;
import com.bystrik.springboot.lesson.entity.EmployeeEntity;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

class EmployeeRepositoryTest extends IntegrationTestBase {

    public static final Integer IVAN_ID = 1;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testFindById(){
        Optional<EmployeeEntity> employee = employeeRepository.findById(IVAN_ID);
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstName(){
        Optional<EmployeeEntity> employee = employeeRepository.findByFirstNameContaining("va");
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstNameAndSalary(){
        List<EmployeeEntity> employees = employeeRepository.findAllByFirstNameAndSalary("Ivan", 1000);
        assertThat(employees, hasSize(1));
    }
}